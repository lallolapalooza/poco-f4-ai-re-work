varying vec2 texc;

void main()
{
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = vec4(0.0,0.0,0.0,1.0);
        return;
    }
    int background = 0, skin = 1, nose = 2, eye_g = 3, l_eye = 4, r_eye = 5, l_brow = 6,
    r_brow = 7, l_ear = 8, r_ear = 9, mouth = 10, u_lip = 11, l_lip = 12, hair = 13,
    hat = 14, ear_r = 15, neck_l = 16, neck = 17, cloth = 18, forehead = 19, chin = 20;
    float forheadk = arrfloat_forheadk[face_idx - 1];
    float foreheadminy = arrfloat_foreheadminy[face_idx - 1];
    float foreheadmaxy = arrfloat_foreheadmaxy[face_idx - 1];
    vec2 pos = texc * pic_wh;
    float temp1 = forheadk * pos.x + foreheadminy;
    float temp2 = forheadk * pos.x + foreheadmaxy;
    bool is_forhead = (pos.y >= temp1 && pos.y <= temp2) || (pos.y >= temp2 && pos.y <= temp1);
    int c_mask_ = int(texture2D(tex_faceparsing, texc).r * 255.0 + 0.1);
    float c_mask = float(c_mask_);
    if (c_mask == arrfloat_mask_index[nose] || (is_forhead && c_mask == arrfloat_mask_index[skin])) {
        float b = texture2D(tex_origin,texc).b;
        if (b >= 0.6) {
            gl_FragColor = vec4(1.0,1.0,1.0,1.0);
        }
        else{
            gl_FragColor = vec4(0.0,0.0,0.0,1.0);
        }
    }
    else{
        gl_FragColor = vec4(0.0,0.0,0.0,1.0);
    }
}