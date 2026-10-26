varying vec2 texc;
void main() {
  vec3 outcolor = texture2D(tex_ori,texc).rgb;
  if (texc.x >= offset.x && texc.x <= offset.x + wh.x && texc.y >= offset.y &&
      texc.y <= offset.y + wh.y) {
    vec2 noav_texv = texc - offset;
    noav_texv.x /= wh.x;
    noav_texv.y /= wh.y;

    vec3 noacen_diff = texture2D(tex_noacen_diff,noav_texv).rgb;
    outcolor = clamp(outcolor + noacen_diff - 0.5,0.0,1.0);
  }
  gl_FragColor = vec4(outcolor,1.0);
}