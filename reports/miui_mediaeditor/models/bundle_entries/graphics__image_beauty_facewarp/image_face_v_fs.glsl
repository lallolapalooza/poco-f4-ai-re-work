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

        float r = 1.0 / 255.0;
        vec4 maskColor = texture2D(tex_force, sub_uv);
        maskColor.r = maskColor.r - 127.0 / 255.0;
        maskColor.g = (maskColor.g - 0.5) * 2.0;
        maskColor.b = maskColor.b - 127.0 / 255.0;
        maskColor.a = (maskColor.a - 0.5) * 2.0;
        float newCoordsX = (maskColor.r * 255.0 * r + maskColor.g * r) *  0.125;
        float newCoordsY = (maskColor.b * 255.0 * r + maskColor.a * r) *  0.125;
        float rIntensity = 1.0;
        if (newCoordsX < 0.0) {
            rIntensity = leftRotIntensity;
        } else {
           rIntensity = rightRotIntensity; 
        }
        vec2 coord = vec2(newCoordsX, newCoordsY);
        vec2 newCoords = vec2(0.0, 0.0);
        newCoords.x = coord.x * cAngle - sAngle * coord.y;
        newCoords.y = coord.x * sAngle + cAngle * coord.y;
        outColor =
            texture2D(tex_origin, texc - newCoords * intensity * rIntensity * scale * block_scale);
    }
    gl_FragColor = outColor;
}