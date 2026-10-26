varying vec2 texf;
varying vec2 texc;
void main(){
    vec4 originColor = texture2D(tex_origin, texc);
    vec4 outColor = originColor;
    float a;
    if (texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y &&
        texf.y <= offset.y + wh.y) {
        vec2 sub_uv= texf - offset;
        sub_uv.x /= wh.x;
        sub_uv.y /= wh.y;
        float r = 1.0 / 255.0;
        vec4 maskColor = texture2D(tex_force, sub_uv);
        maskColor.r = maskColor.r - 125.0 / 255.0;
        maskColor.g = (maskColor.g - 0.5) * 2.0;
        float newCoords = 0.0;
        newCoords = (maskColor.r * 255.0 * r + maskColor.g * r) * 1.25;
        vec2 newCoord_x = hor * intensity * scale * newCoords * block_scale;
        outColor =
            texture2D(tex_origin, texc + newCoord_x);
    }
    gl_FragColor = outColor;
}