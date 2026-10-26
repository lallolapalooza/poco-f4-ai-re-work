varying vec2 texc;
varying vec2 texf;

void main() {
  vec3 oColor = texture2D(tex_origin, texc).rgb;
  vec3 outcolor = oColor.rgb;

  vec3 scaledColor = texture2D(tex_inputScaled, texc).rgb;
  vec3 scaledBlurColor = texture2D(tex_inputScaledBlur, texc).rgb;
  float mask_alpha = texture2D(tex_manual_mask, texf).r;
  mask_alpha = clamp(mask_alpha,0.0,1.0);
  vec3 imDiff = clamp((scaledBlurColor - scaledColor) * 1.4 + 0.05 * scaledBlurColor, 0.0, 0.3);  
  imDiff = min(oColor + imDiff, 1.0); 
  //vec3 blurColor = texture2D(tex_blur,texf).rgb;
  //imDiff = mix(imDiff,blurColor,mask_alpha / 3.0);
  outcolor = mix(outcolor, imDiff, mask_alpha * 0.8 * 0.8);

  gl_FragColor = vec4(outcolor, 1.0);
}