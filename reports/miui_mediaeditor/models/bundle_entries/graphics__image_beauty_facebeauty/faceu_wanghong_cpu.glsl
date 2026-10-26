#ifdef OPENG_ES
vec2 shader_main_wanghong(highp vec2 textureCoordinate)
#else
vec2 shader_main_wanghong(vec2 textureCoordinate)
#endif
{
    return textureCoordinate;
}