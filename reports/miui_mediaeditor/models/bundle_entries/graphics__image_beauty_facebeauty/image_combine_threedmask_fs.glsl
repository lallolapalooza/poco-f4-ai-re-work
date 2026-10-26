varying vec2 texc;
void main(){
	float back_hair_color = texture2D(tex_bh,texc).r;
	float threedmask_color = texture2D(tex_tm,texc).r;
	gl_FragColor = vec4(back_hair_color,threedmask_color,0.0,1.0);
}