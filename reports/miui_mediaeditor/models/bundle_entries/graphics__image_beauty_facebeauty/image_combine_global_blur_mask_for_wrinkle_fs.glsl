varying vec2 texc;

void main()
{
    int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
    if(face_idx == 0 || face_idx > int(face_num))
    {
        gl_FragColor = vec4(texture2D(tex_global_blur,texc).rgb,texture2D(tex_global_blur,texc).r);
        return;
    }
    vec4 global_blur_color = vec4(texture2D(tex_global_blur,texc).r);

    vec2 offset = arrvec2_offset[face_idx - 1];
    vec2 offset_face = arrvec2_offset_face[face_idx - 1];
    vec2 pos = texc * wh - offset;
    if(pos.x < 0.0 || pos.y < 0.0){
        gl_FragColor = vec4(texture2D(tex_global_blur,texc).rgb,texture2D(tex_global_blur,texc).r);
        return;
    }
    pos = (pos + offset_face)/wh_face;
    int face_idx_temp = int(texture2D(tex_label_face, pos).r * 255.0 + 0.1);
    if(face_idx != face_idx_temp || pos.x > 1.0 || pos.y > 1.0)
    {
        gl_FragColor = vec4(texture2D(tex_global_blur,texc).rgb,texture2D(tex_global_blur,texc).r);
        return;
    }

    vec4 blur_mask_color = vec4(texture2D(tex_blur_mask,pos).r);

    if(blur_mask_color.a <= 40.0/255.0 && global_blur_color.a != 0.0)
    {
        blur_mask_color = vec4(global_blur_color.rgb,global_blur_color.a);
    }
    gl_FragColor = blur_mask_color;
}