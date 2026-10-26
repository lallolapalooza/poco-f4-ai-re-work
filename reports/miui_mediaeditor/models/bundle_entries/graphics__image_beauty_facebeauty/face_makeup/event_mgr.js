//事件管理
//提供回调函数，通知注册过的line当前的事件
//比如手势触发，消失 可以在调用手势接口获取信息后，循环调用注册函数列表，相关line在收到后做出反应
//然后有个特殊点的后续可以实现的就是抖音里面那个Obj消失触发，可以提供一个函数，让line调用，当发生Obj消失的时候

(function () {

    var ret = function () {
        /////////////////////////util
        var CopySign = function (a, b) {
            var sign = 1;
            if (b < 0) sign = -1;
            return Math.abs(a) * sign;
        }

        var Quat2Euler = function (quat) {
            var x = quat[0]; var y = quat[1]; var z = quat[2]; var w = quat[3];
            // x-axis rotation
            var sinr = 2.0 * (w * x + y * z);
            var cosr = 1.0 - 2.0 * (x * x + y * y);
            var rx = Math.atan2(sinr, cosr);

            // y-axis rotation
            var sinp = 2.0 * (w * y - z * x);
            var ry = 0;
            if (Math.abs(sinp) >= 1)
                ry = CopySign(Math.PI * 0.5, sinp); // use 90 degrees if out of range
            else
                ry = Math.asin(sinp);

            // z-axis rotation
            var siny = 2.0 * (w * z + x * y);
            var cosy = 1.0 - 2.0 * (y * y + z * z);
            var rz = Math.atan2(siny, cosy);

            var rta = 180.0 / Math.PI;
            rx *= rta; ry *= rta; rz *= rta;
            return [rx, ry, rz];
        }

        // axis = 0 is x, axis = 1 is y, axis = 2 is z
        // opt = 0 means less, opt = 1 means greater
        var isRotationTriggered = function (axis, value, opt, params) {
            var quat = params.rotation;
            var degree = Quat2Euler(quat)[axis];
            switch (opt) {
                case 0:
                    if (degree < value)
                        return true;
                    break;
                case 1:
                    if (degree > value)
                        return true;
                    break;
            }
            return false;
        }

        var isNodeTriggered = function (params) {
            var quat = params.rotation;
            var x = Quat2Euler(quat)[0];
            var y = Quat2Euler(quat)[1];
            if (x < -10 && y > -8 && y < 8) {
                return true;
            }
            return false;
        }

        var action_normal = { biyan_zuo: 1, biyan_you: 0 };
        var action_mirror = { biyan_zuo: 0, biyan_you: 1 };

        var isFaceActionTriggered = function (actionname, params, bFlipAction) {
            if (params.expression == undefined || params.rotation == undefined) return false;

            var actionTable = action_normal;
            if (bFlipAction) actionTable = action_mirror;


            switch (actionname) {

                case eFaceGestureType.BrowsUp:
                    return params.expression[16] >= 0.4 || params.expression[17] >= 0.4 || params.expression[18] >= 0.4;
                    break;

                case eFaceGestureType.MouthLeft:
                    return params.expression[24] >= 0.4 || params.expression[28] >= 0.4;
                    break;

                case eFaceGestureType.MouthRight:
                    return params.expression[23] >= 0.4 || params.expression[27] >= 0.4;
                    break;

                case eFaceGestureType.Sneer:
                    return params.expression[42] >= 0.2;
                    break;

                case eFaceGestureType.LipsFunnel:
                    return params.expression[38] >= 0.2;
                    break;

                case eFaceGestureType.JawOpen:
                    return params.expression[21] >= 0.4;
                    break;

                case eFaceGestureType.LipsPucker:
                    return params.expression[39] >= 0.4;
                    break;

                case eFaceGestureType.LipsUpperClose:
                    return params.expression[33] >= 0.5 || params.expression[34] >= 0.5;
                    break;

                case eFaceGestureType.Puff:
                    return params.expression[43] >= 0.4;
                    break;

                case eFaceGestureType.EyeBlink_L:
                    return params.expression[actionTable.biyan_zuo] >= 0.6;
                    break;

                case eFaceGestureType.EyeBlink_R:
                    return params.expression[actionTable.biyan_you] >= 0.6;
                    break;

                case eFaceGestureType.TurningHead_L:
                    return isRotationTriggered(1, 20, 1, params);
                    break;

                case eFaceGestureType.TurningHead_R:
                    return isRotationTriggered(1, -20, 0, params);
                    break;

                case eFaceGestureType.Smile:
                    return (params.expression[28] >= 0.2 || params.expression[30] >= 0.2) && (params.expression[27] >= 0.2 || params.expression[29] >= 0.2);
                    break;

                case eFaceGestureType.Yawn:
                    return params.expression[21] >= 0.2 && params.expression[0] >= 0.1 && params.expression[1] >= 0.1;
                    break;

                case eFaceGestureType.Nods:
                    return isNodeTriggered(params);
                    break;

                case eFaceGestureType.BrowsDown:
                    return params.expression[14] >= 0.3 && params.expression[15] >= 0.3;
                    break;

                default:
                    return false;
                    break;
            }
        };

        var HandState = function () {
            this.HandType = eHandGestureType.None;
        }

        var HandEvent = function () {
            this.bShow = false; //true ->show false -> disappear
            this.state = new HandState();
        }

        var FaceActionState = function () {
            this.FaceActionType = eFaceGestureType.None;
        }

        //人脸表情事件
        var FaceActionEvent = function () {
            this.bShow = false; //true ->show false -> disappear
            this.state = new FaceActionState();
        }

        //人脸出现消失事件

        var DEF_FACE_DISAPPEAR_THRESHOLD = 5;
        
        var FaceExitState = function()
        {
            this.FaceAppearFrameID = -1;  //人脸出现时才会更新
            this.NoUpdateCount = DEF_FACE_DISAPPEAR_THRESHOLD;
            this.lastUpdateFaceExit = false; //记录最后的状态
        }

        var FaceExistEvent = function () {
            this.bFaceExit = false;
        }


        //返回一个字典
        return {

            m_stb:undefined,
            m_eventObjs:[],

            m_GestureInfo:{},

            m_curHandState :undefined,
            m_latestFaceState4Disappear :undefined,
            m_bBtnClicked : false,
            m_curFaceState : undefined,
            m_latestFaceParam : {},

            RegiterStb:function (stb) {
                this.m_stb = stb;
                
                //这个主要是记录消失事件的出现状态
                this.m_latestFaceState4Disappear = new FaceActionState();
                this.m_curHandState = new HandState();
                this.m_curFaceState = new FaceExitState();

                console.logi("liufei RegiterStb in");
                //this.m_eventObjs 筛选出lineObj里面的obj类型的触发
                //就是那类某个mesh消失，出现造成另外的消失出现的事件类型
                // to be add
            },
    
    
            //暂且那么写，主要就是更新所有类型的事件，并调用相关line的回调接口，通知line对mesh做出合适的调用
            Update:function (mutableParams, globalSetedParam, bInFaceRender) {
    
                try {
    
                    //console.logi("liufei event update:", this.m_stb);
                    
                    this.UpdateFaceAppearEvent(mutableParams, bInFaceRender);

                    if(bInFaceRender)
                    {
                        this.UpdateFaceEvent(mutableParams, globalSetedParam);
                    }

                    if(!bInFaceRender)
                    {
                        this.UpdateRenderWhenStart();

                        this.UpdateHandEvent();
    
                        this.UpdateObjectEvent();

                        this.UpdateBtnClicked();

                        this.UpdateLastEndEvent();
                    }

                } catch (err) {
                    console.logi(err.stack);
                }
    
            },
    
            //屏幕初始化元素的响应,仅为了同化流程,目前还没用，待处理
            UpdateRenderWhenStart:function () {
                this.m_stb.TouchLineRenderWhenStart();
            },


            UpdateBtnClicked:function(){
                if(this.m_bBtnClicked)
                {
                    this.m_stb.TouchLineBtnClicked();
                    this.m_bBtnClicked = false;
                }
            },
    
            UpdateFaceAppearEvent:function (mutableParams, bInFaceRender) {
                //检查人脸 出现/消失 
                //目前frame_id只会在人脸出现之后更新，用来标记人脸是否出现，主要是由于当前两个Render接口的缘故
                if(this.m_curFaceState.FaceAppearFrameID == mutableParams.frame_id)
                {
                    this.m_curFaceState.NoUpdateCount++;
                }else
                {
                    this.m_curFaceState.NoUpdateCount = 0;
                }

                if(bInFaceRender)
                {
                    this.m_curFaceState.FaceAppearFrameID = mutableParams.frame_id;
                }

                //console.logi("liufei frameid:", mutableParams.frame_id, " nocount:",this.m_curFaceState.NoUpdateCount);
                
                var testArray = this.m_stb.GetAllNextFaceAppearTriggers();
                var event = new FaceExistEvent();
                event.bFaceExit = this.m_curFaceState.NoUpdateCount < DEF_FACE_DISAPPEAR_THRESHOLD 
                && this.m_curFaceState.FaceAppearFrameID > 0;

                if(event.bFaceExit != this.m_curFaceState.lastUpdateFaceExit && testArray.length > 0)
                {
                    this.m_curFaceState.lastUpdateFaceExit = event.bFaceExit;
                    this.m_stb.TouchLineFaceAppearEvent(event);
                }
            },
    
            UpdateFaceEvent:function (params, globalSetedParam) {
                //检查人脸触发信息 动作出现/消失
    
                //console.logi("liufei event face test in");
    
                var bFlip = globalSetedParam.flip_action;
                var bLastExpDisappear = false;
                var faceState = new FaceActionState();
    
                var faceEvent = new FaceActionEvent();
                faceEvent.state = faceState;
                faceEvent.bShow = false;
                
    
                //轮询检查所有的人脸表情状态比较烦，先根据需要判定的来。
                //人脸表情消失事件之前，必然最近的状态是那个人脸表情的出现。所以判定m_curFaceState
                //中的人脸表情当前为false即可
                var testArray = this.m_stb.GetAllNextFaceActionTriggers();
    
                //console.logi("liufei event face test array:", testArray.length);

                for(var i = 0; i < testArray.length; i++){
    
                    var element = testArray[i];

                    if (element.ShowOrDisappear == 0) {

                        //console.logi("liufei event test diappear:",element.action);

                        //存在消失事件的时候得提前检查这个事件的出现事件，并更新当前状态
                        if (isFaceActionTriggered(element.action, params, bFlip)) {
                             this.m_latestFaceState4Disappear.FaceActionType = element.action;
                        }

                        if ( this.m_latestFaceState4Disappear.FaceActionType != eFaceGestureType.None) {
                            bLastExpDisappear = !isFaceActionTriggered( this.m_latestFaceState4Disappear.FaceActionType, params, bFlip);
                            //console.logi("liufei face:", this.m_latestFaceState4Disappear.FaceActionType," has lost");
                        }

                        //人脸表情消失事件
                        if (element.action ==  this.m_latestFaceState4Disappear.FaceActionType && bLastExpDisappear) {
                            
                            //console.logi("liufei event face disappear,type:", element.action);

                            faceEvent.bShow = false;
                            faceEvent.state.FaceActionType = element.action;
                            this.m_stb.TouchLineFaceActionEvent(faceEvent);
                        }
                    } else {

                        //console.logi("liufei test event face appear:",element.action);

                        //人脸表情出现,后一个判断是为了阻止连续无意义的触发
                        if (isFaceActionTriggered(element.action, params, bFlip) && !isFaceActionTriggered(element.action, this.m_latestFaceParam, bFlip)) {

                            //console.logi("liufei event face triggerd,type:",element.action);

                            faceEvent.bShow = true;
                            faceEvent.state.FaceActionType = element.action;
                            this.m_stb.TouchLineFaceActionEvent(faceEvent);

                            this.m_latestFaceState4Disappear.FaceActionType = element.action;
                        }
                    }
    
                }
    
                this.m_latestFaceParam = JsTool.DeepCopy(params,{});
            },
    
            UpdateHandEvent:function () {
                //检查手势触发信息 手势动作 出现/消失 这个必定是之前有出现，才能有消失的
                //而且消失事件要跟着同一类事件的出现事件之后，因为我这边肯定只能保留一个状态
                //后续要考虑下多人脸和手

                if(this.m_GestureInfo.gesture_curHandType == undefined)
                {
                    return;
                }

                //console.logi("liufei will test hand");

                //分离模式
                var gesture = new HandState();
                gesture.HandType = this.m_GestureInfo.gesture_curHandType;
    
                var gestureState = this.m_GestureInfo.gesture_curHandState;
    
                var testArray = this.m_stb.GetAllNextHandTriggers();

                for(var i = 0; i < testArray.length; i++){
    
                    var element = testArray[i];
    
                    var handEvent = new HandEvent();
    
                    if (element.ShowOrDisappear == 1) {

                        var bAppear = gestureState == GestureState.ENTER || gestureState == GestureState.HOLD;
                        var bLastSameType = gesture.HandType == this.m_curHandState.HandType;
                        var bTypeOK = element.action == gesture.HandType;

                        if (bTypeOK && bAppear && !bLastSameType) {
                            handEvent.bShow = true;
                            handEvent.state = gesture;

                            //console.logi("hand appear,",gesture.HandType);
    
                            this.m_stb.TouchLineHandEvent(handEvent);
                        }
                    } else {

                        var bDisAppear = element.action != gesture.HandType;
                        var bTypeOK = element.action == this.m_curHandState.HandType;
                        
                        if (bTypeOK && bDisAppear) {
                            handEvent.bShow = false;
                            handEvent.state = gesture;
                            handEvent.state.HandType = element.action;

                            //console.logi("hand disappear,",gesture.HandType);

                            this.m_stb.TouchLineHandEvent(handEvent);
                        }
                    }
                }

                this.m_curHandState = gesture;
            },
    
            //to be add soon 
            UpdateObjectEvent:function () {
                //检查一些组件触发的事件信息，比如某某mesh出现/消失
                var objEvent = undefined;
    
                this.m_stb.TouchLineObjectEvent(objEvent);
            },

            //其实是Obj的一种特殊类型，但是好做一点，而且目前就这个需求
            UpdateLastEndEvent:function ()
            {
                var testArray = this.m_stb.GetAllNextLastEndTriggers();
                if(testArray.length > 0)
                {
                    this.m_stb.TouchLineLastEndEvent();
                }
            },
        }
    };

    return ret;
})()