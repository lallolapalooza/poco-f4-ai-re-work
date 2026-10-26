varying vec2 stf_block;
void main()
{
	vec2 p = Ps.xy;
	stf_block = p;
	p = (p * f_dim - b_offset) / b_dim;
	gl_Position=vec4(p*2.0-vec2(1.0),1.0,1.0);
	stf = p;
	uv = UV;
}