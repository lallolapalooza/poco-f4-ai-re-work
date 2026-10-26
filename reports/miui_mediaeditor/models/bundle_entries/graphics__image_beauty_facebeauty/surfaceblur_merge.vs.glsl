//attribute vec2 Pquad
varying vec2 uv;
varying vec2 uvMask;
void main() 
{
	gl_Position=vec4(Pquad.xy*2.0-1.0,0.0,1.0);
	uv = Pquad.xy;
  uvMask.x = uv.x * 0.5 + 0.5;
  uvMask.y = uv.y;
}
