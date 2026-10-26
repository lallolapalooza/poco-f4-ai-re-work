varying vec2 texc;
varying vec2 texf;

void main(){
	vec4 incolor = texture2D(tex_input, texc);
  incolor.rgb = vec3(texture2D(tex_curves,vec2(incolor.r,0.5)).a,texture2D(tex_curves,vec2(incolor.g,0.5)).a,texture2D(tex_curves,vec2(incolor.b,0.5)).a);
  incolor.rgb = vec3(texture2D(tex_curves,vec2(incolor.r,0.5)).r,texture2D(tex_curves,vec2(incolor.g,0.5)).g,texture2D(tex_curves,vec2(incolor.b,0.5)).b);
	gl_FragColor = incolor;
}
