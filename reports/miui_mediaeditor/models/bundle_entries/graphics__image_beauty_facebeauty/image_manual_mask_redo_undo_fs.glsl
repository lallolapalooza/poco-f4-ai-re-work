varying vec2 texc;

void main(){
    vec3 nowcolor = texture2D(tex_new,texc).rgb;
    vec3 incolor = texture2D(tex_in,texc).rgb;
    if(type < 0.5) {
        nowcolor += incolor;
    }
    else{
        nowcolor -= incolor;
    }
    //nowcolor = clamp(nowcolor,0.0,1.0);
    gl_FragColor = vec4(nowcolor,1.0);
}