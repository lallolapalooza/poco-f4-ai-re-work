varying vec2 pose;
varying vec2 uv_origin;
//#define SAMPLE_CNT_8
//#ifdef SAMPLE_CNT_8
varying vec2 sampleuv[8];
//#else
//varying vec2 sampleuv[4];
//#endif
vec3 rgb2hsv(vec3 c) { 
    vec4 K = vec4(0.0, -0.33333, 0.66667, -1.0); 
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g)); 
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r)); 
    float d = q.x - min(q.w, q.y); 
    float e = 1.0e-10; 
    vec3 hsv = vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x); 
    return hsv; 
} 

void main()
{
		vec4 color = texture2D(tex_mask,stf);
#ifdef USE_COLOR_MASK_OUT
		vec3 origin = texture2D(tex_origin,uv_origin).rgb;
		float c1 = texture2D(tex_origin,sampleuv[0]).g;
		float c2 = texture2D(tex_origin,sampleuv[1]).g;
		float c3 = texture2D(tex_origin,sampleuv[2]).g;
		float c4 = texture2D(tex_origin,sampleuv[3]).g;
		float minc = min(origin.g,min(c1,min(c2,min(c3,c4))));
		float maxc = max(origin.g,max(c1,max(c2,max(c3,c4))));
		c1 = texture2D(tex_origin,sampleuv[4]).g;
		c2 = texture2D(tex_origin,sampleuv[5]).g;
		c3 = texture2D(tex_origin,sampleuv[6]).g;
		c4 = texture2D(tex_origin,sampleuv[7]).g;
		vec3 ertou_maskout = texture2D(tex_ertou,stf).rgb;
		minc = min(minc,min(c1,min(c2,min(c3,c4))));
		maxc = max(maxc,max(c1,max(c2,max(c3,c4))));
		float diffc = maxc - minc;
		
		vec3 hsv = rgb2hsv(origin);
		float mask = 1.0;
		//mask out non-skin & darkness
		//
		//mask = 1.0 - smoothstep(0.4,0.5,diffc);
		mask = 1.0 - smoothstep(0.25,0.30,diffc);
		if(minc > 0.18){
			mask = max(mask, ertou_maskout.b);
		}
		//mask = ertou_maskout;
		
		float step_h = step(0.5,hsv.x);
		/*
		*  	\_/
		*/
		float hue_prop = (1.0-smoothstep(0.15,0.18,hsv.x)) * (1.0 - step_h) + smoothstep(0.875,0.9,hsv.x) * step_h;
		float v_prop = smoothstep(0.3,0.35,hsv.z);
		v_prop = max(v_prop,ertou_maskout.g);
		//mask = ertou_maskout.g;
		mask *= hue_prop;
		mask *= v_prop;
		color.w *= mask;
#endif
		gl_FragColor=color;
}