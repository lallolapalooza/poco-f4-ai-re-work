varying vec2 texc;

void main()
{
    int background = 0, skin = 1, nose = 2, eye_g = 3, l_eye = 4, r_eye = 5, l_brow = 6,
    r_brow = 7, l_ear = 8, r_ear = 9, mouth = 10, u_lip = 11, l_lip = 12, hair = 13,
    hat = 14, ear_r = 15, neck_l = 16, neck = 17, cloth = 18, forehead = 19, chin = 20;
    vec4 outcolor = vec4(0.0,0.0,0.0,0.0);
    int c_mask_ = int(texture2D(tex_faceparsing, texc).r * 255.0 + 0.1);
    float c_mask = float(c_mask_);

    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    int face_idx_temp = int(texture2D(tex_label, texc).a * 255.0 + 0.1);
    if(face_idx != face_idx_temp + 1)
    {
        gl_FragColor = vec4(0.0,0.0,0.0,0.0);
        return;
    }

    if(type == 0.0){
        if (c_mask == arrfloat_mask_index[skin] ||
        c_mask == arrfloat_mask_index[nose] ||
        c_mask == arrfloat_mask_index[l_brow] ||
        c_mask == arrfloat_mask_index[r_brow] ||
        c_mask == arrfloat_mask_index[l_eye] ||
        c_mask == arrfloat_mask_index[r_eye] ||
        c_mask == arrfloat_mask_index[eye_g] ||
        c_mask == arrfloat_mask_index[u_lip] ||
        c_mask == arrfloat_mask_index[l_lip]){
            outcolor = vec4(1.0,1.0,1.0,1.0);
        }
    }
    else if(type == 1.0){
        if(c_mask == arrfloat_mask_index[l_eye] || 
        c_mask == arrfloat_mask_index[r_eye]){
            outcolor = vec4(1.0,1.0,1.0,1.0);
        }
    }
    else if(type == 2.0){
        if(c_mask == arrfloat_mask_index[l_brow] || 
        c_mask == arrfloat_mask_index[r_brow]){
            outcolor = vec4(1.0,1.0,1.0,1.0);
        }
    }
    else if(type == 3.0){
        if(c_mask == arrfloat_mask_index[l_lip] || 
        c_mask == arrfloat_mask_index[u_lip]){
            outcolor = vec4(1.0,1.0,1.0,1.0);
        }
    }
    else if(type == 4.0){
        if(c_mask == arrfloat_mask_index[eye_g] || 
        c_mask == arrfloat_mask_index[l_eye] || 
        c_mask == arrfloat_mask_index[r_eye]){
            outcolor = vec4(1.0,1.0,1.0,1.0);
        }
    }
    else if(type == 5.0){
        if (c_mask == arrfloat_mask_index[skin] ||
        c_mask == arrfloat_mask_index[nose] ||
        c_mask == arrfloat_mask_index[l_ear] ||
        c_mask == arrfloat_mask_index[r_ear] ||
        c_mask == arrfloat_mask_index[l_eye] ||
        c_mask == arrfloat_mask_index[r_eye] ||
        c_mask == arrfloat_mask_index[l_lip] ||
        c_mask == arrfloat_mask_index[u_lip] ||
        c_mask == arrfloat_mask_index[mouth] ||
        c_mask == arrfloat_mask_index[l_brow] ||
        c_mask == arrfloat_mask_index[r_brow] ||
        c_mask == arrfloat_mask_index[eye_g]) {
            outcolor = vec4(1.0,1.0,1.0,1.0);
        }
    }
    else if(type == 6.0){
        if(c_mask == arrfloat_mask_index[l_brow] || 
        c_mask == arrfloat_mask_index[r_brow]){
            outcolor = vec4(211.0/255.0,195.0/255.0,191.0/255.0,1.0);
        }
        else{
            outcolor = vec4(1.0,1.0,1.0,1.0);
        }
    }
    else if(type == 7.0){
        /* if (c_mask == arrfloat_mask_index[background] ||
            c_mask == arrfloat_mask_index[hair]){
            outcolor = vec3(0.0,0.0,0.0);
        }
        else{
            outcolor = vec3(1.0,1.0,1.0);
        } */
        if (c_mask == arrfloat_mask_index[skin] ||
        c_mask == arrfloat_mask_index[nose] ||
        c_mask == arrfloat_mask_index[l_ear] ||
        c_mask == arrfloat_mask_index[r_ear] ||
        c_mask == arrfloat_mask_index[l_eye] ||
        c_mask == arrfloat_mask_index[r_eye] ||
        c_mask == arrfloat_mask_index[l_lip] ||
        c_mask == arrfloat_mask_index[u_lip] ||
        c_mask == arrfloat_mask_index[l_brow] ||
        c_mask == arrfloat_mask_index[r_brow] ||
        c_mask == arrfloat_mask_index[eye_g]) {
            outcolor = vec4(1.0,1.0,1.0,1.0);
        }
    }
    else if(type == 8.0){
        float r = 0.0;
        float g = 0.0;
        float b = 0.0;
        float a = 0.0;
        if (c_mask == arrfloat_mask_index[skin] ||
            c_mask == arrfloat_mask_index[nose] ||
            c_mask == arrfloat_mask_index[l_lip] ||
            c_mask == arrfloat_mask_index[u_lip] ||
            c_mask == arrfloat_mask_index[eye_g]) {
                r = 1.0;
        }
        if (c_mask == arrfloat_mask_index[skin] ||
            c_mask == arrfloat_mask_index[eye_g]) {
                g = 1.0;
        }
        if (c_mask == arrfloat_mask_index[background] ||
            c_mask == arrfloat_mask_index[hair]){
            b = 0.0;
        }
        else{
            b = 1.0;
        }
        if (c_mask == arrfloat_mask_index[skin] ||
            c_mask == arrfloat_mask_index[nose] ||
            c_mask == arrfloat_mask_index[eye_g]) {
            a = 1.0;
        }
        outcolor = vec4(r,g,b,a);
    }
    else if(type == 9.0){
        float r = 0.0;
        float g = 0.0;
        float b = 0.0;
        if (c_mask == arrfloat_mask_index[l_lip] ||
            c_mask == arrfloat_mask_index[u_lip] ||
            c_mask == arrfloat_mask_index[mouth]) {
                r = 1.0;
        }
        if (c_mask == arrfloat_mask_index[mouth]) {
                g = 1.0;
        }
        outcolor = vec4(r,g,b,1.0);
    }
    else if(type == 10.0){
        float r = 1.0;
        float g = 0.0;
        if (c_mask == arrfloat_mask_index[background] ||
            c_mask == arrfloat_mask_index[eye_g]) {
               r = 0.0;
        }
        if (c_mask == arrfloat_mask_index[skin] ||
            c_mask == arrfloat_mask_index[nose] ||
            c_mask == arrfloat_mask_index[l_brow] ||
            c_mask == arrfloat_mask_index[r_brow] ||
            c_mask == arrfloat_mask_index[l_eye] ||
            c_mask == arrfloat_mask_index[r_eye] ||
            c_mask == arrfloat_mask_index[eye_g] ||
            c_mask == arrfloat_mask_index[u_lip] ||
            c_mask == arrfloat_mask_index[l_lip]){
                g = 1.0;
        }
        outcolor = vec4(r,g,0.0,1.0);
    }
    gl_FragColor = outcolor;
}