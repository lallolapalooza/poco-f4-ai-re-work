varying vec2 texc;

void main() {
    float alpha = texture2D(tex_lastmask,texc).r + texture2D(tex_newmask,texc).r;
    //alpha = clamp(alpha,0.0,1.0);
    gl_FragColor = vec4(vec3(alpha),1.0);
}