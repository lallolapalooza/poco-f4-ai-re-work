
varying vec2 texc;
varying vec2 texf;

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);

  vec3 outcolor = origin_color.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 noacen_texv = texf - offset;
    noacen_texv.x /= wh.x;
    noacen_texv.y /= wh.y;
    vec4 noacen_detail_color = texture2D(tex_noacen, noacen_texv);
    vec3 noacen_color = clamp(noacen_detail_color.rgb - 0.5 + origin_color.rgb,0.0,1.0);
    outcolor = mix(origin_color.rgb, noacen_color.rgb, noacen_rate * noacen_detail_color.a);
  }

  gl_FragColor = vec4(outcolor, origin_color.a);
}