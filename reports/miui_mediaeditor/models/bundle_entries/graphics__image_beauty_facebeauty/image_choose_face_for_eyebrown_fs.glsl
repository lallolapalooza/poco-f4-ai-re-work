varying vec2 texc;

void main()
{
     vec2 pos = texc * wh + offset;
     pos /= wh;
     pos = pos * wh_eb + offset_eb;
     gl_FragColor = texture2D(tex_origin,pos);
}