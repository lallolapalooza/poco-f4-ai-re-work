varying vec2 texc;

void main()
{
    float alpha_protect = texture2D(tex_mask_protect,texc).r;
    float alpha_protect_glass = 1.0 - texture2D(tex_mask_protect_glass,texc).r;
    vec3 sbColor = texture2D(tex_slightblur,texc).rgb;
    vec3 hbColor = texture2D(tex_heavyblur,texc).rgb;
    vec3 oColor = texture2D(tex_origin,texc).rgb;
    if(type < 0.5){
        vec3 shdif = clamp((hbColor - sbColor) * 1.4 + 0.075 * hbColor * texture2D(tex_mask_sh,texc).r, 0.0, 0.3);
        /* vec3 rColor = min(oColor + shdif, 1.0);
        vec3 rColor1 = min(rColor, hbColor);
        shdif = shdif + rColor1 - rColor; */

        int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);      
        if(face_idx == 0 || face_idx > int(face_num))
        {
            gl_FragColor = vec4(shdif, 0.0);
        }
        else
        {
            face_idx -= 1;
            vec2 offset = vec2(arrfloat_offset[2 * face_idx],arrfloat_offset[2 * face_idx + 1]);
            vec2 wh = vec2(arrfloat_wh[2 * face_idx],arrfloat_wh[2 * face_idx + 1]);
            vec2 offset_jili = vec2(arrfloat_offsetjili[2 * face_idx],arrfloat_offsetjili[2 * face_idx + 1]);
            vec2 wh_jili = vec2(arrfloat_whjili[2 * face_idx],arrfloat_whjili[2 * face_idx + 1]);
            vec2 texp = (texc - offset) / wh;
            vec2 texpv = texp * wh_jili + offset_jili;
            float alpha = texture2D(tex_mask,texpv).b * alpha_protect * alpha_protect_glass;
            gl_FragColor = vec4(shdif, alpha);
        }
    }
    else{
        float alpha = texture2D(tex_mask,texc).b * alpha_protect * alpha_protect_glass;
        vec3 shdif = clamp((hbColor - sbColor) * 1.4 + 0.05 * hbColor, 0.0, 0.3);
        gl_FragColor = vec4(shdif, alpha);
    }
}