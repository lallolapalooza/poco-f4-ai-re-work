varying vec2 texc;
varying vec4 sampleuv[12];
void main() {
  int MAX_FACE = 5;
  texc = vec2(Pquad.x, Pquad.y);
  gl_Position = vec4(Pquad.xy * 2.0 - 1.0, 0.0, 1.0);
  int i = 0;
  float cos15 = 0.258819;
  float sin15 = 0.965926;
  mat4 rotm15 = mat4(cos15, sin15, 0.0, 0.0, -sin15, cos15, 0.0, 0.0, 0.0,
                   0.0, cos15, sin15, 0.0, 0.0, -sin15, cos15);
  float cos30 = 0.86602;
  float sin30 = 0.5;
  mat4 rotm30 = mat4(cos30, sin30, 0.0, 0.0, -sin30, cos30, 0.0, 0.0, 0.0,
                   0.0, cos30, sin30, 0.0, 0.0, -sin30, cos30);  
  
  vec4 sampleratio = vec4(0.25, 0.5, 1.0, 20.0);
  for (i = 0; i < 4; i++) {
    vec4 dirdir = arrfloat4_sampleDirs[i];
    sampleuv[i] = dirdir * sampleratio.x;
    sampleuv[i + 4] = rotm15 * dirdir * sampleratio.y;
    sampleuv[i + 8] = rotm30 * dirdir * sampleratio.z;   
  }  
}