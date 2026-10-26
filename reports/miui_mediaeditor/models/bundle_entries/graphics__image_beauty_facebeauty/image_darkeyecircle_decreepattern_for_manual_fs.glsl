varying vec2 texc;
varying vec2 texf;

void main() {
  vec3 oColor = texture2D(tex_origin, texc).rgb;
  vec3 outcolor = oColor.rgb;
  vec4 slightblur_color = texture2D(tex_lightblur, texc);
  vec4 heavyblur_color = texture2D(tex_heavyblur, texc);
  vec3 sbColor = slightblur_color.rgb;
  vec3 hbColor = heavyblur_color.rgb;

  vec3 shdif = clamp((hbColor - sbColor) * 1.4/*  + 0.05 * hbColor */, 0.0, 0.3);     
  vec3 oColorTemp = min(oColor + shdif, 1.0);
  shdif = oColorTemp;
  shdif = min(shdif, max(max(oColor, hbColor), oColorTemp));
  outcolor = mix(oColor, shdif, 1.0);
  gl_FragColor = vec4(outcolor, texture2D(tex_mask,texc).r);
}