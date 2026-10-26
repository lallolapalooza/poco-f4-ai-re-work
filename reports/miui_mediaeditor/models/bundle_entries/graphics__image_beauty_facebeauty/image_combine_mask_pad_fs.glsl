varying vec2 texc;
void main() {
  //vec4 outcolor = vec4(texture2D(tex_ori,texc).rgb,0.0);
  /* if (texc.x >= offset.x && texc.x <= offset.x + wh.x && texc.y >= offset.y &&
      texc.y <= offset.y + wh.y) {
    vec2 noav_texv = texc - offset;
    noav_texv.x /= wh.x;
    noav_texv.y /= wh.y;

    float alpha = texture2D(tex_mask,noav_texv).r;
    outcolor.a = alpha;
  } */
  gl_FragColor = vec4(texture2D(tex_ori,texc).rgb,texture2D(tex_mask,texc).r);
}