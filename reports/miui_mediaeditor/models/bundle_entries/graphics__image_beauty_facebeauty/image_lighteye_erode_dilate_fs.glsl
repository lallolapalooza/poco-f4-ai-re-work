varying vec2 texc;

void main() {
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = texture2D(tex_origin,texc);
        return;
    }
    float coreSize = arrfloat_coreSize[face_idx - 1];
    coreSize /= 2.0;
  vec4 uvuv = vec4(texc, texc);
  vec4 offsetoffset = vec4(offset, offset);
  float retValue = 1.0;
  if (type < 0.5) {
    retValue = 0.0;
  }

  for (int i = 0; i < 4; i++) {
    vec4 sampleuv = uvuv + coreSize * arrfloat4_sampleDirs[i] * offsetoffset;
    float color = texture2D(tex_origin, sampleuv.xy).r;
    if (type < 0.5) {
      retValue = max(retValue, color);
    } else {
      retValue = min(retValue, color);
    }
    color = texture2D(tex_origin, sampleuv.zw).r;
    if (type < 0.5) {
      retValue = max(retValue, color);
    } else {
      retValue = min(retValue, color);
    }
  }
  gl_FragColor = vec4(retValue,texture2D(tex_origin,texc).gba);
}