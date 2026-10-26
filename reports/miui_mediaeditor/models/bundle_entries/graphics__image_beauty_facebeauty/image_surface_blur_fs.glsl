/*varying vec2 texc;

void main()
{
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = vec4(texture2D(tex_origin,texc).rgb, 0.0);
        return;
    }
    float radius = arrfloat_radius[face_idx - 1];
    radius *= 1.5;
    //vec2 offset = arrvec2_offset[face_idx - 1];
    vec3 sum = vec3(0.), sumw = vec3(0.), k = vec3(0.);
 
    vec3 color0 = texture2D(tex_origin, texc).rgb * 255.;

    float pi = 3.1415926;
    float r_temp = radius / 8.0;
    for(float i = 1.0; i <= 3.0; i += 1.0){
        r_temp *= 2.0;
        for(float j = 0.0; j < 8.0; j += 1.0){
            float x =  r_temp * cos(2.0 * pi / 8.0 * j + pi / 16.0 * i) * offset.x +texc.x;
            float y =  r_temp * sin(2.0 * pi / 8.0 * j + pi / 16.0 * i) * offset.y +texc.y;
            vec2 pos = vec2(x, y);
            vec3 color1 = texture2D(tex_origin, pos).rgb * 255.;
            
            float weight = (color1.g - color0.g) / 255.0;
            weight = 1.0 - min(weight * weight * threshold / 4.0, 1.0);
            k = vec3(weight);
            sum += k * color1;
            sumw += k;
        }
    }
    sum += color0;
    sumw += 1.0;

    vec3 col = mix(color0, clamp(sum / sumw, 0., 255.), step(0., sumw));
    float alpha = texture2D(tex_averblur_mask,texc).r;
    col = clamp(col,0.0,255.0);
    float highlight_alpha = texture2D(tex_manual_mask_blur,texc).r;
    if(highlight_alpha > 0.0){
        float b = color0.b / 255.0;
        col = mix(col,color0,b * highlight_alpha);
    } 
    float shadow_alpha = texture2D(tex_manual_mask_blur,texc).b;
    if(shadow_alpha > 0.0){
        float r = color0.r / 255.0;
        col = mix(col,color0,(1.0 - r)*shadow_alpha);
    }

    gl_FragColor = vec4(clamp(col / 255.,0.0,1.0),alpha);
}*/

varying vec2 texc;
varying vec4 sampleuv[12];

vec3 hsv2rgb(vec3 c) {
    const vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

vec3 rgb2hsv(vec3 c) {
    const vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));

    float d = q.x - min(q.w, q.y);
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + 0.001)), d / (q.x + 0.001), q.x);
}

vec3 tmp(vec3 colorrbg,float contrast,float brightness){
    float PI = 3.1415926;
	float B = brightness / 255.0;
	float c = contrast / 255.0;
	float k = tan((45.0 + 44.0 * c) / 180.0 * PI);

	vec3 tempolor = ((colorrbg*255.0 - 127.5 * (1.0 - B)) * k + 127.5 * (1.0 + B)) / 255.0;
    return tempolor;
}

vec4 SurfaceBlurCumulate(vec4 sum, float yFactor, float centerg, vec4 suv) {
  vec3 color = texture2D(tex_origin, suv.xy).rgb;
  float weight = color.g - centerg;
  weight = 1.0 - min(weight * weight * yFactor, 1.0);
  weight *= clamp(texture2D(tex_averblur_mask,suv.xy).r,0.0,1.0);
  sum.xyz += color * weight;
  sum.w += weight;
  color = texture2D(tex_origin, suv.zw).rgb;
  weight = color.g - centerg;
  weight = 1.0 - min(weight * weight * yFactor, 1.0);
  weight *= clamp(texture2D(tex_averblur_mask,suv.zw).r,0.0,1.0);
  sum.xyz += color * weight;
  sum.w += weight;
  return sum;
}

void main() {
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = vec4(texture2D(tex_origin,texc).rgb, 0.0);
        return;
    }
    float radius = arrfloat_radius[face_idx - 1];
    radius *= 1.5;
  vec4 color0 = texture2D(tex_origin, texc);
  vec4 sum = vec4(0.0);
  float yFacetor = threshold / 4.0;
  for (int i = 0; i < 12; i++) {
    vec4 temp_sampleuv = sampleuv[i] * radius + vec4(texc,texc);    
    sum = SurfaceBlurCumulate(sum, yFacetor, color0.g, temp_sampleuv);
  }
  sum.xyz += color0.xyz;
  sum.w += 1.0;
  sum.xyz /= sum.w;
  float alpha = texture2D(tex_averblur_mask,texc).r;

  float input_light = dot(color0.xyz, vec3(0.25, 0.5, 0.25));
  float blur_light = dot(sum.xyz, vec3(0.25, 0.5, 0.25));
  float threshold_light = 10.0 / 255.0;
  float scale = 5.0;
  float diff = input_light - blur_light - threshold_light;
  if (diff > 0.0) {
    alpha = max(0.0, alpha * (1.0 - diff * scale));
  }

  float highlight_alpha = texture2D(tex_manual_mask_blur,texc).r;
  if(highlight_alpha > 0.0){
    float b = color0.b;
    sum = mix(sum,color0,b * highlight_alpha * alpha);
  } 
  float shadow_alpha = texture2D(tex_manual_mask_blur,texc).b;
  if(shadow_alpha > 0.0){
    float r = color0.r;
    sum = mix(sum,color0,(1.0 - r)*shadow_alpha * alpha);
  }

  /* vec3 a = rgb2hsv(sum.xyz);
  vec3 m = a + vec3(0.0,-0.05,0.0);
  gl_FragColor = vec4(tmp(hsv2rgb(m).xyz,33.0,-2.0),alpha); */
  gl_FragColor = vec4(sum.xyz, alpha * alpha); 
}