varying vec2 texc;
// uniform vec4 arrfloat4_sampleDirs[4];

void main() {
  vec4 uvuv = vec4(texc, texc);
  vec4 offsetoffset = vec4(offset, offset);
  /* vec4 retValue_min = vec4(1.0);
  vec4 retValue_max_body = vec4(0.0);
  vec4 retValue_max_forehead = vec4(0.0);
  vec4 retValue_max_forehead_skin = vec4(0.0);
  for (int i = 0; i < 4; i++) {
    vec4 sampleuv = uvuv + coreSize * 0.5 * arrfloat4_sampleDirs[i] * offsetoffset;
    vec4 color = texture2D(tex_origin, sampleuv.xy);
    retValue_min = min(retValue_min, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_min = min(retValue_min, color);

    sampleuv = uvuv + coreSize * 1.5 * arrfloat4_sampleDirs[i] * offsetoffset;
    color = texture2D(tex_origin, sampleuv.xy);
    retValue_max_body = max(retValue_max_body, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_max_body = max(retValue_max_body, color);

    sampleuv = uvuv + coreSize * 2.0 * 4.5 * arrfloat4_sampleDirs[i] * offsetoffset;
    color = texture2D(tex_origin, sampleuv.xy);
    retValue_max_forehead = max(retValue_max_forehead, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_max_forehead = max(retValue_max_forehead, color);

    sampleuv = uvuv + coreSize * 2.0 * arrfloat4_sampleDirs[i] * offsetoffset;
    color = texture2D(tex_origin, sampleuv.xy);
    retValue_max_forehead_skin = max(retValue_max_forehead_skin, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_max_forehead_skin = max(retValue_max_forehead_skin, color);
  }
  gl_FragColor = vec4(retValue_min.r,retValue_max_body.g,retValue_max_forehead.b,retValue_max_forehead_skin.a); */
  
  vec4 retValue_min = texture2D(tex_origin, texc);
  //vec4 retValue_min_face = retValue_min;
  for (int i = 0; i < 4; i++) {
    vec4 sampleuv = uvuv + coreSize * 0.5 * arrfloat4_sampleDirs[i] * offsetoffset;
    vec4 color = texture2D(tex_origin, sampleuv.xy);
    retValue_min = min(retValue_min, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_min = min(retValue_min, color);

    /* sampleuv = uvuv + coreSize * 1.0 * arrfloat4_sampleDirs[i] * offsetoffset;
    color = texture2D(tex_origin, sampleuv.xy);
    retValue_min_face = min(retValue_min_face, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_min_face = min(retValue_min_face, color); */
  }
  //gl_FragColor = vec4(retValue_min.r,retValue_min_face.gb,1.0); 
  gl_FragColor = vec4(retValue_min.rgb,1.0); 
}