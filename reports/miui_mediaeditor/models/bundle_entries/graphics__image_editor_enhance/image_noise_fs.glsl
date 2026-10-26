varying vec2 texc;
varying vec2 texf;

void main() {
  vec4 in_color = texture2D(tex_input, texc);
  vec3 result_color = in_color.rgb;
  float real_noise = texture2D(tex_noise, texf).r;
  result_color = mix(max(result_color + 2.0 * vec3(real_noise) - 1.0, 0.0), min(result_color + 2.0 * (vec3(real_noise) - 0.5), 1.0), step(0.5, vec3(real_noise)));
  vec4 mask_color = texture2D(tex_mask,texf);
	gl_FragColor = vec4(mix(in_color.rgb,result_color,clamp(mask_color.a,0.0,1.0) * noise_amount), in_color.a);
}