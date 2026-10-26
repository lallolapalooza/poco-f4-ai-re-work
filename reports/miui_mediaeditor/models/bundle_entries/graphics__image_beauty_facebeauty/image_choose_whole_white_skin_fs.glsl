varying vec2 texc;

/* void main()
{
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = vec4(texture2D(tex_skin_mask,texc).rg,0.0,0.0);
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
        gl_FragColor = vec4(texture2D(tex_skin_mask,texc).rg,0.0,0.0);
        return;
    }

    vec3 skin_white_color = texture2D(tex_skinwhite_mask,pos).rgb;
    vec3 whole_skin_color = skin_color + skin_white_color;
    whole_skin_color = clamp(whole_skin_color,0.0,1.0);
    int c_mask_ = int(texture2D(tex_faceparsing, pos).r * 255.0 + 0.1);
    float c_mask = float(c_mask_);
    if (c_mask == arrfloat_mask_index[hair])
        whole_skin_color = vec3(0.0,0.0,0.0);

    vec3 whole_skin_color_black = vec3(0.0);

    float k = arrfloat_k[face_idx - 1];
    float b1 = arrfloat_b1[face_idx - 1];
    float b2 = arrfloat_b2[face_idx - 1];
    float y1 = k * texc.x + b1;
    float y2 = k * texc.x + b2;
    float y_min = min(y1,y2);
    float y_max = max(y1,y2);
    if(texc.y >= y_min && texc.y <= y_max){
        whole_skin_color_black.g = whole_skin_color.r;
    }else{
        whole_skin_color_black.r = whole_skin_color.r;
    }

    float forehead_skin = 0.0;
    vec4 outcolor = texture2D(tex_input,texc);
    float r = outcolor.r;
    float g = outcolor.g;
    float b = outcolor.b;
    if(r > 0.371 && g >0.156 && b > 0.078 && r > b && r > g && abs(r-g)>0.059 && (max(max(r,g),b)-min(min(r,g),b) > 0.059)){
      //skin
      forehead_skin = 1.0;
    }
    
    gl_FragColor = vec4(whole_skin_color.r,whole_skin_color.r,whole_skin_color_black.g,forehead_skin);
} */

float get_skin_white_color(){
    float skin_white_color_result = 0.0;
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        skin_white_color_result = texture2D(tex_skin_mask,texc).r;
        return skin_white_color_result;
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
        skin_white_color_result = texture2D(tex_skin_mask,texc).r;
        return skin_white_color_result;
    }

    vec3 skin_white_color = texture2D(tex_skinwhite_mask,pos).rgb;
    vec3 whole_skin_color = skin_color + skin_white_color;
    whole_skin_color = clamp(whole_skin_color,0.0,1.0);
    int c_mask_ = int(texture2D(tex_faceparsing, pos).r * 255.0 + 0.1);
    float c_mask = float(c_mask_);
    if (c_mask == arrfloat_mask_index[hair])
        whole_skin_color = vec3(0.0,0.0,0.0);

    skin_white_color_result = whole_skin_color.r;
    return skin_white_color_result;
}

void main(){
    
    float skin_white_color = get_skin_white_color();

    float skin_color = texture2D(tex_skin_mask,texc).r;
    float face_rate_color = smoothstep(0.0,0.2,texture2D(tex_face_rate_mask,texc).a);
    gl_FragColor = vec4(skin_white_color,skin_color,face_rate_color,1.0);
}