varying vec2 texc;
// varying vec4 sampleuv[5];

// const float f = 1. / .04;
// const float j = .5 * f * f;
// float g(vec3 a) { return dot(a, vec3(.298839, .586811, .11435)); }
// void main() {
//   float b = 0.;
//   vec3 h = vec3(0.);
//   vec3 k = texture2D(tex_origin, texc).rgb;
//   float l = g(k);
//   float c;
//   float d;
//   vec3 e;
//   for (int a = -6; a <= 6; a++) {
//     if(type < 0.5){
//         e = texture2D(tex_origin, texc + vec2(float(a) * delta * 10.0, 0.)).rgb;
//     }else{
//         e = texture2D(tex_origin, texc + vec2(0., float(a) * delta * 10.0)).rgb;
//     }   
//     c = g(e) - l;
//     d = arrfloat_kernel[6 + a] * exp(-(c * c * j));
//     h += e * d;
//     b += d;
//   }
//   gl_FragColor = vec4(h / b, b / i);
// }

//varying vec2 texc;
//uniform float delta;
//uniform float kernel[15];
// const float i = float(7 * 2 + 1);
// const float f = 1. / .04;
// const float j = .5 * f * f;
// float g(in vec3 a) { return dot(a, vec3(.298839, .586811, .11435)); }
// void main() {
//  float b = 0.;
//  vec3 h = vec3(0.);
//  vec3 k = texture2D(tex_origin, texc.xy).rgb;
//  float l = g(k);
//  float c;
//  float d;
//  vec3 e;
//  for (int a = -7; a <= 7; a++) {
//     if(type < 0.5){
//         e = texture2D(tex_origin, texc + vec2(float(a) * delta, 0.)).rgb;
//     }else{
//         e = texture2D(tex_origin, texc + vec2(0., float(a) * delta)).rgb;
//     }   
//    c = g(e) - l;
//    d = arrfloat_kernel[7 + a] * exp(-(c * c * j));
//    h += e * d;
//    b += d;
//  }
//  gl_FragColor = vec4(h / b, b / i);
// }

// float weight_sum = 0.0;
// vec3 sum = vec3(0.0);
// void GaussianBlurCumulate(vec4 suv,float weight,float l) {
//     float pi = 3.1415916;
//     vec4 color = texture2D(tex_origin, suv.xy);
//     float c = g(color.rgb) - l;
//     sum += color.rgb * weight * exp(-(c * c * j));
//     weight_sum += weight * exp(-(c * c * j));
//     color = texture2D(tex_origin, suv.zw);
//     c = g(color.rgb) - l;
//     sum += color.rgb * weight * exp(-(c * c * j));
//     weight_sum += weight * exp(-(c * c * j));
// }

// void main() {
//     vec4 color0 = texture2D(tex_origin, texc);
//     vec4 colorlasat = texture2D(tex_last, texc);
//     float l = g(color0.rgb);
//     if(seg_num > 0.5){
//         sum = colorlasat.rgb;
//         weight_sum = colorlasat.a;
//     }
//     for (int i = 0; i < 5; i++) {
//         GaussianBlurCumulate(sampleuv[i] * r + vec4(texc,texc), float(arrfloat_weight[i + 1]), l);
//     }
//     if(seg_num < 0.5){
//         float weight = float(arrfloat_weight[0]);  
//         sum += color0.rgb * weight;
//         weight_sum += weight;
//     }
//     gl_FragColor = vec4(sum,weight_sum);
// }

// uniform vec2 delta;
// uniform vec2 blur;
// uniform vec2 spread;
// uniform vec2 scale;
float h(vec3 b, float a) {
  return fract(sin(dot(gl_FragCoord.xyz + a, b)) * 43758.5453 + a);
}
void main() {
  vec2 e = (texc.xy - .5) / scale + .5;
  float i = h(vec3(12.9898, 78.233, 151.7182), 0.) - .5;
  vec2 j = delta * blur.x;
  vec2 k = delta * blur.y;
  vec3 l = vec3(1. - spread.x);
  float m = 1. - spread.y;
  float b, c, f = 0.;
  vec4 a, g = vec4(0.);
  for (float d = -12.; d <= 12.; d++) {
    b = (d + i) / 12.;
    a = vec4(texture2D(tex_origin, e + j * b).rgb,
             texture2D(tex_origin, e + k * b).a);
    a.rgb = pow(a.rgb, l);
    a.a = pow(a.a, m);
    c = 1. - abs(b);
    g += a * c;
    f += c;
  }
  gl_FragColor = vec4(g / f);
    //gl_FragColor = texture2D(tex_origin,texc);
}