void main() {
		
		vec3 sampleTex[25];
		vec3 col = vec3(0.0);
        float mc = texture2D(tex_force, maskCoordOut).r;
        if(abs(mc - 125.0 / 255.0)>0.0001){
			for(int i = 0; i < 25; i++)
			{
			sampleTex[i] = vec3(texture2D(tex_force, maskCoordOut + offset_blur * offsets[i]));
			}

			for(int i = 0; i < 25; i++){
				col += sampleTex[i] * kernel[i];
			}
 		}else {
			col.r = mc;
		}
        float newCoords = 0.0;
        newCoords = col.r - 125.0 / 255.0;
		float r = 1.0 / 255.0;
		float a = 0.0;
		vec2 coord = vec2(0.0);
		if (newCoords < 0.0) {
			a = ceil(newCoords / r);
			coord.x = a / 255.0 + 125.0 / 255.0;
			coord.y = (newCoords / r - a) *0.5 + 0.5;
		} else {
			a = floor(newCoords / r);
			coord.x = a / 255.0 + 125.0 / 255.0;
        	coord.y = (newCoords / r - a) *0.5 + 0.5;
		}
		gl_FragColor = vec4(coord.x, coord.y,125.0 / 255.0, 125.0 / 255.0);
}