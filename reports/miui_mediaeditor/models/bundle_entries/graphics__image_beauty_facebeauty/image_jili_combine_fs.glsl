varying vec2 texc;
void main(){
	vec3 threedred_color = texture2D(tex_threemask,texc).rgb;
	vec4 jili_color = texture2D(tex_jilimask,texc);
	gl_FragColor = vec4(jili_color.g,threedred_color.r,jili_color.r,jili_color.b);
}