varying vec2 pose;
varying vec2 uv_origin;
//#define SAMPLE_CNT_8
//#ifdef SAMPLE_CNT_8
varying vec2 sampleuv[8];
//#else
//varying vec2 sampleuv[4];
//#endif
void main()
{
	gl_Position=vec4(Ps.xy*2.0-1.0,0.0,1.0);
	stf = st;
	uv_origin = st2;
	pose = Ps;
	float offset_scale = 1.5;
	sampleuv[0] = (arrvec2_sampleDirs[0] * offset_scale + st2);
	sampleuv[1] = (arrvec2_sampleDirs[1] * offset_scale + st2);
	sampleuv[2] = (arrvec2_sampleDirs[2] * offset_scale + st2);
	sampleuv[3] = (arrvec2_sampleDirs[3] * offset_scale + st2);
	//#ifdef SAMPLE_CNT_8
	sampleuv[4] = (arrvec2_sampleDirs[4] * offset_scale + st2);
	sampleuv[5] = (arrvec2_sampleDirs[5] * offset_scale + st2);
	sampleuv[6] = (arrvec2_sampleDirs[6] * offset_scale + st2);
	sampleuv[7] = (arrvec2_sampleDirs[7] * offset_scale + st2);
	//#endif
	
}