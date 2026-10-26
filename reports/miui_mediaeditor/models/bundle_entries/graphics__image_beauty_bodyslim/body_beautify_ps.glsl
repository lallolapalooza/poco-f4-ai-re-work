
vec4 real_main(vec2 st) {
    vec4 C = texture2D(tex, st);//vec4(st, 0.0, 1.0);
    vec2 tmp =  vec2(st.x, st.y);
    if(flipyf > 0.5) {
        tmp = vec2(st.x, 1.0 - st.y);
    } else {
        if(oriF == 180.0) {
            tmp = vec2(1.0 - st.x, 1.0 - st.y);
        } else if(oriF == 270.0) {
            tmp = vec2(1.0 - st.y, st.x);
        } else if(oriF == 90.0) {
            tmp = vec2(st.x, 1.0 - st.y);
        }
    }
    tmp = tmp * frameF;
    if(distance(tmp, headF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }
    if(distance(tmp, armRF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }
    if(distance(tmp, armLF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }
    if(distance(tmp, legRTF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }
    if(distance(tmp, legLTF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }
    if(distance(tmp, legRMF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }
    if(distance(tmp, legLMF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }
    if(distance(tmp, legRBF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }
    if(distance(tmp, legLBF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, legLBF) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pWaistL0) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pWaistL1) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pWaistL2) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pWaistR0) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pWaistR1) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pWaistR2) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pSwanL0) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pSwanL1) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pSwanL2) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pSwanR0) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pSwanR1) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }

    if(distance(tmp, pSwanR2) < 5.0 && debugF > 0.5) {
        C = vec4(1.0, 0.0, 0.0, 1.0);
    }


    return C;
}


void main() {

#ifdef POINTS_DEBUG
    gl_FragColor = real_main(st_warp);
#else
    gl_FragColor = texture2D(tex, st_warp);
#endif
}
