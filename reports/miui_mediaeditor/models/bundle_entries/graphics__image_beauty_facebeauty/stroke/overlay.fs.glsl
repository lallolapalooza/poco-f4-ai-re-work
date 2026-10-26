void main() {
  vec4 color = texture2D(tex, st);
  vec4 stroke = texture2D(tex_stroke, st);
  vec3 ret = mix(color.rgb, stroke.rgb, stroke.a * timingIntensity);
  gl_FragColor = vec4(ret, color.a);
}