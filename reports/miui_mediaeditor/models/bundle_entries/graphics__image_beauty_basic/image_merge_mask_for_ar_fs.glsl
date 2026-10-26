varying vec2 texc;

void main(){
    if(texc.x >= offset.x && texc.x <= offset.x + wh.x && texc.y >= offset.y && texc.y <= offset.y + wh.y){
        vec2 texv = texc - offset;
        texv.x /= wh.x;
        texv.y /= wh.y;

        int face_idx = int(texture2D(tex_label, texc).r * 255.0 + 0.1);
        if(face_idx == int(face_id + 0.1) + 1)
        {
            gl_FragColor = texture2D(tex_newmask,texv);
        }
        else{
            gl_FragColor = texture2D(tex_lastmask,texc);
        }
    }
    else{
        gl_FragColor = texture2D(tex_lastmask,texc);
    }
}