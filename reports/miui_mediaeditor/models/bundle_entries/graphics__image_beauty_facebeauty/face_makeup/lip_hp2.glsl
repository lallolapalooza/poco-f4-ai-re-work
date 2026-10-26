 float ACESFilm_inv(float x)
{
    float a = 2.51;
    float b = 0.03;
    float c = 2.43;
    float d = 0.59;
    float e = 0.14;
    float delta = clamp((x*(a*x+b))/(x*(c*x+d)+e), 0.0, 1.0);
    return 2.0 * x - delta;
}

 float computeHighPassWeight(vec4 image, vec4 blurImage){
     float hPass = 0.0;
    float gray = 0.2126 * image.r + 0.7152 * image.g + 0.0722 * image.b;
    float grayBlur = 0.2126 * blurImage.r + 0.7152 * blurImage.g + 0.0722 * blurImage.b;

    float initValue = abs(gray - grayBlur);
    //apply invers S - curve
    initValue = ACESFilm_inv(initValue);
    hPass = clamp(initValue * u_highPassStrength, 1.0 / 255.0, 1.0);
    //apply invers S - curve (one more.)
    hPass = ACESFilm_inv(hPass);

    return hPass;
}

void main()
{
    vec4 blurImage = texture2D(u_blurTexture, st);
    vec4 image = texture2D(u_inputTexture, st);

    gl_FragColor = vec4(vec3(computeHighPassWeight(image, blurImage)),1.0);

}