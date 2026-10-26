void main()
{
    vec4 c = texture2D(tex, vUV).rgba;
    gl_FragColor = c;
}