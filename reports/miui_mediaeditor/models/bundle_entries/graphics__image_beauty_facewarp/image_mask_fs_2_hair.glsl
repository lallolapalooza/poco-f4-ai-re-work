void main() {
		
		vec3 sampleTex[25];
		vec3 col = vec3(0.0);
        vec2 mc = texture2D(tex_force, maskCoordOut).rg;
        if((abs(mc.g - 127.0 / 255.0)>0.0001)){
			for(int i = 0; i < 25; i++)
			{
			sampleTex[i] = vec3(texture2D(tex_force, maskCoordOut + offset_blur * offsets[i]));
			}

			for(int i = 0; i < 25; i++){
				col += sampleTex[i] * kernel[i];
			}
 		}else {
			col.rg = mc;
		}
		
        float newCoords = 0.0;
        newCoords = col.r - 127.0 / 255.0;
		float r = 1.0 / 255.0;
		float a = 0.0;
		vec2 coord = vec2(0.0);
		if (newCoords < 0.0) {
			a = ceil(newCoords / r);
			coord.x = a / 255.0 + 127.0 / 255.0;
			coord.y = (newCoords / r - a) *0.5 + 0.5;
		} else {
			a = floor(newCoords / r);
			coord.x = a / 255.0 + 127.0 / 255.0;
        	coord.y = (newCoords / r - a) *0.5 + 0.5;
		}

		float newCoords2 = 0.0;
        newCoords2 = col.g - 127.0 / 255.0;
		 a = 0.0;
		vec2 coord2 = vec2(0.0);
		if (newCoords2 < 0.0) {
			a = ceil(newCoords2 / r);
			coord2.x = a / 255.0 + 127.0 / 255.0;
			coord2.y = (newCoords2 / r - a) *0.5 + 0.5;
		} else {
			a = floor(newCoords2 / r);
			coord2.x = a / 255.0 + 127.0 / 255.0;
        	coord2.y = (newCoords2 / r - a) *0.5 + 0.5;
		}
		gl_FragColor = vec4(coord.x, coord.y, coord2.x, coord2.y);
}