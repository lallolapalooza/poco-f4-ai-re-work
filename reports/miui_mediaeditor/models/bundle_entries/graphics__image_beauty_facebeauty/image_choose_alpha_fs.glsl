varying vec2 texc;
void main(){
	gl_FragColor = vec4(texture2D(tex_pic,texc).a);
}