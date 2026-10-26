varying vec2 texc;
varying vec2 texf;

void main(){
    if(texf.x >= offset.x && texf.x <= offset.x + wh.x && texf.y >= offset.y && texf.y <= offset.y + wh.y){
        vec2 texv = texf - offset;
        texv.x /= wh.x;
        texv.y /= wh.y;
        vec3 origin_color = texture2D(tex_origin,texc).rgb;
        vec4 acenaver_color = texture2D(tex_acenaver,texv);
        /* vec3 diff = acenaver_color.rgb - texture2D(tex_raw,texf).rgb;
        vec3 outcolor = origin_color + diff;   
        float alpha = 1.0 - (acenaver_color.a * 0.8);
        gl_FragColor = vec4(mix(origin_color,outcolor,aver_rate * texture2D(tex_aver,texf).a * alpha),1.0); */
        vec4 mask_color = texture2D(tex_protect_manual_mask,texf);
        float aver_alpha = texture2D(tex_aver,texf).a;
		float zhi_alpha = mask_color.a;
		if(zhi_alpha < 1.0){
            aver_alpha = 1.0;
        }

        gl_FragColor = vec4(mix(origin_color,acenaver_color.rgb,acenaver_color.a * aver_alpha),1.0);
    }
    else{
        gl_FragColor = texture2D(tex_origin,texc);
    }
}