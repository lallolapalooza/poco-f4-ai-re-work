varying vec2 texc;
varying vec2 texf;
varying vec4 sampleuv[5];
void main() {
    texc = vec2(Pquad.x, Pquad.y);
    texf = (texc * b_dim + b_offset) / f_dim;
    gl_Position = vec4(Pquad.xy * 2.0 - 1.0, 0.0, 1.0);
    int i = 0;

    float rate = 0.2;
    for (i = 0; i < 5; i++) {
        sampleuv[i] = sampleDirs * rate * float(i + 1);
    }  
}