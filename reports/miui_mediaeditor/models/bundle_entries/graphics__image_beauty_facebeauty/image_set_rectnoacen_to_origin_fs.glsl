varying vec2 texc;

void main()
{
     if(texc.x >= offset.x && texc.x <= offset.x + wh.x && texc.y >= offset.y && texc.y <= offset.y + wh.y){
		vec2 texv = texc - offset;
		texv.x /= wh.x;
		texv.y /= wh.y;
        gl_FragColor = texture2D(tex_noacen,texv);
     }
     else{
        gl_FragColor = texture2D(tex_origin,texc);
     }
}