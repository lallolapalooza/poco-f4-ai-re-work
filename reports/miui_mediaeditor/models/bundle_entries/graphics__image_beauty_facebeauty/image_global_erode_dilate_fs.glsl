varying vec2 texc;
// uniform vec4 arrfloat4_sampleDirs[4];

void main() {
  vec4 uvuv = vec4(texc, texc);
  vec4 offsetoffset = vec4(offset, offset);
  vec4 retValue = vec4(1.0);
  if (type < 0.5) {
    retValue = vec4(0.0);
  }
  vec4 minValue = vec4(1.0);
  for (int i = 0; i < 4; i++) {
    vec4 sampleuv = uvuv + coreSize / 2.0 * arrfloat4_sampleDirs[i] * offsetoffset;
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