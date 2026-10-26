precision mediump float;
varying vec2 cen;
void main() {
	float dist = sqrt(dot(cen,cen));
	if (dist < 0.5) {
		gl_FragColor = vec4(1.0, 1.0 , 1.0,1.0);
	} else if (dist < 0.8){
		gl_FragColor = vec4(0.42, 0.32 , 1.0,1.0);
	} else {
		discard;
	}
}