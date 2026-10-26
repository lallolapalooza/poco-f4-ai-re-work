varying vec2 texc;

void main()
{
    /* int background = 0, skin = 1, nose = 2, eye_g = 3, l_eye = 4, r_eye = 5, l_brow = 6,
    r_brow = 7, l_ear = 8, r_ear = 9, mouth = 10, u_lip = 11, l_lip = 12, hair = 13,
    hat = 14, ear_r = 15, neck_l = 16, neck = 17, cloth = 18, forehead = 19, chin = 20; */
    float skin_red_lip = texture2D(tex_skin_red_lip,texc).r;
    vec4 warp_skin_color1 = texture2D(tex_warp_skin_red1,texc);
    vec4 warp_skin_color2 = texture2D(tex_warp_skin_red2,texc);
    /* int c_mask_ = int(texture2D(tex_faceparsing, texc).r * 255.0 + 0.1);
    float c_mask = float(c_mask_);
    if (c_mask != arrfloat_mask_index[skin] &&
        c_mask != arrfloat_mask_index[eye_g]){
            warp_skin_color = vec4(0.0);
    } */
    float skin_red_protect = texture2D(tex_skin_red_mask, texc).g;
    warp_skin_color1 *= skin_red_protect * (1.0 - skin_red_lip);
    warp_skin_color2 *= skin_red_protect * (1.0 - skin_red_lip);
    if(type < 0.5){
        gl_FragColor = vec4(skin_red_lip,warp_skin_color1.r,warp_skin_color1.g,warp_skin_color1.b);
    }
    else{
        gl_FragColor = vec4(warp_skin_color2.r,warp_skin_color2.g,warp_skin_color2.b,texture2D(tex_warp_brown,texc).g);
    }
}