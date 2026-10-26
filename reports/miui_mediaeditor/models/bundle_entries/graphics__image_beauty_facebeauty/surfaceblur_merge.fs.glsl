//uniform sampler2D tex_origin;
//uniform sampler2D tex_blur1;
//uniform sampler2D tex_blur2;
//uniform sampler2D tex_mask;
//uniform float smoothIntensity;
varying vec2 uv;
varying vec2 uvMask;

void main(){
  vec4 color = texture2D(tex_origin,uv).rgba;
  vec3 blur1 = texture2D(tex_blur1,uv).rgb;
  vec3 blur2 = texture2D(tex_blur2,uv).rgb;
  float skin_mask = texture2D(tex_mask,uvMask).a;

  vec3 diff = (color.rgb - blur1) / 2.0 + 0.5;
  vec3 even = clamp(blur2 + 2.0 * diff - 1.0, vec3(0.0),vec3(1.0));
  even = mix(color.rgb, even, skin_mask);

  gl_FragColor = vec4(even.xyz,color.a);
}