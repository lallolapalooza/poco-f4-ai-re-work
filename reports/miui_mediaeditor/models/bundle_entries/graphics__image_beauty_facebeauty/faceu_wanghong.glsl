
float pow0145(float x)
{
	//[0.4990 3.0376 -11.2808 22.0536 -20.4465 7.1500]
	return 0.4990+x*(3.0376+x*(-11.2808+x*(22.0536+x*(-20.4465+x*7.1500))));
}

vec2 faceStretch(vec2 textureCoord, vec2 originPosition, vec2 targetPosition, float radius)
{
    vec2 direction = targetPosition - originPosition;
    float lengthA = length(direction);
    float lengthB = min(lengthA, radius);
    direction *= lengthB / lengthA;
    float infect = distance(textureCoord, originPosition)/radius;
    infect = clamp(1.0-infect,0.0,1.0);

    return direction * infect;
}

#ifdef OPENG_ES
vec2 shader_main_wanghong(highp vec2 textureCoordinate)
#else
vec2 shader_main_wanghong(vec2 textureCoordinate)
#endif
{
    vec2 newCoord = textureCoordinate;

    vec2 eyea = p_eyea*xy_a*image_ratio_to_std_size_inv;
    vec2 eyeb = p_eyeb*xy_a*image_ratio_to_std_size_inv;
    vec2 left = p_left*xy_a*image_ratio_to_std_size_inv;
    vec2 right = p_right*xy_a*image_ratio_to_std_size_inv;

    float aspect = 0.5625;
    float weight = 0.0;
    float face_width = distance(eyea,eyeb);

    float eyedis = face_width;

    vec2 pLeftEye = p_eyea+(p_eyeb-p_eyea)*0.035;
    vec2 pRightEye = p_eyeb+(p_eyea-p_eyeb)*0.035;

    //eye1
    float eyeRadius = face_width*0.3*myparameter1*0.9;//distance(left, right)*0.32;//0.3;
    float dis_eye1;
    if(abs(eyea.x-eyeb.x) > abs(eyea.y-eyeb.y))
    {
        dis_eye1 = distance(vec2(newCoord.x*aspect,newCoord.y), vec2(p_eyea.x*aspect,p_eyea.y));
    }
    else
    {
        dis_eye1 = distance(vec2(newCoord.x,newCoord.y*aspect), vec2(p_eyea.x,p_eyea.y*aspect));
    }
    if(dis_eye1 <= eyeRadius)
    {
        weight = pow0145(dis_eye1 / eyeRadius); //0.16
        newCoord = pLeftEye+(newCoord-pLeftEye)*weight;
    }

    // eye2
    float dis_eye2;
    if(abs(eyea.x-eyeb.x) > abs(eyea.y-eyeb.y))
    {
        dis_eye2 = distance(vec2(newCoord.x*aspect,newCoord.y), vec2(p_eyeb.x*aspect,p_eyeb.y));
    }
    else
    {
        dis_eye2 = distance(vec2(newCoord.x,newCoord.y*aspect), vec2(p_eyeb.x,p_eyeb.y*aspect));
    }
    if(dis_eye2 <= eyeRadius)
    {
        weight = pow0145(dis_eye2 / eyeRadius); //0.16
        newCoord = pRightEye+(newCoord-pRightEye)*weight;
    }

    float radius = face_width*1.10;
    vec2 targetleftF = p_nose + (p_faceleft - p_nose) * 0.965;//0.96;
    vec2 leftFplus = faceStretch(newCoord, p_faceleft, targetleftF, radius);
    newCoord = newCoord - leftFplus*myparameter2;

    vec2 targetrightF = p_nose + (p_faceright - p_nose) * 0.965;//0.96;
    vec2 rightFplus = faceStretch(newCoord, p_faceright, targetrightF, radius);
    newCoord = newCoord - rightFplus*myparameter2;

    radius = face_width*1.5;
    vec2 targetchin = p_nose + (p_chin - p_nose) * 0.87;//0.9;
    vec2 chinplus = faceStretch(newCoord, p_chin, targetchin, radius);
    newCoord = newCoord - chinplus*myparameter2;

    vec2 chinCenter = p_nose;// + (p_chin - p_nose) * 0.7;

    radius = face_width*1.2;
    vec2 targetleftC = chinCenter + (p_chinleft - chinCenter) * 0.94;//0.95;
    vec2 leftCplus = faceStretch(newCoord, p_chinleft, targetleftC, radius);
    newCoord = newCoord - leftCplus*myparameter2;

    vec2 targetrightC = chinCenter + (p_chinright - chinCenter) * 0.94;//0.95;
    vec2 rightCplus = faceStretch(newCoord, p_chinright, targetrightC, radius);
    newCoord = newCoord - rightCplus*myparameter2;

    chinCenter = p_nose + (p_chin - p_nose) * 0.7;

    radius = face_width*1.2;
    targetleftC = chinCenter + (p_chinleft_u - chinCenter) * 0.89;//0.9;
    leftCplus = faceStretch(newCoord, p_chinleft_u, targetleftC, radius);
    newCoord = newCoord - leftCplus*myparameter2;

    targetrightC = chinCenter + (p_chinright_u - chinCenter) * 0.89;//0.9;
    rightCplus = faceStretch(newCoord, p_chinright_u, targetrightC, radius);
    newCoord = newCoord - rightCplus*myparameter2;

    return textureCoordinate+(newCoord-textureCoordinate)*myparameter;
}