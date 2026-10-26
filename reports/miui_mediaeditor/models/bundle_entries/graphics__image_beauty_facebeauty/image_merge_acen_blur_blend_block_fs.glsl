varying vec2 texc;
varying vec2 texf;

void main(){
    vec3 origin_color = texture2D(tex_origin,texc).rgb;
    vec3 acenblur_color = texture2D(tex_acenblur,texc).rgb;
    gl_FragColor = vec4(mix(origin_color,acenblur_color,blur_rate * blur_rate_scale),1.0);
}