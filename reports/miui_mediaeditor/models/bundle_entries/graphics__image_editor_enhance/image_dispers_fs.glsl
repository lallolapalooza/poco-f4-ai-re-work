varying vec2 texc;
varying vec2 texf;

vec3 saturate3f(vec3 v) {
  return vec3(clamp(v.x, 0.0, 1.0), clamp(v.y, 0.0, 1.0), clamp(v.z, 0.0, 1.0));
}
vec3 hue2rgb(float hue) {
  hue = fract(hue);
  vec3 h = saturate3f(vec3(abs(hue * 6.0 - 3.0) - 1.0, 2. - abs(hue * 6. - 2.),
                           2. - abs(hue * 6. - 4.)));
  return h;
}
vec3 rgb2hsl(vec3 c) {
  float cMin = min(min(c.r, c.g), c.b);
  float cMax = max(max(c.r, c.g), c.b);
  float delta = cMax - cMin;
  vec3 hsl = vec3(0., 0., (cMax + cMin) / 2.);
  if (delta != 0.0) {
    if (hsl.z < .5) {
      hsl.y = delta / (cMax + cMin);
    } else {
      hsl.y = delta / (2. - cMax - cMin);
    }
    float deltaR = (((cMax - c.r) / 6.) + (delta / 2.)) / delta;
    float deltaG = (((cMax - c.g) / 6.) + (delta / 2.)) / delta;
    float deltaB = (((cMax - c.b) / 6.) + (delta / 2.)) / delta;
    if (c.r == cMax) {
      hsl.x = deltaB - deltaG;
    } else if (c.g == cMax) {
      hsl.x = (1. / 3.) + deltaR - deltaB;
    } else {
      hsl.x = (2. / 3.) + deltaG - deltaR;
    }
    hsl.x = fract(hsl.x);
  }
  return hsl;
}
vec3 hsl2rgb(vec3 hsl) {
  if (hsl.y == 0.) {
    return vec3(hsl.z);
  } else {
    float b;
    if (hsl.z < .5) {
      b = hsl.z * (1. + hsl.y);
    } else {
      b = hsl.z + hsl.y - hsl.y * hsl.z;
    }
    float a = 2. * hsl.z - b;
    return a + hue2rgb(hsl.x) * (b - a);
  }
}
vec3 change_hue(vec3 color, float hue){
	vec3 hsl = rgb2hsl(color);
	hsl.r += hue / 2.0;
	if(hsl.r > 1.0){
		hsl.r -= 1.0;
	}
	return clamp(hsl2rgb(hsl),0.0,1.0);
}

const mat3 inv_yuv_Mat = mat3(vec3(1, 0.956, 0.621),
						  vec3(1.0, -0.272, -0.647),
						  vec3(1.0, -1.107, 1.705));
const mat3 yuv_Mat = mat3(vec3(0.299, 0.587, 0.114),
						  vec3(0.596, -0.274, -0.321),
						  vec3(0.211, -0.523, 0.311));

mat3 rotateMatrix(float angle) {
    return mat3(vec3(1.0, 0.0, 0.0), 
				vec3(0.0, cos(angle), -sin(angle)), 
				vec3(0.0, sin(angle), cos(angle)));
}

vec3 applyTransformation(vec3 incolor, mat3 trans_Mat) {
	vec3 yuv = incolor * yuv_Mat;
	yuv = trans_Mat * yuv;
	return yuv * inv_yuv_Mat;
}

void main1(){
	// vec4 incolor = texture2D(tex_input, texc);
	// vec3 incolor_change = change_hue(incolor.rgb);
	// vec2 center = vec2(0.5);
	// vec2 texc_d = texc + (texc - center) * dispersion_disperse * 0.025 * dispersion_scale;
	// vec2 texc_d_m = texc + (texc - center) * dispersion_disperse * -0.025 * dispersion_scale;
	// vec3 d_color = texture2D(tex_input, texc_d).rgb;
	// vec3 d_m_color = texture2D(tex_input, texc_d_m).rgb;
	// incolor.r = d_color.r;
	// incolor.g = d_m_color.g;
	// gl_FragColor = vec4(incolor_change,incolor.a);
	vec4 incolor = texture2D(tex_input, texc);
	vec2 texc_d = texf + (texf - dispersion_center) * dispersion_disperse * -0.025 * dispersion_scale;
	vec2 texc_d_m = texf + (texf - dispersion_center) * dispersion_disperse * 0.025 * dispersion_scale;
	texc_d = (texc_d * f_dim - b_offset) / b_dim;
	texc_d_m = (texc_d_m * f_dim - b_offset) / b_dim;
	vec4 color0 = texture2D(tex_input, texc_d);
	vec4 color1 = texture2D(tex_input, texc_d_m);
	// vec3 ocolor = vec3(change_hue(incolor.rgb,dispersion_hue).r,change_hue(color0.rgb,dispersion_hue).g,change_hue(color1.rgb,dispersion_hue).b);
	// ocolor = change_hue(ocolor,-dispersion_hue);
	// gl_FragColor = vec4(ocolor,incolor.a);
	mat3 rotMat = rotateMatrix(dispersion_hue);
  mat3 rotMat_inv = rotateMatrix(-dispersion_hue);
  vec3 result_color = vec3(applyTransformation(incolor.rgb, rotMat).r, applyTransformation(color0.rgb, rotMat).g, applyTransformation(color1.rgb, rotMat).b);
  result_color = applyTransformation(result_color, rotMat_inv);
  gl_FragColor = vec4(result_color.rgb, incolor.a);
}

void main(){
  vec4 incolor = texture2D(tex_input, texc);
  float tex_dis = distance(texf, dispersion_center);
  float dispers_r = tex_dis < dispersion_scale ? dispersion_disperse * tex_dis / dispersion_scale : dispersion_disperse;
  vec2 dispers_offset = texf - dispersion_center;
  vec2 dispers_texc1 = (1.0 + dispers_r * 0.05) * dispers_offset + dispersion_center;
  vec2 dispers_texc2 = (1.0 - dispers_r * 0.05) * dispers_offset + dispersion_center;
  dispers_texc1 = (dispers_texc1 * f_dim - b_offset) / b_dim;
  dispers_texc2 = (dispers_texc2 * f_dim - b_offset) / b_dim;
  vec4 color_disper1 = texture2D(tex_input, dispers_texc1);
  vec4 color_disper2 = texture2D(tex_input, texc);
  vec4 color_disper3 = texture2D(tex_input, dispers_texc2);
  mat3 rotMat = rotateMatrix(dispersion_hue);
  mat3 rotMat_inv = rotateMatrix(-dispersion_hue);
  vec3 result_color = vec3(applyTransformation(color_disper1.rgb, rotMat).r, applyTransformation(color_disper2.rgb, rotMat).g, applyTransformation(color_disper3.rgb, rotMat).b);
  result_color = applyTransformation(result_color, rotMat_inv);
  gl_FragColor = vec4(result_color.rgb, incolor.a);
}


