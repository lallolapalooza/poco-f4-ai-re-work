#version 300 es
#ifdef GL_ES
   precision highp float;
   precision highp sampler3D;
#endif
#if __VERSION__ >= 120
   in vec2 varTexcoord;
   out vec4 outputColor;
#else
   varying vec2 varTexcoord;
#endif
uniform sampler2D text_y, text_u, text_v, text_a, text_fliter;
uniform sampler2D text_light, noise_light, low_brightness;
uniform sampler3D filter_rgb;
uniform float width, height, width_stride, smoothStepFactor;
uniform int alphaPlaneExisted; // 0 for horizental 1 for vertical
uniform float videoFilterIntensity;
uniform int videoFilter;
uniform bool extraVideoFilter, haveNoise, needBlur;
uniform bool needColorSeparation, verticalScreen, haveFace, isMoving;
uniform vec2 moveLightPos;
uniform vec4 facePosition;

float MULTIPLY(float x, float a, float b) {
    return x * a * b;
}

float SCREEN(float x, float a, float b) {
   return 1.0 - x * ((1.0 -a)*(1.0 -b));
}

float BURN(float a, float b) {
  if (a == 0.0) {
    return a;
  } else {
    return max(0.0, (1.0 - (1.0 - b) / a));
  } 
}

float DODGE(float a, float b) {
   if (a == 1.0) {
      return a;
   } else {
      return min (1.0, (b / (1.0 -a)));
   }
}

float addition(float a, float b) {
  return min(1.0, (a +b));
}

float average(float a, float b) {
  return (a + b) / 2.0;
}

float subtract(float a, float b) {
   return max(0.0, (a - b));
}

float multiply(float a, float b) {
  return MULTIPLY(1.0, a, b);
}

float negation(float a, float b) {
  return 1.0 - abs(1.0 - a - b);
}

float difference(float a , float b) {
  return abs(a - b);
}

float screen(float a, float b) {
  return SCREEN(1.0, a, b);
}

float overlay(float a, float b) {
  if (a < 0.5) {
    return MULTIPLY(2.0, a, b);
  } else {
    return SCREEN(2.0, a, b);
  }
}

float hardlight(float a, float b) {
   if (b < 0.5) {
    return MULTIPLY(2.0, b, a);
  } else {
    return SCREEN(2.0, b, a);
  }
}

float hardmix(float a, float b) {
   if (a < (1.0 - b)) {
     return 0.0;
   } else {
     return 1.0;
   } 
}

float darken(float a, float b) {
  return min(a, b);
}

float lighten(float a, float b) {
  return max(a, b);
}

float divide(float a, float b) {
   return (a / b) * 1.0; 
}

float dodge(float a, float b) {
   return DODGE(a, b);
}

float burn(float a, float b) {
   return BURN(a, b);
}

float softlight(float a, float b) {
   if (a > (127.0 / 255.0)) {
     return b + (1.0 -b) * (a - (127.5/255.0)) / (127.5/255.0) * (0.5 - abs(b - (127.5/255.0)) / 1.0);
   } else {
    return b - b * (((127.5 / 255.0) - a) / (127.5/255.0)) * (0.5 - abs(b - (127.5 / 255.0)) /1.0);
   }
}

float exclusion(float a, float b) {
   return a + b - 2.0 * a * b / 1.0;
}

float pinlight(float a, float b) {
  if (b < (128.0 / 255.0)) {
      return min(a, 2.0 *b);
  }else {
      return max(a, 2.0 * (b - (128.0 / 255.0)));
  }
}

float phoenix(float a, float b) {
    return min(a, b) - max(a, b) + 1.0;
}

float reflects(float a, float b) {
   if (b == 1.0) {
       return b;
   } else {
      return min(1.0, (a * a / (1.0 - b)));
   }
}

float glow(float a, float b) {
    if (a == 1.0) {
       return a;
   } else {
      return min(1.0, (b * b / (1.0 - a)));
   }
}

//float and(float a, float b) {
//   return   a & b;
//}

//float or(float a, float b) {
//  return a | b;
//}

//float xor(float a, float b) {
//  return a ^ b;
//}

float vividlight(float a, float b) {
  if(a < (128.0 / 255.0)) {
      return BURN(2.0 * a, b);
  } else {
      return DODGE(2.0 * (a - (128.0 / 255.0)), b);
  }
}

float linearlight(float a, float b) {
  if(b < (128.0 / 255.0)){
     return b + 2.0 * a - 1.0;
  }else {
    return b + 2.0 * (a - (128.0 / 255.0));
  }
}

vec4 adjust_brightness(vec4 rgbdata, float percent) {
    float r = texture(low_brightness, vec2(rgbdata.r, 0.0), 0.0).r;
    float g = texture(low_brightness, vec2(rgbdata.g, 0.0), 0.0).g;
    float b = texture(low_brightness, vec2(rgbdata.b, 0.0), 0.0).b;

    vec4 res = vec4(r, g, b, 1.0);
    return vec4(mix(rgbdata.rgb, res.rgb, percent), 1.0);
}

vec4 getFromColor(vec2 uv) {
    float r, g, b, y, u, v, a;

    y = texture(text_y, vec2(uv.x,1.0 - uv.y), 0.0).r;
    u = texture(text_u, vec2(uv.x,1.0 - uv.y), 0.0).r;
    v = texture(text_v, vec2(uv.x,1.0 - uv.y), 0.0).r;
    float falphap = float(alphaPlaneExisted);
    a = (1.0 - falphap) + falphap * texture(text_a, uv.xy, 0.0).r;

    if(y < 0.4){
            y = 0.9 * y + 0.4 - 0.4 * 0.9;
    }

    //yuv2rgb
    {
        r = clamp(1.1643 * (y - 0.0625) + 1.5958  * (v - 0.5), 0.0, 1.0);
        g = clamp(1.1643 * (y - 0.0625) - 0.39173 * (u - 0.5) - 0.81290 * (v - 0.5), 0.0, 1.0);
        b = clamp(1.1643 * (y - 0.0625) + 2.017   * (u - 0.5), 0.0, 1.0);
    }

    vec4 rgbdata = vec4(r,g,b,1.0);
    return adjust_brightness(rgbdata, 0.2);
}

vec2 edge_mirror(vec2 uv){
  if(uv.y > 1.0 || uv.y < 0.0){
    uv.y = ceil(uv.y) - uv.y;
  }
  if(uv.x > 1.0 || uv.x < 0.0){
    uv.x = ceil(uv.x) - uv.x;
  }
  return uv;
}

vec4 colorSeparation(vec2 uv) {
    float offset = 0.0015;
    float r, g;
    if (verticalScreen) {
        r = clamp(getFromColor(edge_mirror(uv + vec2(0.0, offset))).r, 0.0, 1.0);
        g = clamp(getFromColor(edge_mirror(uv - vec2(0.0, offset))).g, 0.0, 1.0);
    } else {
        r = clamp(getFromColor(edge_mirror(uv + vec2(offset, 0.0))).r, 0.0, 1.0);
        g = clamp(getFromColor(edge_mirror(uv - vec2(offset, 0.0))).g, 0.0, 1.0);
    }
    float b = clamp(getFromColor(uv).b, 0.0, 1.0);

    vec4 rgbdata = vec4(r, g, b, 1.0);
    return rgbdata;
}

vec4 addVideoFilter1(vec4 color) {
    float r  = color.r;
    float g = color.g;
    float b = color.b;

    float quadx, quady, x, y;
    float bi = floor(b * 63.0);
    float mixratio = b * 63.0 - floor(b * 63.0);

    quady = floor(bi / 8.0);
    quadx = bi - quady * 8.0;
    x = quadx * 64.0 + clamp(r * 63.0, 1.0, 63.0);
    y = quady * 64.0 + clamp(g * 63.0, 1.0, 63.0);
    vec2 poss1 = vec2(x / 512.0, y / 512.0);

    bi = bi + 1.0;
    quady = floor(bi / 8.0);
    quadx = bi - quady * 8.0;
    x = quadx * 64.0 + clamp(r * 63.0, 1.0, 63.0);
    y = quady * 64.0 + clamp(g * 63.0, 1.0, 63.0);
    vec2 poss2 = vec2(x / 512.0, y / 512.0);

    vec4 color1 = texture(text_fliter, poss1, 0.0);
    vec4 color2 = texture(text_fliter, poss2, 0.0);

    r = mix(r, mix(color1.r, color2.r, mixratio), videoFilterIntensity);
    g = mix(g, mix(color1.g, color2.g, mixratio), videoFilterIntensity);
    b = mix(b, mix(color1.b, color2.b, mixratio), videoFilterIntensity);

    vec4 rgbdata = vec4(r,g,b,1.0);
    return rgbdata;
}

vec4 addVideoFilter(vec4 data_color)
{
    float r, g, b;
    r = (data_color.r * 255.0 / 17.0 + 0.5) / 16.0;
    g = (data_color.g * 255.0 / 17.0 + 0.5) / 16.0;
    b = (data_color.b * 255.0 / 17.0 + 0.5) / 16.0;
    return mix(data_color, texture(filter_rgb, vec3(r,g,b)), videoFilterIntensity);
}

// 3 x 3 guassian blur
vec4 guassianBlur3(vec2 uv) {
   float offset = 2.0 / width;
   vec4 Color = getFromColor(uv) * 0.2270270270 * 2.0;
   Color += getFromColor(uv + vec2(offset, 0.0)) * 0.3162162162;
   Color += getFromColor(uv - vec2(offset, 0.0)) * 0.3162162162;
   Color += getFromColor(uv + vec2(0.0, offset)) * 0.3162162162;
   Color += getFromColor(uv - vec2(0.0, offset)) * 0.3162162162;
   Color += getFromColor(uv + vec2(-offset, offset)) * 0.0702702703;
   Color += getFromColor(uv + vec2(offset, -offset)) * 0.0702702703;
   Color += getFromColor(uv + vec2(-offset, -offset)) * 0.0702702703;
   Color += getFromColor(uv + vec2(offset, offset)) * 0.0702702703;
   return clamp(Color / 2.0, 0.0, 1.0);
}

// 5 x 5 guassian blur
vec4 guassianBlur5(vec2 uv) {
   float offset = 2.0 / width;

   vec4 color = getFromColor(uv + vec2(-2.0*offset, -2.0*offset)) * 0.0030;
   color += getFromColor(uv + vec2(-1.0*offset, -2.0*offset)) * 0.0133;
   color += getFromColor(uv + vec2(0.0, -2.0*offset)) * 0.0219;
   color += getFromColor(uv + vec2(1.0*offset, -2.0*offset)) * 0.0133;
   color += getFromColor(uv + vec2(2.0*offset, -2.0*offset)) * 0.0030;

   color += getFromColor(uv + vec2(-2.0*offset, -1.0*offset)) * 0.0133;
   color += getFromColor(uv + vec2(-1.0*offset, -1.0*offset)) * 0.0596;
   color += getFromColor(uv + vec2(0.0, -1.0*offset)) * 0.0983;
   color += getFromColor(uv + vec2(1.0*offset, -1.0*offset)) * 0.0596;
   color += getFromColor(uv + vec2(2.0*offset, -1.0*offset)) * 0.0133;

   color += getFromColor(uv + vec2(-2.0*offset, 0.0)) * 0.0219;
   color += getFromColor(uv + vec2(-1.0*offset, 0.0)) * 0.0983;
   color += getFromColor(uv + vec2(0.0, 0.0)) * 0.1621;
   color += getFromColor(uv + vec2(1.0*offset, 0.0)) * 0.0983;
   color += getFromColor(uv + vec2(2.0*offset, 0.0)) * 0.0219;

   color += getFromColor(uv + vec2(-2.0*offset, 1.0*offset)) * 0.0133;
   color += getFromColor(uv + vec2(-1.0*offset, 1.0*offset)) * 0.0596;
   color += getFromColor(uv + vec2(0.0, 1.0*offset)) * 0.0983;
   color += getFromColor(uv + vec2(1.0*offset, 1.0*offset)) * 0.0596;
   color += getFromColor(uv + vec2(2.0*offset, 1.0*offset)) * 0.0133;

   color += getFromColor(uv + vec2(-2.0*offset, 2.0*offset)) * 0.0030;
   color += getFromColor(uv + vec2(-1.0*offset, 2.0*offset)) * 0.0133;
   color += getFromColor(uv + vec2(0.0, 2.0*offset)) * 0.0219;
   color += getFromColor(uv + vec2(1.0*offset, 2.0*offset)) * 0.0133;
   color += getFromColor(uv + vec2(2.0*offset, 2.0*offset)) * 0.0030;

   return clamp(color, 0.0, 1.0);
}

vec2 zoom(vec2 uv, float amount) {
    return 0.5 + ((uv - 0.5) * (1.0 / amount));
}

vec4 lightColor(vec2 uv) {
    vec2 pos = vec2(uv.x,1.0 - uv.y);
    if (isMoving) {
        pos = zoom(pos, 1.1);
    }

    if (verticalScreen) {
       pos = clamp(pos + vec2(moveLightPos.y, moveLightPos.x), 0.0, 1.0);
    } else {
       pos = clamp(pos + moveLightPos, 0.0, 1.0);
    }
    vec4 light_color = texture(text_light, pos, 0.0);

    return light_color;
}

vec4 noiseColor(vec2 uv) {
    vec2 pos = vec2(uv.x,1.0 - uv.y);
    vec4 noise_color = texture(noise_light, pos, 0.0);
    return noise_color;
}

vec4 blendSource(vec4 source, vec4 light) {
    float r, g, b;
    r = screen(source.r, light.r);
    g = screen(source.g, light.g);
    b = screen(source.b, light.b);
    return vec4(r, g, b, 1.0);
}

vec4 photoColor(vec2 uv) {
    vec4 rgbdata;

    if (needColorSeparation) {
        rgbdata = colorSeparation(uv);
    } else {
        rgbdata = getFromColor(uv);
    }

    if (needBlur) {
        vec4 blur = guassianBlur5(uv);
        rgbdata = mix(rgbdata, blur, 0.5);
    }

    if (extraVideoFilter) {
        rgbdata = addVideoFilter(rgbdata);
    }

    if (haveNoise) {
       rgbdata = blendSource(rgbdata, noiseColor(varTexcoord));
    }

    return blendSource(rgbdata, lightColor(varTexcoord));
}

void main (void)
{
    vec4 rgbdata = photoColor(varTexcoord);

    mat4 RGB2YUV = mat4( 0.257,  0.504,  0.098, 0.0625,
                            -0.148, -0.291,  0.439, 0.500,
                             0.439, -0.368, -0.071, 0.500,
                             0.000,  0.000,  0.000, 1.000);

    vec4 finalColor = clamp(rgbdata * RGB2YUV, vec4(0.0), vec4(1.0));

    #if __VERSION__ >= 140
        outputColor = finalColor;
    #else
        gl_FragColor = finalColor;
    #endif
}