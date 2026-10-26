#ifdef OPENG_ES
vec2 shader_main_nvshen(highp vec2 textureCoordinate)
#else
vec2 shader_main_nvshen(vec2 textureCoordinate)
#endif
{
    return textureCoordinate;
}