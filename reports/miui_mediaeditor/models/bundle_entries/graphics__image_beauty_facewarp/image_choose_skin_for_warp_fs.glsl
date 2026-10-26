varying vec2 texc;

void main()
{
    int background = 0, skin = 1, nose = 2, eye_g = 3, l_eye = 4, r_eye = 5, l_brow = 6,
    r_brow = 7, l_ear = 8, r_ear = 9, mouth = 10, u_lip = 11, l_lip = 12, hair = 13,
    hat = 14, ear_r = 15, neck_l = 16, neck = 17, cloth = 18, forehead = 19, chin = 20;
    vec3 outcolor = vec3(0.0);
    vec4 mask_color = texture2D(tex_mask, texc);
    int c_mask_ = int(mask_color.r * 255.0 + 0.1);
    float c_mask = float(c_mask_);
    float r = 0.0;
    float g = 0.0;
    float b = 0.0;
    if (c_mask == arrfloat_mask_index[skin] ||
        c_mask == arrfloat_mask_index[nose] ||
        c_mask == arrfloat_mask_index[l_brow] ||
        c_mask == arrfloat_mask_index[r_brow] ||
        c_mask == arrfloat_mask_index[l_eye] ||
        c_mask == arrfloat_mask_index[r_eye] ||
        c_mask == arrfloat_mask_index[eye_g] ||
        c_mask == arrfloat_mask_index[u_lip] ||
        c_mask == arrfloat_mask_index[l_lip]){
        r = 1.0;
    }
    if(c_mask == arrfloat_mask_index[skin] ||
        c_mask == arrfloat_mask_index[nose] ||
        c_mask == arrfloat_mask_index[l_brow] ||
        c_mask == arrfloat_mask_index[r_brow] ||
        c_mask == arrfloat_mask_index[l_eye] ||
        c_mask == arrfloat_mask_index[r_eye] ||
        c_mask == arrfloat_mask_index[eye_g] ||
        c_mask == arrfloat_mask_index[u_lip] ||
        c_mask == arrfloat_mask_index[l_lip] ||
        c_mask == arrfloat_mask_index[hair]){
        g = 1.0;
    }
    outcolor = vec3(r,g,b);
    outcolor += vec3(mask_color.g,0.0,0.0);
    gl_FragColor = vec4(outcolor,1.0);
}