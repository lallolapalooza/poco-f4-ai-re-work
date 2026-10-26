//attribute vec2 Pquad
//uniform vec4 arrvec4_sampleDirs[4];
//uniform vec4 sampleLens;
varying vec2 uv;
varying vec4 sampleuv[12];
void main() 
{
	gl_Position=vec4(Pquad.xy*2.0-1.0,0.0,1.0);
	uv = Pquad.xy;
	int i = 0;
  vec4 uvuv= vec4(uv,uv);
  //vec4 sampleLens2 = vec4(1.5,3.0,4.5,20.0);
  for(i = 0; i < 4; i++){
    vec4 dirdir = arrfloat4_sampleDirs[i];
    sampleuv[i] = dirdir * sampleLens.x + uvuv;
    sampleuv[i+4] = dirdir * sampleLens.y + uvuv;
    sampleuv[i+8] = dirdir * sampleLens.z + uvuv;
  }
}
