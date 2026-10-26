varying vec2 texf;
varying vec2 texc;
void main(){
    vec4 originColor = texture2D(tex_origin, texc);
    vec4 outColor = originColor;
    if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
        texf.y <= offset.y + wh.y) {
        vec2 sub_uv= texf - offset;
        sub_uv.x /= wh.x;
        sub_uv.y /= wh.y;

        // vec2 mask_uv = texf - maskoffset;
        // mask_uv.x /= masksize.x;
        // mask_uv.y /= masksize.y;

        vec2 mask_uv;
        mask_uv.x = texc.x;
        mask_uv.y = texc.y + flag * 5.0 * offset_blur.y;

        float maskIntensity = 1.0;
        //maskIntensity = texture2D(tex_mask, mask_uv).b;
		//vec3 sampleTex[25];
		vec3 col = vec3(0.0);
		//for(int i = 0; i < 25; i++)
		//{
		//    vec3 s = vec3(texture2D(tex_mask, mask_uv + offset_blur * arrfloat2_offsets[i]));
         //   float r = s.r;
         //   if (r * 10.0 < 120.0 / 255.0 && r * 5.0 > 0.0) {
         //       s.r = r / 10.0;
         //   } else {
         //       s.r = 1.0;
         //   }
         //   sampleTex[i] = s;
		//}

		//for(int i = 0; i < 25; i++){
		//	col += sampleTex[i] * arrfloat_kernel[i];
		//}
        col = vec3(texture2D(tex_mask, texf).rgb);
        vec4 maskColor;
        float newCoordsX;
        float newCoordsY;
        float r = 1.0 / 255.0;
        if (type < 0.5){
            maskIntensity = 1.0;
            maskColor = texture2D(tex_force, sub_uv);
            maskColor.r = maskColor.r - 127.0 / 255.0;
            maskColor.g = (maskColor.g - 0.5) * 2.0;
            maskColor.b = maskColor.b - 127.0 / 255.0;
            maskColor.a = (maskColor.a - 0.5) * 2.0;
            newCoordsX = (maskColor.r * 255.0 * r + maskColor.g * r) *  0.125;
            newCoordsY = (maskColor.b * 255.0 * r + maskColor.a * r) *  0.125;
        } else {
            //maskIntensity = 1.0 - col.g;
            //maskIntensity = 1.0 - col.r;
            // if (maskIntensity < 0.0) {
            //     maskIntensity = 0.0;
            // }
           // maskIntensity = 1.0;
            maskIntensity = 1.0 - col.r;
            maskColor = texture2D(tex_force, sub_uv);
            maskColor.r = maskColor.r - 127.0 / 255.0;
            maskColor.g = (maskColor.g - 0.5) * 2.0;
            maskColor.b = maskColor.b - 127.0 / 255.0;
            maskColor.a = (maskColor.a - 0.5) * 2.0;
            newCoordsX = (maskColor.r * 255.0 * r + maskColor.g * r) *  0.125;
            newCoordsY = (maskColor.b * 255.0 * r + maskColor.a * r) *  0.125;
        }
        float rIntensity = 1.0;
        if (newCoordsX <= 0.0) {
            rIntensity = leftRotIntensity;
        } else {
           rIntensity = rightRotIntensity;
        }
        vec2 coord = vec2(newCoordsX, newCoordsY);
        vec2 newCoords = vec2(0.0, 0.0);
        newCoords.x = coord.x * cAngle - sAngle * coord.y;
        newCoords.y = coord.x * sAngle + cAngle * coord.y;
        outColor =
            texture2D(tex_origin, texc + newCoords * intensity * rIntensity * scale * block_scale * 1.0 * maskIntensity);
        //  if(abs(maskColor.r) > 0.0) {
        //    outColor = vec4(1.0, 0.0, 0.0, 1.0);
        //  }
    }
    gl_FragColor = outColor;
}