varying vec2 texc;

void main()
{
    int face_idx = int(texture2D(tex_warp, texc).a * 255.0 + 0.1);
    if(face_idx < 0 || face_idx >= int(face_num))
    {
        gl_FragColor = vec4(0.5,0.5,0.0,1.0);
        return;
    }
    vec2 offset = vec2(arrfloat_offset[2 * face_idx],arrfloat_offset[2 * face_idx + 1]);
    vec2 wh = vec2(arrfloat_wh[2 * face_idx],arrfloat_wh[2 * face_idx + 1]);
    vec2 offset_jili = vec2(arrfloat_offsetjili[2 * face_idx],arrfloat_offsetjili[2 * face_idx + 1]);
    vec2 wh_jili = vec2(arrfloat_whjili[2 * face_idx],arrfloat_whjili[2 * face_idx + 1]);
    vec2 texp = (texc - offset_jili) / wh_jili;
    vec2 texpv = texp * wh + offset;
    vec4 jili_highpass_color = texture2D(tex_jili_highpass, texpv);
    gl_FragColor = vec4(jili_highpass_color.b); 
}