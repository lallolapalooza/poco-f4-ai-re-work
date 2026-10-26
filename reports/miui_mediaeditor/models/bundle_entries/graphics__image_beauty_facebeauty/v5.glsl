varying vec4 st_offset1;
varying vec4 st_offset2;
void main() 
{
	gl_Position=vec4(P_quad.xy*2.0-1.0,0.0,1.0);
	st = P_quad.xy;
	st_offset1 = vec4(st.xy+ vec2(-widthOffset, 0.0), st.xy+ vec2(widthOffset, 0.0));
	st_offset2 = vec4(st.xy+ vec2(0.0,-heightOffset), st.xy+ vec2(0.0, heightOffset));
}