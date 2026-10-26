varying vec2 texc;

float blendStrongLight(float base, float blend) { 
  float color1 = 2.0 * base * blend; 
  float color2 = 1.0 - (1.0 - base) * (1.0 - blend) * 2.0; 
  return mix(color1, color2, step(0.5, blend)); 
} 

float blendSoftLight(float base, float blend) { 
  float color1 = 2.0 * base * blend + base * base * (1.0 - 2.0 * blend); 
  float color2 = sqrt(base) * (2.0 * blend - 1.0) + 2.0 * base * (1.0 - blend); 
  return mix(color1, color2, step(0.5, blend)); 
} 


void main()
{
    float alpha = texture2D(tex_brown_mask,texc).g;
    vec4 alpha_color = texture2D(tex_sharpenbrown,texc);
    float protect_alpha = alpha_color.b * (1.0 - alpha_color.a);

    vec3 outcolor = texture2D(tex_origin,texc).rgb;
    vec3 oriblur_color = texture2D(tex_oriblur,texc).rgb;   
    vec3 ori_color = texture2D(tex_origin,texc).rgb;
    vec3 sharpen_color = ori_color + (ori_color - oriblur_color) * 0.3;

    gl_FragColor = vec4(outcolor,alpha * protect_alpha);
}