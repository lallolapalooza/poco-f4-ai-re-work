varying vec2 texc;

float blendSuperposition(float base, float blend) {
    float color1 = base * blend * 2.0;
    float color2 = 1.0 - (1.0 - base)*(1.0 - blend) * 2.0;
    return mix(color1, color2, step(0.5, base));
}

vec3 hsv2rgb(vec3 c) {
    const vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

vec3 rgb2hsv(vec3 c) {
    const vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));

    float d = q.x - min(q.w, q.y);
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + 0.001)), d / (q.x + 0.001), q.x);
}

vec3 bright_curve(vec3 color,float rate)
{
    float a1 = -0.0119802, b1 = -0.258122, c1 = 0.944906, d1 = 0.191047;
	float a2 = -0.0125641, b2 = -0.148298, c2 = 0.968544, d2 = 0.192318;
	float curveKeyPoints0 = -1.0;
	float curveKeyPoints1 = 0.5;
	float curveKeyPoints2 = 1.0;

    float x = color.r * 2.0 - 1.0;
    float new_o_r = 0.0;
    if(x >= curveKeyPoints0 && x < curveKeyPoints1)
    {
        float y = a1 * pow(x,3.0) + b1 * pow(x,2.0) + c1 * x + d1;
        new_o_r = (y + 1.0) / 2.0;
    }
    else
    {
        float y = a2 * pow(x,3.0) + b2 * pow(x,2.0) + c2 * x + d2;
        new_o_r = (y + 1.0) / 2.0;
    }

    x = color.g * 2.0 - 1.0;
    float new_o_g = 0.0;
    if(x >= curveKeyPoints0 && x < curveKeyPoints1)
    {
        float y = a1 * pow(x,3.0) + b1 * pow(x,2.0) + c1 * x + d1;
        new_o_g = (y + 1.0) / 2.0;
    }
    else
    {
        float y = a2 * pow(x,3.0) + b2 * pow(x,2.0) + c2 * x + d2;
        new_o_g = (y + 1.0) / 2.0;
    }

    x = color.b * 2.0 - 1.0;
    float new_o_b = 0.0;
    if(x >= curveKeyPoints0 && x < curveKeyPoints1)
    {
        float y = a1 * pow(x,3.0) + b1 * pow(x,2.0) + c1 * x + d1;
        new_o_b = (y + 1.0) / 2.0;
    }
    else
    {
        float y = a2 * pow(x,3.0) + b2 * pow(x,2.0) + c2 * x + d2;
        new_o_b = (y + 1.0) / 2.0;
    }
    vec3 new_color = vec3(new_o_r,new_o_g,new_o_b);
    return color + (new_color - color) * rate;
}

vec3 saturation_change(vec3 source, float saturation) {
    vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);
    float luminance = dot(source.rgb, luminanceWeighting);
    vec3 greyScaleColor = vec3(luminance);
    return mix(greyScaleColor, source.rgb, saturation);
}

vec3 temperature_change(vec3 source, float temperature) {
    mat3 RGBtoYIQ = mat3(0.299, 0.587, 0.114, 0.596, -0.274, -0.322, 0.212, -0.523, 0.311);
    mat3 YIQtoRGB = mat3(1.0, 0.956, 0.621, 1.0, -0.272, -0.647, 1.0, -1.105, 1.702);
    vec3 warmFilter = vec3(0.93, 0.54, 0.0);
    vec3 yiq = RGBtoYIQ * source.rgb;
    yiq.b = clamp(yiq.b, -0.5226, 0.5226);
    vec3 rgb = YIQtoRGB * yiq;
    float floatA = (rgb.r < 0.5 ? (2.0 * rgb.r * warmFilter.r) : (1.0 - 2.0 * (1.0 - rgb.r) * (1.0 - warmFilter.r)));
    float floatB = (rgb.g < 0.5 ? (2.0 * rgb.g * warmFilter.g) : (1.0 - 2.0 * (1.0 - rgb.g) * (1.0 - warmFilter.g)));
    float floatC = (rgb.b < 0.5 ? (2.0 * rgb.b * warmFilter.b) : (1.0 - 2.0 * (1.0 - rgb.b) * (1.0 - warmFilter.b)));
    vec3 processed = vec3(floatA, floatB, floatC);
    return mix(rgb, processed, temperature);
}

vec3 brightness_contrast_change(vec3 source,float brightness,float contrast){
    float PI = 3.1415926;
	float B = brightness / 255.0;
	float c = contrast / 255.0;
	float k = tan((45.0 + 44.0 * c) / 180.0 * PI);
	vec3 colorrbg = ((source*255.0 - 127.5 * (1.0 - B)) * k + 127.5 * (1.0 + B)) / 255.0;
    return colorrbg;
}

vec3 bright_dark(vec3 source,float bright){
	vec3 gray_dot = vec3(0.299,0.587,0.114);
	float gray = dot(source,gray_dot);
	source += bright * (1.0 - gray);
	return source;
}

void main()
{
    vec3 source_color = texture2D(tex_source,texc).rgb;
    vec3 outcolor = saturation_change(source_color,0.68);
    outcolor = temperature_change(outcolor,0.08);
    outcolor = brightness_contrast_change(outcolor,0.0,27.0);
    outcolor = bright_dark(outcolor,0.15);
    /* vec3 destination_color = texture2D(tex_destination,texc).rgb;
    float r = blendSuperposition(source_color.r,destination_color.r);
    float g = blendSuperposition(source_color.g,destination_color.g);
    float b = blendSuperposition(source_color.b,destination_color.b);
    vec3 outcolor = vec3(r,g,b);
    outcolor = clamp(outcolor,0.0,1.0);
    outcolor = mix(source_color,outcolor,0.3); */
    /* vec3 a = rgb2hsv(outcolor);
    vec3 m = a + vec3(0.0,0.0,0.1);
    outcolor = hsv2rgb(m); */
    /* outcolor = bright_curve(outcolor,1.0);
    outcolor = clamp(outcolor,0.0,1.0); */

    gl_FragColor = vec4(outcolor,1.0);
}