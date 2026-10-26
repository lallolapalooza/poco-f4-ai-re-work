varying vec2 texc;

void main()
{
     gl_FragColor = texture2D(tex_origin,texc * wh + offset);
}