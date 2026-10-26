varying vec2 texc;

void main()
{
    /* int background = 0, skin = 1, nose = 2, eye_g = 3, l_eye = 4, r_eye = 5, l_brow = 6,
    r_brow = 7, l_ear = 8, r_ear = 9, mouth = 10, u_lip = 11, l_lip = 12, hair = 13,
    hat = 14, ear_r = 15, neck_l = 16, neck = 17, cloth = 18, forehead = 19, chin = 20;
    int c_mask_ = int(texture2D(tex_faceparsing, texc).r * 255.0 + 0.1);
    float c_mask = float(c_mask_); */
    float alpha_protect = texture2D(tex_mask_protect,texc).r;
    float alpha_protect_glass = 1.0 - texture2D(tex_mask_protect_glass,texc).r;
    if(type == 0.0)
    {
        float alpha = texture2D(tex_mask,texc).b * alpha_protect * alpha_protect_glass;
        /* if(c_mask == arrfloat_mask_index[background]|| 
        c_mask == arrfloat_mask_index[hair])
            alpha = 0.0; */
        gl_FragColor = vec4(texture2D(tex_origin,texc).rgb,alpha);
    }
    else
    {
        float alpha = texture2D(tex_mask,texc).b * alpha_protect * alpha_protect_glass;
        /* if(c_mask == arrfloat_mask_index[background]|| 
        c_mask == arrfloat_mask_index[hair])
            alpha = 0.0; */
        gl_FragColor = vec4(texture2D(tex_origin,texc).rgb,alpha);
    }
}