attribute vec4 vertex;
varying vec2 cen;
void main() { 
	gl_Position = vec4(vertex.xy,1.0,1.0);
	cen = vertex.zw;
}