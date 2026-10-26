varying vec2 texc;
void main() {
  float region_color = texture2D(tex_region,texc).r;
  float stroke_color = texture2D(tex_stroke,texc).r;
  vec4 origin_color = texture2D(tex_origin,texc);
  float alpha = clamp(region_color + stroke_color,0.0,1.0);
  gl_FragColor = vec4(mix(origin_color.rgb,vec3(1.0,0.0,0.0),alpha),alpha);
}