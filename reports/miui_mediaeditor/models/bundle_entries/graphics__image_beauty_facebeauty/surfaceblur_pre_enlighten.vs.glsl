varying vec2 st;
void main()
{
	gl_Position=vec4(Pquad.xy*2.0-1.0,0.0,1.0);
	st = Pquad.xy;
}