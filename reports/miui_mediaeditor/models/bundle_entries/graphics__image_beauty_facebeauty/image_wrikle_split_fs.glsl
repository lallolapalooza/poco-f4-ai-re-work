
varying vec2 texc;
varying vec2 texf;

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);

  vec3 outcolor = origin_color.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 wrikle_texv = texf - offset;
    wrikle_texv.x /= wh.x;
    wrikle_texv.y /= wh.y;
    vec4 wrikle_detail_color = texture2D(tex_wrikle, wrikle_texv);
    vec3 wrikle_color = clamp(wrikle_detail_color.rgb - 0.5 + origin_color.rgb,0.0,1.0);
    outcolor = mix(origin_color.rgb, wrikle_color.rgb, wrikle_rate * wrikle_detail_color.a);
  }

  gl_FragColor = vec4(outcolor, origin_color.a);
}