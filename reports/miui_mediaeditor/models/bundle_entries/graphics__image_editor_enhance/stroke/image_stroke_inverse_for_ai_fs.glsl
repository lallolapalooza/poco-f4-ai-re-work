varying vec2 texc;
void main() {
  vec4 mask_color = texture2D(tex_mask,texc);
  vec4 region_color = texture2D(tex_region,texc);
  if(type < 0.5){
    gl_FragColor = vec4(mask_color.a - region_color.r);
  }else{
    gl_FragColor = mask_color - region_color;
  }

}