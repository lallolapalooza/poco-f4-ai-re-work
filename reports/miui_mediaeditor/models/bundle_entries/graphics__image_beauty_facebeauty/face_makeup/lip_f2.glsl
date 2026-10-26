void main(){
	vec4 origin;		
	origin = texture2D(tex_gloss, st2).rgba;
	gl_FragColor = origin;
}