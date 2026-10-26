varying vec2 texc;

void main() {
  vec4 uvuv = vec4(texc, texc);
  vec4 offsetoffset = vec4(offset, offset);
  vec4 retValue = texture2D(tex_origin,texc);
  for (int i = 0; i < 4; i++) {
    vec4 sampleuv = uvuv + coreSize * arrfloat4_sampleDirs[i] * offsetoffset;
    vec4 color = texture2D(tex_origin, sampleuv.xy);
    if (type < 0.5) {
      retValue = max(retValue, color);
    } else {
      retValue = min(retValue, color);
    }
    color = texture2D(tex_origin, sampleuv.zw);
    if (type < 0.5) {
      retValue = max(retValue, color);
    } else {
      retValue = min(retValue, color);
    }
  }
  gl_FragColor = retValue;
}