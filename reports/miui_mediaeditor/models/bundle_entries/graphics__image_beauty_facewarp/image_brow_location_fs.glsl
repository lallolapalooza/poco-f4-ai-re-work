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
        vec4 maskColor = texture2D(tex_force, sub_uv);
        vec2 newCoords = vec2(0.0);
        newCoords = (maskColor.rg - 127.0 / 255.0) ;
        newCoords = -newCoords * 0.125;
        float intensity_t = intensity;
        if(maskColor.b <= 0.5){
            intensity_t *= left_intensity;
        }else{
            intensity_t *= right_intensity;
        }
        vec2 coord = newCoords;
        vec2 newCoord_x = -hor * intensity_t * scale * newCoords.x * block_scale ;
        vec2 newCoord_y = ver * intensity_t * scale * newCoords.y * block_scale;
        float alpha = texture2D(tex_mask,texf).g;
        //alpha = smoothstep(0.0,1.0,alpha);
        outColor =
            texture2D(tex_origin, texc - (newCoord_x + newCoord_y) * alpha * 1.4);
        //outColor = mix(originColor,vec4(1.0,0.0,0.0,1.0),alpha * 0.5);
    }
    gl_FragColor = outColor;
}