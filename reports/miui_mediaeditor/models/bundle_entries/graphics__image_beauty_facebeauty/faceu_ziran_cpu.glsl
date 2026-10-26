
#ifdef OPENG_ES
vec2 shader_main_ziran(highp vec2 textureCoordinate)
#else
vec2 shader_main_ziran(vec2 textureCoordinate)
#endif
{
    return textureCoordinate;
}