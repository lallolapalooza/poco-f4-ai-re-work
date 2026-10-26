varying vec2 texc;
vec3 RGB_To_YCbCr(vec3 rgb) 
{
	float Y = float(0.299 * rgb.r + 0.587 * rgb.g + 0.114 * rgb.b);
	float Cb = 0.564 * (rgb.b - Y);
	float Cr = 0.713 * (rgb.r - Y);
	return vec3(Cb, Cr, Y);
}
void main() {
  float eX = 0.0;
  float eY = 0.0;
  for(int i = 0; i < 9; i++)
  {
      float texColor = RGB_To_YCbCr(texture2D(tex_in,texc + arrvec2_BOX[i] * wh_offset).rgb).z;
      eX += texColor * arrfloat_VES_SOBEL_Gx[i];
      eY += texColor * arrfloat_VES_SOBEL_Gy[i];
  }
  gl_FragColor = vec4(1.0 - (abs(eX) + abs(eY)));
}