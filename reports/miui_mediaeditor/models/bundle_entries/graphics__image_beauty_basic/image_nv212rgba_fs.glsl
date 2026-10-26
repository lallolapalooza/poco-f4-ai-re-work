// uniform u_offset
varying vec2 texc;
void main() {
  vec2 st = texc;
  vec2 st_int = floor(st * dims.zw);
  vec2 st_y = floor(st_int * vec2(0.25, 1.0));
  vec2 st_uv = floor(st_int * vec2(0.25, 0.5)) + vec2(0.0, dims.w);
  float y_channel = floor(st_int.x - st_y.x * 4.0);
  float uv_channel = floor(floor(st_int.x * 0.5) - st_uv.x * 2.0);
  vec4 y_mask =
      vec4(y_channel == 0.0 ? 1.0 : 0.0, y_channel == 1.0 ? 1.0 : 0.0,
           y_channel == 2.0 ? 1.0 : 0.0, y_channel == 3.0 ? 1.0 : 0.0);
  vec4 v_mask = vec4(uv_channel == 0.0 ? 1.0 : 0.0, 0.0,
                     uv_channel == 1.0 ? 1.0 : 0.0, 0.0);
  float Y = dot(texture2D(tex_yuv, min((st_y + vec2(0.5, 0.5)) * dims.xy,
                                       vec2(1.0, 0.666))),
                y_mask);
  vec4 fetch_uv = texture2D(tex_yuv, (st_uv + vec2(0.5, 0.5)) * dims.xy);
  float U = dot(fetch_uv, v_mask.yxwz) - 0.5;
  float V = dot(fetch_uv, v_mask) - 0.5;
  vec3 YUV = vec3(Y, U, V);
  gl_FragColor =
      vec4(dot(YUV, vec3(1.0, 0.0, 1.402)), dot(YUV, vec3(1.0, -0.344, -0.714)),
           dot(YUV, vec3(1.0, 1.772, 0.0)), 1.0);
}