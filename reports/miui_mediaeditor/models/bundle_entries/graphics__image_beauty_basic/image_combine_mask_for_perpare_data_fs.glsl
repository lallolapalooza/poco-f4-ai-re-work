varying vec2 texc;
void main(){
	float one_color = texture2D(tex_one,texc).r;
	float two_color = texture2D(tex_two,texc).r;
	float three_color = texture2D(tex_three,texc).r;
	float four_color = texture2D(tex_four,texc).r;
	gl_FragColor = vec4(one_color,two_color,three_color,four_color);
}