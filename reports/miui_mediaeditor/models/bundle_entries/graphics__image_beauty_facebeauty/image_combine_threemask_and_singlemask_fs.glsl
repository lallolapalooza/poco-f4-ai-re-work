varying vec2 texc;

void main(){
    gl_FragColor = vec4(texture2D(tex_threemask,texc).rgb,texture2D(tex_singlemask,texc).r);
}