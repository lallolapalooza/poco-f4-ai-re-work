varying vec2 texc;

vec3 RGB_To_YCbCr(vec3 rgb) 
{
	float Y = float(0.299 * rgb.r + 0.587 * rgb.g + 0.114 * rgb.b);
	float Cb = 0.564 * (rgb.b - Y);
	float Cr = 0.713 * (rgb.r - Y);
	return vec3(Cb, Cr, Y);
}

vec3 YCbCr_To_RGB(vec3 YCbCr) 
{
	float R = YCbCr.z + 1.402 * YCbCr.y;
	float G = YCbCr.z - 0.334 * YCbCr.x - 0.714 * YCbCr.y;
	float B = YCbCr.z + 1.772 * YCbCr.x;
	return vec3((min(1.0, max(0.0, R))), (min(1.0, max(0.0, G))), (min(1.0, max(0.0, B))));
}

void main() {
  vec4 in_color = texture2D(tex_in,texc);
  vec4 smooth_color = texture2D(tex_smooth,texc);
  
  vec3 color_mix = mix(in_color.rgb, smooth_color.rgb, smooth_value);
  vec3 yuv_color = RGB_To_YCbCr(color_mix);
  
  float result_color = clamp(1.0 - abs(yuv_color.z - value) / range, 0.0, 1.0);
  if(invertion > 0.5){
     result_color = 1.0 - result_color;
  }

  gl_FragColor = vec4(mix(in_color.rgb,vec3(1.0,0.0,0.0),result_color),result_color);
}