varying vec2 texc;
varying vec4 sampleuv[5];
void main() {
    texc = vec2(Pquad.x, Pquad.y);
    gl_Position = vec4(Pquad.xy * 2.0 - 1.0, 0.0, 1.0);
    int i = 0;

    float rate = 0.2;
    for (i = 0; i < 5; i++) {
        vec4 sampleuv_i = sampleDirs * rate * float(i + 1);
        if(seg_num < 0.5){
            sampleuv[i] = sampleuv_i * 0.5;
        }else{
            sampleuv[i] = sampleDirs * rate * 2.5 + sampleuv_i * 0.5;
        }
    }  
}