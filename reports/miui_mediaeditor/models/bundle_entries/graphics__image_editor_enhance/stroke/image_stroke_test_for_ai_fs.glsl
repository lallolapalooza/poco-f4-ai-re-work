varying vec2 texc;
void main() {
  vec4 mask_color = texture2D(tex_mask,texc);
  vec4 region_color = texture2D(tex_region,texc);
  gl_FragColor = 1.0 - clamp(mask_color + region_color,0.0,1.0);
}