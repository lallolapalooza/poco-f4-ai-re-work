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
        float rIntensity = 1.0;
        if (newCoords.x < 0.0) {
           rIntensity = leftRotIntensity;
        } else {
          rIntensity = rightRotIntensity; 
       }
        vec2 coord = newCoords;
        vec2 newCoord_x = -hor * intensity * scale * newCoords.x * block_scale * rIntensity * h_intensity * 0.8 * rIntensity;
        vec2 newCoord_y = ver * intensity * scale * newCoords.y * block_scale * rIntensity * t_intensity * rIntensity;
        outColor =
            texture2D(tex_origin, texc - newCoord_x - newCoord_y);
    }
    gl_FragColor = outColor;
}