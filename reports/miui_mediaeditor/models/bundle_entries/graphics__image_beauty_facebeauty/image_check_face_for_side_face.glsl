varying vec2 texc;
void main(){
	vec4 maskColor = texture2D(tex_warp, texc);
	float left_dis = distance(texc, remove_pouch_nasolabial_point74);
	float right_dis = distance(texc, remove_pouch_nasolabial_point73);
	if((remove_pouch_nasolabial_pitch > 0.0 && left_dis > right_dis) ||
		(remove_pouch_nasolabial_pitch < 0.0 && left_dis < right_dis)){
		maskColor.b *= remove_pouch_nasolabial_offset_degree;
	}
	gl_FragColor = maskColor;
}