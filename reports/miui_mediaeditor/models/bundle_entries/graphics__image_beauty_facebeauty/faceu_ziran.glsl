
float pow013(float x)
{
	//[0.5455 2.7262 -10.0146 19.4737 -18.0126 6.2931]
	return 0.5455+x*(2.7262+x*(-10.0146+x*(19.4737+x*(-18.0126+x*6.2931))));
}

vec2 faceStretch(vec2 textureCoord, vec2 originPosition, vec2 targetPosition, float radius)
{
    vec2 direction = targetPosition - originPosition;
    float lengthA = length(direction);
    float lengthB = min(lengthA, radius);
    direction *= lengthB / lengthA;
    float infect = distance(textureCoord, originPosition)/radius;
    infect = clamp(1.0-infect,0.0,1.0);
    //infect = pow(infect, curve);

    return direction * infect;
}

#ifdef OPENG_ES
vec2 shader_main_ziran(highp vec2 textureCoordinate)
#else
vec2 shader_main_ziran(vec2 textureCoordinate)
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

    // eye1
    float eyeRadius = distance(left, right)*0.36*myparameter1*1.5;
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
        weight = pow013(dis_eye1 / eyeRadius);
        newCoord = p_eyea +(newCoord - p_eyea) * weight;
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
        weight = pow013(dis_eye2 / eyeRadius);
        newCoord = p_eyeb +(newCoord - p_eyeb) * weight;
    }

    float radius = face_width*1.0;
    vec2 targetleftF = p_nose + (p_faceleft - p_nose) * 0.97;
    vec2 leftFplus = faceStretch(newCoord, p_faceleft, targetleftF, radius);
    newCoord = newCoord - leftFplus*myparameter2;

    vec2 targetrightF = p_nose + (p_faceright - p_nose) * 0.97;
    vec2 rightFplus = faceStretch(newCoord, p_faceright, targetrightF, radius);
    newCoord = newCoord - rightFplus*myparameter2;

    targetleftF = p_nose + (p_faceleft_d - p_nose) * 0.98;
    leftFplus = faceStretch(newCoord, p_faceleft_d, targetleftF, radius);
    newCoord = newCoord - leftFplus*myparameter2;

    targetrightF = p_nose + (p_faceright_d - p_nose) * 0.98;
    rightFplus = faceStretch(newCoord, p_faceright_d, targetrightF, radius);
    newCoord = newCoord - rightFplus*myparameter2;

    radius = face_width*1.5;
    vec2 targetchin = p_nose + (p_chin - p_nose) * 0.94;
    vec2 chinplus = faceStretch(newCoord, p_chin, targetchin, radius);
    newCoord = newCoord - chinplus*myparameter2;

    vec2 chinCenter = p_nose + (p_chin - p_nose) * 0.7;

    radius = face_width*1.2;
    vec2 targetleftC = chinCenter + (p_chinleft - chinCenter) * 0.97;
    vec2 leftCplus = faceStretch(newCoord, p_chinleft, targetleftC, radius);
    newCoord = newCoord - leftCplus*myparameter2;

    vec2 targetrightC = chinCenter + (p_chinright - chinCenter) * 0.97;
    vec2 rightCplus = faceStretch(newCoord, p_chinright, targetrightC, radius);
    newCoord = newCoord - rightCplus*myparameter2;

    radius = face_width*1.2;
    targetleftC = chinCenter + (p_chinleft_u - chinCenter) * 0.93;
    leftCplus = faceStretch(newCoord, p_chinleft_u, targetleftC, radius);
    newCoord = newCoord - leftCplus*myparameter2;

    targetrightC = chinCenter + (p_chinright_u - chinCenter) * 0.93;
    rightCplus = faceStretch(newCoord, p_chinright_u, targetrightC, radius);
    newCoord = newCoord - rightCplus*myparameter2;

    return textureCoordinate+(newCoord-textureCoordinate)*myparameter;
}