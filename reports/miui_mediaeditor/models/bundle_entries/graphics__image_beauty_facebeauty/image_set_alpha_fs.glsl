varying vec2 texc;
void main(){
	gl_FragColor = vec4(texture2D(tex_src,texc).rgb,texture2D(tex_alpha,texc).r);
}