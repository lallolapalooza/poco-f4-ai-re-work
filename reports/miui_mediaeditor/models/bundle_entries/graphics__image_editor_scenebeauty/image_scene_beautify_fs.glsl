varying vec2 texc;
varying vec2 texf;
vec4 lut4x4(vec4 textureColor, sampler2D lut) {
  float blueColor = textureColor.b * 15.0;
  vec2 quad1;
  quad1.y = max(min(4.0, floor(floor(blueColor) / 4.0)), 0.0);
  quad1.x = max(min(4.0, floor(blueColor) - (quad1.y * 4.0)), 0.0);
  vec2 quad2;
  quad2.y = max(min(floor(ceil(blueColor) / 4.0), 4.0), 0.0);
  quad2.x = max(min(ceil(blueColor) - (quad2.y * 4.0), 4.0), 0.0);
  vec2 texPos1;
  texPos1.x =
      (quad1.x * 0.25) + 0.5 / 64.0 + ((0.25 - 1.0 / 64.0) * textureColor.r);
  texPos1.y =
      (quad1.y * 0.25) + 0.5 / 64.0 + ((0.25 - 1.0 / 64.0) * textureColor.g);
  vec2 texPos2;
  texPos2.x =
      (quad2.x * 0.25) + 0.5 / 64.0 + ((0.25 - 1.0 / 64.0) * textureColor.r);
  texPos2.y =
      (quad2.y * 0.25) + 0.5 / 64.0 + ((0.25 - 1.0 / 64.0) * textureColor.g);
  vec4 newColor1 = texture2D(lut, texPos1);
  vec4 newColor2 = texture2D(lut, texPos2);
  vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
  return newColor;
}
void main() {
  vec4 color = vec4(texture2D(tex_input, texc).rgb, 1.0);
  vec4 newColor = lut4x4(color, tex_lut);
  gl_FragColor = mix(color, vec4(newColor.rgb, color.w), strength);
}