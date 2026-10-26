varying vec2 texc;

vec2 random(vec2 cord) {
  float temp = sin(dot(cord, vec2(12.9898, 78.233))) * 43758.5453;
  return fract(vec2(1.3453, 1.3647) * temp) * 2.0 - 1.0;
}
float gray(vec3 color) {
  return dot(color, vec3(0.298839, 0.586811, 0.11435));
}

void main() {
  vec4 in_color = texture2D(tex_input, texc);
  vec3 result_color = in_color.rgb;
  vec2 textureScale_big = textureScale * 1.2;
  vec2 texc_noise = texc * textureScale_big / noiseTextureSize;
  texc_noise = texc_noise + random(texc_noise).xy / noiseTextureSize / textureScale_big / 3.0;
  vec4 noise_color = texture2D(tex_noise, texc_noise);
  vec2 real_noise_t = vec2(mix(noise_color.g, noise_color.r, noise_size), mix(noise_color.a, noise_color.b, noise_size));
  float real_noise = mix(real_noise_t.x, real_noise_t.y, noise_roughness);
  float o_gray = gray(result_color);
  float highlight_rate = (1.0 - o_gray * noise_highlight) * (1.0 - pow(1.0 - o_gray, 4.0)); 
  real_noise = mix(0.5, real_noise, highlight_rate);
  gl_FragColor = vec4(real_noise);
}