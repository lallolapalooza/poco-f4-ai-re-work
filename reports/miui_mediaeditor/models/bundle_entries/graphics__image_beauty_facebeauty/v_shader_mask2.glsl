varying vec2 st;
void main()
{
	gl_Position=vec4(Ps.xy*2.0-1.0,0.0,1.0);
	st = Ps.sy;
}