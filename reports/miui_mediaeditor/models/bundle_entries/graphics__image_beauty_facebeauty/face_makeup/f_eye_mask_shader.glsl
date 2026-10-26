void main()
{
		vec4 color=texture2D(tex_eye_mask,uv);
		gl_FragColor = color;

}