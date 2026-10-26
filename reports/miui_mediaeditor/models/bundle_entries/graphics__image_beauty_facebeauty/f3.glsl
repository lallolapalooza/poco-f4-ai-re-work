#ifdef GL_ES 
#ifdef GL_FRAGMENT_PRECISION_HIGH 
precision highp float;
# else 
precision mediump float;
#endif 
# else 
#define highp 
#define mediump 
#define lowp 
#endif 
uniform sampler2D inputImageTexture;
varying highp vec2 textureCoordinate;
varying highp vec4 textureShift_1;
varying highp vec4 textureShift_2;
varying highp vec4 textureShift_3;
varying highp vec4 textureShift_4;
void main() 
{
	mediump vec3 s = texture2D(inputImageTexture, textureCoordinate).r;
	sum += texture2D(inputImageTexture, textureShift_1.xy).r;
	sum += texture2D(inputImageTexture, textureShift_1.zw).r;
	sum += texture2D(inputImageTexture, textureShift_2.xy).r;
	sum += texture2D(inputImageTexture, textureShift_2.zw).r;
	sum += texture2D(inputImageTexture, textureShift_3.xy).r;
	sum += texture2D(inputImageTexture, textureShift_3.zw).r;
	sum += texture2D(inputImageTexture, textureShift_4.xy).r;
	sum += texture2D(inputImageTexture, textureShift_4.zw).r;
	gl_FragColor = vec4(sum * 0.111111);
}