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
        newCoords = (maskColor.rg - 127.0 / 255.0);
        vec2 coord_x = newCoords;
        vec2 coord_y = newCoords;
        if (maskColor.b < 0.5){
          coord_x = hor * intensity  * newCoords.x * block_scale * leftRotIntensity;
          coord_y = ver * intensity  * newCoords.y * 2.8 * block_scale * leftRotIntensity;
        }
        else{
          coord_x = hor2 * intensity  * newCoords.x  * block_scale * rightRotIntensity;
          coord_y = ver2 * intensity  * newCoords.y * 2.8 * block_scale * rightRotIntensity;
        }
        outColor =
            texture2D(tex_origin, texc - coord_x + coord_y);
    }
    gl_FragColor = outColor;
}