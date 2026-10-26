//uniform sampler2D tex_input;
//uniform float yFactor;
varying vec2 uv;
varying vec4 sampleuv[12];

vec4 SurfaceBlurCumulate(vec4 sum, float centerg, vec4 suv){
  vec3 color = texture2D(tex_input,suv.xy).rgb;
  float weight = color.g - centerg;
  //weight = 1.0 - min(weight * yFactor, 1.0);
  weight = 1.0 - min(weight * weight * yFactor, 1.0);
  sum.xyz += color * weight;
  sum.w += weight;
  color = texture2D(tex_input,suv.zw).rgb;
  weight = color.g - centerg;
  weight = 1.0 - min(weight * weight * yFactor, 1.0);
  //weight = 1.0 - min(weight * yFactor, 1.0);
  sum.xyz += color * weight;
  sum.w += weight;
  return sum;
}

void main(){
  vec3 color = texture2D(tex_input,uv).rgb;
  int i = 0;
  vec4 sum = vec4(0.0);
  for(i = 0; i < 12; i++){
    sum = SurfaceBlurCumulate(sum,color.g,sampleuv[i]);
  }
  sum.xyz += color;
  sum.w += 1.0;
  sum.xyz /= sum.w;
  gl_FragColor = vec4(sum.xyz,1.0);
}