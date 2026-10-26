varying vec2 texc;
varying vec2 texf;
void main(){
	// if(is_android > 0.5){
	// 	gl_FragColor = vec4(texture2D(tex_result,texf).rgb,0.0);
	// }
	// else{
	// 	gl_FragColor = vec4(texture2D(tex_result,texf).rgb,1.0);
	// }
	gl_FragColor = vec4(texture2D(tex_input,texf).rgb,1.0);
}