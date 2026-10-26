float overlayBlend(float base, float blend) {
    return base<0.5?(2.0*base*blend):(1.0-2.0*(1.0-base)*(1.0-blend));
}

vec3 overlayBlend(vec3 base, vec3 blend) {
    return vec3(overlayBlend(base.r,blend.r),overlayBlend(base.g,blend.g),overlayBlend(base.b,blend.b));
}

vec3 highlightMerge(vec3 color, float highlight) {
//    if(u_highlightMergeAlterMode){
        return clamp(vec3(1.0) - (vec3(1.0) - color) * vec3(1.0 - highlight), 0.0, 1.0);
//    }
   // return clamp(color + vec3(highlight), 0.0, 1.0);
}

float PerceptualRoughnessToSpecPower(float perceptualRoughness) {
    float m = perceptualRoughness * perceptualRoughness;
    float sq = max(1e-4, m*m);
    float n = (2.0 / sq) - 2.0;
    n = max(n, 1e-4);
    return n;
}

void main() {
    vec4 origin = texture2D(tex_origin, st1);
    vec4 lipMask = texture2D(tex_mask, st2);
    vec4 glossed = texture2D(tex_glossed, st2);
    float gray = 0.4;
    vec4 resultColor = origin;
    float lipOpacity = clamp(lipMask.a * u_lipMaskStrength, 0.0, 1.0);
    vec3 color = vec3(0.5);
    //vec3 color2;

    if (lipOpacity > 0.1) {
        //  float blueColor = color.b * 15.0;
        //  vec2 quad1;
        //  vec2 quad2;
        //  vec2 texPos1;
        //  vec2 texPos2;
        //  vec4 newColor1;
        //  vec4 newColor2;
        // quad1.y = floor(floor(blueColor) * 0.25);
        // quad1.x = floor(blueColor) - (quad1.y * 4.0);
        // quad2.y = floor(ceil(blueColor) * 0.25);
        // quad2.x = ceil(blueColor) - (quad2.y * 4.0);
        // texPos2 = color.rg * 0.234375 + 0.0078125;
        // texPos1 = quad1 * 0.25 + texPos2;
        // texPos2 = quad2 * 0.25 + texPos2;
       // newColor1 = texture2D(u_lookUp, texPos1);
        //newColor2 = texture2D(u_lookUp, texPos2);
        //color2 = mix(newColor1.rgb, newColor2.rgb, fract(blueColor));
        //color = mix(color, color2, lipOpacity);

        //compute blending..
        //color2 = overlayBlend(color, vec3(gray));
        //color = mix(color, color2, lipOpacity);
        //color = vec3(0.0,0.0,0.0);

        //test..
        float finalHighlight = glossed.g;
        if(u_usesPerceptualRoughness > 0.5) {
            float specularPower = PerceptualRoughnessToSpecPower (u_roughness);
            float specularTerm = ((specularPower + 1.0) * pow(glossed.g, specularPower));
            finalHighlight = specularTerm * glossed.g * u_highlightScale;
        }

        //specular
        color = highlightMerge(color, finalHighlight);

        resultColor = vec4(mix(origin.rgb, color, clamp(1.0 , 0.0, 1.0)), 1.0);
        //resultColor = vec4(color,1.0);
        resultColor = vec4(resultColor.rgb * 0.5 + 0.5 , 1.0);
        //resultColor = vec4(lipOpacity,lipOpacity,lipOpacity,1.0);
    } else {
        resultColor = vec4(0.5, 0.5, 0.5, 0.0);
    }

    gl_FragColor = resultColor;
}