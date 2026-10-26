varying vec2 texc;
void main(){
	if(is_android > 0.5){
		vec2 texv = vec2(texc.x, 1.0 - texc.y);
		gl_FragColor = texture2D(tex_result,texv);
	}
	else{
		gl_FragColor = texture2D(tex_result,texc);
	}
}