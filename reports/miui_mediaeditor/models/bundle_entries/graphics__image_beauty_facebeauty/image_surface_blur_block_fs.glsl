/*varying vec2 texc;
varying vec2 texf;

void main()
{
    int face_idx = int(texture2D(tex_label, texf).b * 255.0 + 0.1);
    float radius;
    if(face_idx == 0 || face_idx > int(face_num))
    {
        radius = default_radius;
    }
    else{
        radius = arrfloat_radius[face_idx - 1];
    }

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
    gl_FragColor = vec4(clamp(col / 255.,0.0,1.0),texture2D(tex_averblur_mask,texf).a);
}*/

varying vec2 texc;
varying vec2 texf;
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

float check_is_face(vec2 texf_check){
    int background = 0, skin = 1, nose = 2, eye_g = 3, l_eye = 4, r_eye = 5, l_brow = 6,
    r_brow = 7, l_ear = 8, r_ear = 9, mouth = 10, u_lip = 11, l_lip = 12, hair = 13,
    hat = 14, ear_r = 15, neck_l = 16, neck = 17, cloth = 18, forehead = 19, chin = 20;
    int c_mask_ = int(texture2D(tex_label, texf_check).r * 255.0 + 0.1);
    float c_mask = float(c_mask_);
    if (c_mask == arrfloat_mask_index[skin] ||
        c_mask == arrfloat_mask_index[nose]){
           return 1.0;
    }
    else{
        return 0.0;
    }

}

vec4 SurfaceBlurCumulate(vec4 sum, float yFactor, float centerg, vec4 suv,vec4 suvf,float is_face) {
  vec3 color = texture2D(tex_origin, suv.xy).rgb;
  float weight = color.g - centerg;
  weight = 1.0 - min(weight * weight * yFactor, 1.0);
  if(check_is_face(suvf.xy) == is_face){
    weight *= clamp(sqrt(texture2D(tex_averblur_mask,suvf.xy).a),0.0,1.0);
  }
  else{
    weight *= 0.0;
  }
  sum.xyz += color * weight;
  sum.w += weight;
  color = texture2D(tex_origin, suv.zw).rgb;
  weight = color.g - centerg;
  weight = 1.0 - min(weight * weight * yFactor, 1.0);
  if(check_is_face(suvf.xy) == is_face){
    weight *= clamp(sqrt(texture2D(tex_averblur_mask,suvf.zw).a),0.0,1.0);
  }
  else{
    weight *= 0.0;
  }
  sum.xyz += color * weight;
  sum.w += weight;
  return sum;
}

void main() {
    int face_idx = int(texture2D(tex_label, texf).b * 255.0 + 0.1);
    float radius = 0.0;
    float is_face = check_is_face(texf);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        radius = default_radius;
    }
    else{
        radius = arrfloat_radius[face_idx - 1];
    }
    radius *= 1.5;
  vec4 color0 = texture2D(tex_origin, texc);
  vec4 sum = vec4(0.0);
  float yFacetor = threshold / 4.0;
  for (int i = 0; i < 12; i++) {
    vec4 temp_sampleuv = sampleuv[i] * radius + vec4(texc,texc);
    vec2 tempf1 = (temp_sampleuv.xy * b_dim + b_offset) / f_dim;
    vec2 tempf2 = (temp_sampleuv.zw * b_dim + b_offset) / f_dim;
    vec4 temp_sampleuvf = vec4(tempf1,tempf2);   
    sum = SurfaceBlurCumulate(sum, yFacetor, color0.g, temp_sampleuv,temp_sampleuvf,is_face);
  }
  sum.xyz += color0.xyz;
  sum.w += 1.0;
  sum.xyz /= sum.w;
  float alpha = texture2D(tex_averblur_mask,texf).a;

  float input_light = dot(color0.xyz, vec3(0.25, 0.5, 0.25));
  float blur_light = dot(sum.xyz, vec3(0.25, 0.5, 0.25));
  float threshold_light = 10.0 / 255.0;
  float scale = 5.0;
  float diff = input_light - blur_light - threshold_light;
  if (diff > 0.0) {
    alpha = max(0.0, alpha * (1.0 - diff * scale));
  }

  float highlight_alpha = texture2D(tex_manual_mask_blur,texf).r;
  if(highlight_alpha > 0.0){
    float b = color0.b;
    sum = mix(sum,color0,b * highlight_alpha * alpha);
  } 
  float shadow_alpha = texture2D(tex_manual_mask_blur,texf).b;
  if(shadow_alpha > 0.0){
    float r = color0.r;
    sum = mix(sum,color0,(1.0 - r)*shadow_alpha * alpha);
  }

  /* vec3 a = rgb2hsv(sum.xyz);
  vec3 m = a + vec3(0.0,0.0,0.0); */
  gl_FragColor = vec4(sum.xyz, alpha);
}