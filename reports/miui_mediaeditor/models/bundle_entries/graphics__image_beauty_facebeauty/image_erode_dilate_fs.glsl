/* varying vec2 texc;

void main()
{
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = texture2D(tex_origin,texc);
        return;
    }
    float coreSize = arrfloat_coreSize[face_idx - 1];
    float add = 1.0;
    if(coreSize == 1.0){
        add = 0.5;
    }
    if(type < 0.5)
    {
        vec4 maxValue=vec4(0.0);        
        float halfCoreSize=coreSize / -2.0;
        for(float y=0.0;y<coreSize;y+=add)
        {
            for(float x=0.0;x<coreSize;x+=add)
            {
                vec4 color=texture2D(tex_origin,texc+vec2((halfCoreSize+x)*offset.x,(halfCoreSize+y)*offset.y));
                maxValue=max(maxValue,color);
            }
        }
    
        gl_FragColor=maxValue;
    }
    else
    {
        vec4 minValue=vec4(1.0);
        float halfCoreSize=coreSize / -2.0;
        for(float y=0.0;y<coreSize;y+=add)
        {
            for(float x=0.0;x<coreSize;x+=add)
            {
                vec4 color=texture2D(tex_origin,texc+vec2((halfCoreSize+x)*offset.x,(halfCoreSize+y)*offset.y));
                minValue=min(minValue,color);
            }
        }
        
        gl_FragColor=minValue;
    }
} */

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