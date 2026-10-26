varying vec2 texc;

void main(){
    if(texc.x >= offset.x && texc.x <= offset.x + wh.x && texc.y >= offset.y && texc.y <= offset.y + wh.y){
        vec2 texv = texc - offset;
        texv.x /= wh.x;
        texv.y /= wh.y;
        if(type < 0.5)
            gl_FragColor = texture2D(tex_newmask,texv);
        else if(type < 1.5)
            gl_FragColor = vec4(texture2D(tex_lastmask,texc).rgb,1.0 - texture2D(tex_newmask,texv).r);
        else if(type < 2.5){
            vec4 last_color = texture2D(tex_lastmask,texc);
            vec4 new_color = texture2D(tex_newmask,texv);
            gl_FragColor = vec4(mix(last_color.rgb,new_color.rgb,new_color.a),1.0);
            //gl_FragColor = new_color;
        }else{
            vec4 last_color = texture2D(tex_lastmask,texc);
            vec4 new_color = texture2D(tex_newmask,texv);
            gl_FragColor = vec4(max(last_color.rgb,new_color.rgb),1.0);
        }
    }
    else{
        gl_FragColor = texture2D(tex_lastmask,texc);
    }
}