// void main() {

// 		offsets[0] = vec2(-offset.x * 2.0,  offset.y * 2.0);
// 		offsets[1] = vec2(-offset.x,  offset.y * 2.0);
// 		offsets[2] = vec2(0.0,  offset.y * 2.0);
// 		offsets[3] = vec2(offset.x,  offset.y * 2.0);
// 		offsets[4] = vec2(offset.x * 2.0,  offset.y * 2.0);
// 		offsets[5] = vec2(-offset.x * 2.0,  offset.y);
// 		offsets[6] = vec2(-offset.x,  offset.y);
// 		offsets[7] = vec2(0.0,  offset.y);
// 		offsets[8] = vec2(offset.x,  offset.y);
// 		offsets[9] = vec2(offset.x * 2.0,  offset.y);
// 		offsets[10] = vec2(-offset.x * 2.0,  0.0);
// 		offsets[11] = vec2(-offset.x,  0.0);
// 		offsets[12] = vec2(0.0,  0.0);
// 		offsets[13] = vec2(offset.x,  0.0);
// 		offsets[14] = vec2(offset.x * 2.0,  0.0);
// 		offsets[15] = vec2(-offset.x * 2.0,  -offset.y);
// 		offsets[16] = vec2(-offset.x,  -offset.y);
// 		offsets[17] = vec2(0.0,  -offset.y);
// 		offsets[18] = vec2(offset.x,  -offset.y);
// 		offsets[19] = vec2(offset.x * 2.0,  -offset.y);
// 		offsets[20] = vec2(-offset.x * 2.0,  -offset.y * 2.0);
// 		offsets[21] = vec2(-offset.x,  -offset.y * 2.0);
// 		offsets[22] = vec2(0.0,  -offset.y * 2.0);
// 		offsets[23] = vec2(offset.x,  -offset.y * 2.0);
// 		offsets[24] = vec2(offset.x * 2.0,  -offset.y * 2.0);

		
// 		kernel[0] = 1.0/273.0;
// 		kernel[1] = 4.0/273.0;
// 		kernel[2] = 7.0/273.0;
// 		kernel[3] = 4.0/273.0;
// 		kernel[4] = 1.0/273.0;
// 		kernel[5] = 4.0/273.0;
// 		kernel[6] = 16.0/273.0;
// 		kernel[7] = 26.0/273.0;
// 		kernel[8] = 16.0/273.0;
// 		kernel[9] = 5.0/273.0;
// 		kernel[10] = 7.0/273.0;
// 		kernel[11] = 26.0/273.0;
// 		kernel[12] = 41.0/273.0;
// 		kernel[13] = 26.0/273.0;
// 		kernel[14] = 7.0/273.0;
// 		kernel[15] = 4.0/273.0;
// 		kernel[16] = 16.0/273.0;
// 		kernel[17] = 26.0/273.0;
// 		kernel[18] = 16.0/273.0;
// 		kernel[19] = 5.0/273.0;
// 		kernel[20] = 1.0/273.0;
// 		kernel[21] = 4.0/273.0;
// 		kernel[22] = 7.0/273.0;
// 		kernel[23] = 4.0/273.0;
// 		kernel[24] = 1.0/273.0 ;

// 		vec3 sampleTex[25];
// 		for(int i = 0; i < 25; i++)
// 		{
// 			sampleTex[i] = vec3(texture2D(tex, st + offsets[i]));
// 		}
// 		vec3 col = vec3(0.0);
// 		for(int i = 0; i < 25; i++)
// 			col += sampleTex[i] * kernel[i];
 
// 		gl_FragColor = vec4(col, 1.0);
// }


void main() {
		vec3 col = vec3(0.0);
		for(int i = 0; i < 25; i++) {
			vec3 c = texture2D(tex, st + vec2(offsets[i] * base)).rgb;
			col += c * kernels[i];
		}
		gl_FragColor = vec4(col, 1.0);
}
