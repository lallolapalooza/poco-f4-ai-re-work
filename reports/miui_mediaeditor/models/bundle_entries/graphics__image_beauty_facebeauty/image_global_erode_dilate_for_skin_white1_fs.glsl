varying vec2 texc;

void main() {
  vec4 uvuv = vec4(texc, texc);
  vec4 offsetoffset = vec4(offset, offset);
 
  int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
  vec2 dir_ver;
  if(face_idx == 0 || face_idx > int(face_num))
  {
    dir_ver = vec2(0.0,1.0);
  }else
  {
    dir_ver = arrfloat2_dir_ver[face_idx - 1];
  }
  //gl_FragColor = texture2D(tex_origin, texc);
  //return;
  if(type < 0.5){
    vec4 retValue_max = texture2D(tex_origin, texc);
    vec4 sampleuv = uvuv + coreSize * 1.0 * vec4(dir_ver,dir_ver) * offsetoffset;
    vec4 color = texture2D(tex_origin, sampleuv.xy);
    retValue_max = max(retValue_max, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_max = max(retValue_max, color);

    sampleuv = uvuv + coreSize * 1.0 * vec4(dir_ver,dir_ver) * offsetoffset * -1.0;
    color = texture2D(tex_origin, sampleuv.xy);
    retValue_max = max(retValue_max, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_max = max(retValue_max, color);
    gl_FragColor = vec4(texture2D(tex_origin, texc).r,retValue_max.gb,1.0); 
  }
  else{
    vec4 retValue_min = texture2D(tex_origin, texc);
    vec4 sampleuv = uvuv + coreSize * 1.5 * vec4(dir_ver,dir_ver) * offsetoffset;
    vec4 color = texture2D(tex_origin, sampleuv.xy);
    retValue_min = min(retValue_min, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_min = min(retValue_min, color);

    sampleuv = uvuv + coreSize * 1.5 * vec4(dir_ver,dir_ver) * offsetoffset * -1.0;
    color = texture2D(tex_origin, sampleuv.xy);
    retValue_min = min(retValue_min, color);
    color = texture2D(tex_origin, sampleuv.zw);
    retValue_min = min(retValue_min, color);
    gl_FragColor = vec4(texture2D(tex_origin, texc).r,retValue_min.gb,1.0); 
  }
}