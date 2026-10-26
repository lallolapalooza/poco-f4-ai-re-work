varying vec2 texc;
void main(){
	vec3 stroke_mask = texture2D(tex_stroke,texc).rgb;
	vec4 origin_color = texture2D(tex_origin,texc);
	if(stroke_mask.r > 0.001){		
		gl_FragColor = vec4(mix(origin_color.rgb,stroke_color,stroke_mask.r * type),origin_color.a);
	}
	else{
		gl_FragColor = origin_color;
	}
}