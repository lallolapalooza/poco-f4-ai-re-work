(function () {
  var techInited = false;
  var vsSrc = FaceUnity.ReadFromCurrentItem("stroke/vs.glsl");
  var fsSrc = FaceUnity.ReadFromCurrentItem("stroke/fs.glsl");
  var vertices = undefined;
  var vertexBuffer = undefined;
  var verticesDrawing = undefined;
  var vertexBufferDrawing = undefined;
  var maxSize = 1024;
  var mTech = undefined;
  var perDrawMaxPoint = 1000;
  var pointSize = 0.008;
  var edgeStart = 0;
  var flaw_stroke_mask = undefined;
  var image_w = -1;
  var image_h = -1;
  var rtt_w = -1;
  var rtt_h = -1;
  var lst_point_x = -1;
  var lst_point_y = -1;

  var CreateTech = function () {
    mTech = ngl.CheckAndCreateTec("stroke_tech");
    mTech.SetVertexShader(vsSrc);
    mTech.SetFragmentShader(fsSrc);
    var verticesData = [];
    for (var i = 0; i < perDrawMaxPoint; i++) {
      for(var j = 0; j < 360; j += 10){
        verticesData.push(0.0);
        verticesData.push(0.0);
        verticesData.push(0.0);
        verticesData.push(0.0);
        verticesData.push(0.0);
        verticesData.push(0.0);
      }
    }
    vertices = new Float32Array(verticesData);
    verticesDrawing = new Float32Array(verticesData.slice());
    vertexBuffer = ngl.CheckAndCreateVBO("stroke_vbo", vertices, vertices.length * 4, gl.DYNAMIC_DRAW);
    vertexBufferDrawing = ngl.CheckAndCreateVBO("stroke_vbo_drawing", verticesDrawing, verticesDrawing.length * 4, gl.DYNAMIC_DRAW);
  }

  ////Stroke
  var StrokeSystem = function (rttName, w, h) {
    this.needClearAndRepaint = false;
    //rtt
    this.rttInited = false;
    this.rttName = rttName;
    this.rttW = w;
    this.rttH = h;
    this.rtt = undefined;
    //
    this.stretching = false;
    this.currentStroke = [];
    this.currentStrokeDrawingData = [];
    //undo/redo
    this.currentStack = [];
    this.currentStackTotalPoints = 0;
    this.popedStack = [];

    this.getMask = function () {
      if (this.rtt != undefined) {
        return this.rtt.tex;
      }
    }

    this.beginStroke = function () {
      console.logd("beginStroke");
      this.stretching = true;
    };

    this.addStrokePoint = function (x, y) {
      if (this.stretching) {
        var offset = this.currentStrokeDrawingData.length;
        for(var i = 0;i < 360; i += 10){
          this.currentStroke.push(x);
          this.currentStroke.push(y);
          this.currentStrokeDrawingData.push(x);
          this.currentStrokeDrawingData.push(y);
          verticesDrawing[offset] = x;
          verticesDrawing[offset + 1] = y;
          offset += 2;
          
          var temp_x = Number(x) + pointSize * Math.cos(i / 180.0 * 3.1415926);
          var temp_y = Number(y) + pointSize * FaceUnity.g_image_w / FaceUnity.g_image_h * Math.sin(i / 180.0 * 3.1415926);
          this.currentStroke.push(temp_x);
          this.currentStroke.push(temp_y);
          this.currentStrokeDrawingData.push(temp_x);
          this.currentStrokeDrawingData.push(temp_y);
          verticesDrawing[offset] = temp_x;
          verticesDrawing[offset + 1] = temp_y;
          offset += 2;

          temp_x = Number(x) + pointSize * Math.cos((i + 10) / 180.0 * 3.1415926);
          temp_y = Number(y) + pointSize * FaceUnity.g_image_w / FaceUnity.g_image_h * Math.sin((i + 10) / 180.0 * 3.1415926);
          this.currentStroke.push(temp_x);
          this.currentStroke.push(temp_y);
          this.currentStrokeDrawingData.push(temp_x);
          this.currentStrokeDrawingData.push(temp_y);
          verticesDrawing[offset] = temp_x;
          verticesDrawing[offset + 1] = temp_y;
          offset += 2;
        }
        console.logd("length:", this.currentStroke.length);
      }
    };

    this.endStroke = function () {
      console.logd("endStroke");
      console.logd("this.currentStroke.length:", this.currentStroke.length);
      console.logd("this.currentStackTotalPoints :", this.currentStackTotalPoints);
      this.stretching = false;
      if (this.currentStroke.length && this.currentStackTotalPoints < perDrawMaxPoint) {
        var len = this.currentStroke.length / (108 * 2);
        if ((this.currentStackTotalPoints + len) > perDrawMaxPoint) {
          len = perDrawMaxPoint - this.currentStackTotalPoints;
        }
        var start = this.currentStackTotalPoints;
        for (var i = 0; i < len; i++) {
          for(var j = 0; j < 108 * 2; ++j) {
            vertices[start * 108 * 2 + i * 108 * 2 + j] = this.currentStroke[i * 108 * 2 + j];
          }
        }

        this.currentStackTotalPoints += len;
        this.currentStack.push(this.currentStroke.slice(0, len * 108 * 2 + 1));
        this.popedStack = [];
        this.currentStroke = [];
        this.currentStrokeDrawingData = [];
        this.needClearAndRepaint = true;
      }
    };

    this.currentStackCount = function () {
      return this.currentStack.length;
    };

    this.popedStackCount = function () {
      return this.popedStack.length;
    };

    this.undo = function () {
      if (this.currentStack.length > 0) {
        var str = this.currentStack.pop();
        this.currentStackTotalPoints -= str.length / (108 * 2);
        this.popedStack.push(str);
      }
      this.needClearAndRepaint = true;
    };

    this.redo = function () {
      if (this.popedStack.length > 0) {
        var str = this.popedStack.pop();
        this.currentStackTotalPoints += str.length / (108 * 2);
        this.currentStack.push(str);
      }
      this.needClearAndRepaint = true;
    };

    this.clear = function () {
      this.currentStack = [];
      this.popedStack = [];
      this.currentStackTotalPoints = 0;
      this.needClearAndRepaint = true;
    }

    this.onPaint = function () {
      ////
      console.logd(this.rttName, "onPaint", " ", this.needClearAndRepaint);
      if (this.rttInited == false) {
        this.rtt = ngl.CheckAndCreateRtt(this.rttName, this.rttW, this.rttH, 0);
        if (this.rtt != undefined) {
          this.rttInited = true;
        } else {
          console.loge("CheckAndCreateRtt failed");
        }
      }
      /////
      if (this.stretching || this.needClearAndRepaint) {
        gl.PushFBO();
        gl.viewport(0, 0, rtt_w, rtt_h);
        //gl.pointSize(pointSize);
        this.rtt.bindonly();
        if (this.stretching) {
          //绘制 当前stroke 到 r通道
          mTech.SetUniformJS({ color: [0.2, 0.0, 0.0, 1.0], pointSize: pointSize, edgeStart: edgeStart });
          var points = this.currentStrokeDrawingData.length / 2;
          ngl.UpdateVBO(vertexBufferDrawing, 0, points * 2 * 4, verticesDrawing);
          mTech.SetVertexPointer("Pos", 2, gl.FLOAT, 0, 0, 0);
          mTech.Draw(vertexBufferDrawing, gl.TRIANGLES, points);
          this.currentStrokeDrawingData = [];
        }
        if (this.needClearAndRepaint) {
          gl.clearColor(0.0, 0.0, 0.0, 0.0);
          gl.clear(gl.COLOR_BUFFER_BIT);
          /* mTech.SetUniformJS({ color: [0.0, 0.1, 0.0, 1.0], pointSize: pointSize, edgeStart: edgeStart });
          var points = this.currentStackTotalPoints * 108;
          ngl.UpdateVBO(vertexBuffer, 0, points * 2 * 4, vertices);
          mTech.SetVertexPointer("Pos", 2, gl.FLOAT, 0, 0, 0);
          mTech.Draw(vertexBuffer, gl.TRIANGLES, points); */
        }
        gl.PopFBO();
        this.needClearAndRepaint = false;
      }
    };
  };

  return {
    aitype: FaceUnity.FUAITYPE_FACELANDMARKS75,
    SetParam: function (name, value) {
      if (name == "pointsize") {
        pointSize = value;
        return 1;
      } else if (name == "image_beginstroke") {
        flaw_stroke_mask.beginStroke();
      } else if (name == "image_endstroke") {
        flaw_stroke_mask.endStroke();
      } else if (name == "image_undo") {
        flaw_stroke_mask.undo();
      } else if (name == "image_redo") {
        flaw_stroke_mask.redo();
      } else if (name == "image_clear") {
        flaw_stroke_mask.clear();
      }
      return 0;
    },
    GetParam: function (name) {
      if (name == "image_flaw_stroke_mask") {
        var masktex = flaw_stroke_mask.getMask();
        console.logd("image_flaw_stroke_mask:", masktex);
        return masktex;
      }
    },
    AddPoint: function (x, y) {
      flaw_stroke_mask.addStrokePoint(x, y);
    },
    OnUpdate: function (params) {
      try {
        if (!techInited) {
          CreateTech();
          techInited = true;
        };
        if (image_w != FaceUnity.g_image_w || image_h != FaceUnity.g_image_h) {
          var w = FaceUnity.g_image_w;
          var h = FaceUnity.g_image_h;
          var ratio = h / w;
          if (w > maxSize) {
            w = maxSize;
            h = w * ratio;
          };
          if (h > maxSize) {
            h = maxSize;
            w = h / ratio;
          };
          flaw_stroke_mask = new StrokeSystem("flaw_stroke_mask", w, h);
          image_w = FaceUnity.g_image_w;
          image_h = FaceUnity.g_image_h;
          rtt_w = w;
          rtt_h = h;
          console.logd("image_w:", image_w);
          console.logd("image_h:", image_h);
        }
      } catch (err) {
        console.log(err.stack);
      }
    },
    OnRender: function (params) {
      flaw_stroke_mask.onPaint();
    },
    RenderNonFace: function (params) {
      //DoRender();
    },
    Destroy: function (params) {
      flaw_stroke_mask = undefined;
      mTech = undefined;
      vertices = undefined;
      verticesDrawing = undefined;
      vertexBuffer = undefined;
      vertexBufferDrawing = undefined;
    },
    name: "stroke_demo"
  };
})()