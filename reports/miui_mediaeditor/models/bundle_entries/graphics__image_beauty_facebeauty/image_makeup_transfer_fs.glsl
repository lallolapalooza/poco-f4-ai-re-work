varying vec2 texc;
varying vec2 texf;

void main() {

  vec4 origin_color = texture2D(tex_origin, texc);

  vec3 outcolor = origin_color.rgb;
  if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
      texf.y <= offset.y + wh.y) {
    vec2 makeup_texv = texf - offset;
    makeup_texv.x /= wh.x;
    makeup_texv.y /= wh.y;
    vec4 makeup_result =  texture2D(tex_makeup_transfer, makeup_texv);
    makeup_result.rgb += origin_color.rgb - 0.5;
    outcolor = mix(origin_color.rgb,makeup_result.rgb,makeup_rate * makeup_result.a);
  }

  gl_FragColor = vec4(outcolor, origin_color.a);
}