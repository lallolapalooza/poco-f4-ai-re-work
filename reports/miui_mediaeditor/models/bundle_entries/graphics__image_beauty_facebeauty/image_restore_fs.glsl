varying vec2 texc;
varying vec2 texf;

void main() {

  vec4 origin_color = texture2D(tex_origin, texc);

  vec3 outcolor = origin_color.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 restore_texv = texf - offset;
    restore_texv.x /= wh.x;
    restore_texv.y /= wh.y;
    vec4 restore_color = texture2D(tex_restore, restore_texv);
    outcolor = clamp(restore_color.rgb - 0.5 + origin_color.rgb,0.0,1.0);
    outcolor = mix(origin_color.rgb,outcolor,restore_rate * restore_color.a);
  }
  gl_FragColor = vec4(outcolor, origin_color.a);
}