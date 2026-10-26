vec4 shader_main_s_image_mix(vec2 st, vec4 C)
{
    if(is_android > 0.5)
        return texture2D(tex_result,vec2(st.x,1.0 - st.y));
    else
        return texture2D(tex_result,vec2(st.x,st.y));
}