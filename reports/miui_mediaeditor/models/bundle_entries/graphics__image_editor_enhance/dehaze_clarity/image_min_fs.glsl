varying vec2 texc;

bool is_gray(vec3 incolor){
  float epsilon = 0.01;
  float maxDifference = max(abs(incolor.r - incolor.g), max(abs(incolor.g - incolor.b), abs(incolor.b - incolor.r)));
  return maxDifference < epsilon;
}

void main() {
    vec4 color = texture2D(tex_origin, texc);
	if(is_gray(color.rgb) && color.r < 0.05){
		if(abs(texc.x - 0.0) < 0.001){
            color = texture2D(tex_origin, vec2(texc.x + wh.x,texc.y));
        }else if(abs(texc.x - 1.0) < 0.001){
            color = texture2D(tex_origin, vec2(texc.x - wh.x,texc.y));
        }else if(abs(texc.y - 0.0) < 0.001){
            color = texture2D(tex_origin, vec2(texc.x,texc.y + wh.y));
        }else if(abs(texc.y - 1.0) < 0.001){
            color = texture2D(tex_origin, vec2(texc.x,texc.y - wh.y));
        }
	}
    gl_FragColor = vec4(color.rgb,min(color.r,min(color.g,color.b)));
}
