varying vec2 texc;

void main()
{
     if(texc.x >= offset.x && texc.x <= offset.x + wh.x && texc.y >= offset.y && texc.y <= offset.y + wh.y){
		vec2 texv = texc - offset;
		texv.x /= wh.x;
		texv.y /= wh.y;
          if(type < 0.5){
               gl_FragColor = texture2D(tex_origin,texv);
          }else{
               gl_FragColor = vec4(vec3(texture2D(tex_origin,texv).a),1.0);
          }
     }
     else{
      gl_FragColor = backcolor; 
     }
}