void main()
{
	gl_Position=vec4(Ps.xy*2.0-1.0,0.0,1.0);
	stf = Ps.xy+(Ps.xy - st.xy);
	//st_mask = st;
}