varying vec2 texc;
void main() {
  vec4 textureColor_I = texture2D(tex_mean_I, texc);
  vec4 textureColor_II = texture2D(tex_mean_II, texc);
  vec4 textureColor_p = texture2D(tex_mean_p, texc);
  vec4 colorA = vec4(textureColor_I.rgb, textureColor_p.b);
  vec4 colorB = vec4(textureColor_II.rgb, textureColor_p.a);
  vec4 colorC = vec4(textureColor_I.rgb, textureColor_p.r);
  vec4 colorD = vec4(textureColor_II.rgb, textureColor_p.g);
  vec4 resultH = colorD - colorA * colorC;
  vec4 resultI = colorB - colorA * colorA;
  gl_FragColor = resultH / (resultI + 0.01);
}
