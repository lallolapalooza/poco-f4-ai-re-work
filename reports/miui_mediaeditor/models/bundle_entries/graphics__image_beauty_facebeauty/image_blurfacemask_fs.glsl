varying vec2 texf;
void main(){
	vec3 sampleTex[25];
	vec3 col = vec3(0.0);
	for(int i = 0; i < 25; i++)
	{
		vec3 s = vec3(texture2D(tex_mask, texf + offset_blur * arrfloat2_offsets[i]));
        float r = s.r;
        if (r * 10.0 < 120.0 / 255.0 && r * 5.0 > 0.0) {
              s.r = r / 10.0;
        } else {
              s.r = 1.0;
        }
        sampleTex[i] = s;
	}

	for(int i = 0; i < 25; i++){
	col += sampleTex[i] * arrfloat_kernel[i];
	}
	gl_FragColor = vec4(col, 1.0);
}