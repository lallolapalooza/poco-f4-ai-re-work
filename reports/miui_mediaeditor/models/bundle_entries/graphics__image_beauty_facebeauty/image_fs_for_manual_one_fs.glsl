varying vec2 texc;
void main(){
	float r = texture2D(tex_result,texc).r;
	if(r > 0.0001){
		gl_FragColor = vec4(1.0,1.0,1.0,1.0);
	}
	else{
		gl_FragColor = vec4(0.0,0.0,0.0,1.0);
	}
}