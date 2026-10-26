varying vec2 texc;

void main() {
  vec4 origin_color = texture2D(tex_origin, texc);
  vec4 raw_color = texture2D(tex_raw, texc);

  vec3 outcolor = origin_color.rgb;
  if (texc.x >= offset.x && texc.x <= offset.x + wh.x && texc.y >= offset.y &&
      texc.y <= offset.y + wh.y) {
    vec2 aver_texv = texc - offset;
    aver_texv.x /= wh.x;
    aver_texv.y /= wh.y;
    float alpha = texture2D(tex_mask,aver_texv).b;
    vec3 aver_color = texture2D(tex_aver, aver_texv).rgb;
    vec3 noacen_color = texture2D(tex_noacen, aver_texv).rgb - 0.5 + raw_color.rgb;
    noacen_color = clamp(noacen_color,0.0,1.0);
    aver_color = clamp(noacen_color + aver_color - 0.5,0.0,1.0);
    float alpha_source = alpha * aver_rate;

    outcolor = origin_color.rgb * (1.0 - alpha_source) + aver_color.rgb * alpha_source;
  }

  gl_FragColor = vec4(outcolor, 1.0);
}