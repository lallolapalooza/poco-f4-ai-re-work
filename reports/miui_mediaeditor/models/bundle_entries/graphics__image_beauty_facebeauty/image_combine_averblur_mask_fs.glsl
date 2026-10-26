varying vec2 texc;

void main()
{
    vec3 skin_color = texture2D(tex_skin,texc).rgb;
    vec3 eye_color = texture2D(tex_eye,texc).rgb;
    vec3 brown_color = texture2D(tex_brown,texc).rgb;
    vec3 mouth_color = texture2D(tex_mouth,texc).rgb;
    vec3 outcolor = clamp(skin_color - eye_color,0.0,1.0);
    outcolor = clamp(outcolor - brown_color,0.0,1.0);
    outcolor = clamp(outcolor - mouth_color,0.0,1.0);
    gl_FragColor = vec4(outcolor,1.0);
}