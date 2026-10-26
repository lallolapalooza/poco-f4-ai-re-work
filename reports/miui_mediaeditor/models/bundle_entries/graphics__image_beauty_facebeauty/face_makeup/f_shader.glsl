
void main()
{
		
		vec4 color = texture2D(tex,stf);
		gl_FragColor=vec4(color.rgb,1.0);
		//gl_FragColor=vec4(1.0);
}