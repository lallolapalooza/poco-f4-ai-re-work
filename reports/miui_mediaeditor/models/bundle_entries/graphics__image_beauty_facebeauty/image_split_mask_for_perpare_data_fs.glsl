varying vec2 texc;
void main(){
	vec3 color = texture2D(tex_combine,texc).rgb;
	if(type == 0.0)
		gl_FragColor = vec4(vec3(color.r),1.0);
	else if(type == 1.0)
		gl_FragColor = vec4(vec3(color.g),1.0);
	else
		gl_FragColor = vec4(vec3(color.b),1.0);
}