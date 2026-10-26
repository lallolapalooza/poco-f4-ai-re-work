varying vec2 texc;
varying vec2 texf;

void main(){
	vec4 incolor = texture2D(tex_input, texc);
  vec2 step = 1.0 / vec2(input_w,input_h);
	vec3 texA = texture2D( tex_input, texc + vec2(-step.x, -step.y) * 1.0 ).rgb;
	vec3 texB = texture2D( tex_input, texc + vec2( step.x, -step.y) * 1.0 ).rgb;
	vec3 texC = texture2D( tex_input, texc + vec2(-step.x,  step.y) * 1.0 ).rgb;
	vec3 texD = texture2D( tex_input, texc + vec2( step.x,  step.y) * 1.0 ).rgb;
		
	vec3 around = 0.25 * (texA + texB + texC + texD);
  gl_FragColor = vec4(around,1.0);
}
