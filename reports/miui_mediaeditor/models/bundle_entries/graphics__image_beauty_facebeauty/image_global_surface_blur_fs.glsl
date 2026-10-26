
varying vec2 texc;
varying vec4 sampleuv[12];

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
  vec4 color0 = texture2D(tex_origin, texc);
  vec4 sum = vec4(0.0);
  float yFacetor = threshold / 4.0;
  for (int i = 0; i < 12; i++) {
    sum = SurfaceBlurCumulate(sum, yFacetor, color0.g, sampleuv[i]);
  }
  sum.xyz += color0.xyz;
  sum.w += 1.0;
  sum.xyz /= sum.w;
  float alpha = texture2D(tex_averblur_mask, texc).r;
  float input_light = dot(color0.xyz, vec3(0.25, 0.5, 0.25));
  float blur_light = dot(sum.xyz, vec3(0.25, 0.5, 0.25));
  float threshold_light = 10.0 / 255.0;
  float scale = 5.0;
  float diff = input_light - blur_light - threshold_light;
  if (diff > 0.0) {
    alpha = max(0.0, alpha * (1.0 - diff * scale));
  }
  gl_FragColor = vec4(sum.xyz, alpha * alpha);
}