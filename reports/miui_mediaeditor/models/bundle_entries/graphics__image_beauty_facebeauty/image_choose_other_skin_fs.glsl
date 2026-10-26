varying vec2 texc;

void main()
{
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = texture2D(tex_skin_mask,texc);
        return;
    }
    int background = 0, skin = 1, nose = 2, eye_g = 3, l_eye = 4, r_eye = 5, l_brow = 6,
    r_brow = 7, l_ear = 8, r_ear = 9, mouth = 10, u_lip = 11, l_lip = 12, hair = 13,
    hat = 14, ear_r = 15, neck_l = 16, neck = 17, cloth = 18, forehead = 19, chin = 20;
    vec3 skin_color = texture2D(tex_skin_mask,texc).rgb;

    vec2 offset = arrvec2_offset[face_idx - 1];
    vec2 offset_face = arrvec2_offset_face[face_idx - 1];
    vec2 pos = texc * wh - offset;
    pos = (pos + offset_face)/wh_face;

    int face_idx_temp = int(texture2D(tex_label_face, pos).r * 255.0 + 0.1);
    if(face_idx != face_idx_temp)
    {
        gl_FragColor = texture2D(tex_skin_mask,texc);
        return;
    }

    vec3 skin_white_color = texture2D(tex_skinwhite_mask,pos).rgb;
    vec3 other_skin_color = skin_color - skin_white_color;
    other_skin_color = clamp(other_skin_color,0.0,1.0);
    int c_mask_ = int(texture2D(tex_faceparsing, pos).r * 255.0 + 0.1);
    float c_mask = float(c_mask_);
    if (c_mask == arrfloat_mask_index[hair])
        other_skin_color = vec3(0.0,0.0,0.0);
    gl_FragColor = vec4(other_skin_color,1.0);
}