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
        float newCoords = 0.0;
        newCoords = (maskColor.g - 125.0 / 255.0) * 0.25 * 0.9;
        vec2 coord = vec2(0.0);
        if (maskColor.b < 0.5) {
            coord = newCoords * leftVec;
        } else {
            coord = newCoords * rightVec;
        }
        outColor =
            texture2D(tex_origin, texc + coord * intensity * scale * block_scale);
    }
    gl_FragColor = outColor;
}