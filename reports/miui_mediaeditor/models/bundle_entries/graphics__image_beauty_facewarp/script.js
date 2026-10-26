(function () {
	var image_beauty_controller = getImageBeautyControllerInstance();
	image_beauty_controller.LoadResourceFaceWarp();
	var claim_version_major = 7;
	var claim_version_minor = 2;
	var claim_version_fix = 0;
	var is_version_verified = false;
	if (FaceUnity.SDK_VERSION) {
		var sdk_major = FaceUnity.SDK_VERSION_MAJOR;
		var sdk_minor = FaceUnity.SDK_VERSION_MINOR;
		var sdk_fix = FaceUnity.SDK_VERSION_FIX;
		var claim_version_num = claim_version_major * 10000 + claim_version_minor * 100 + claim_version_fix;
		var sdk_version_num = sdk_major * 10000 + sdk_minor * 100 + sdk_fix;
		if (sdk_version_num >= claim_version_num) {
			is_version_verified = true;
		}
	}
	if (!is_version_verified) {
		console.log("This bundle claims at least sdk version:", claim_version_major, "_", claim_version_minor, "_", claim_version_fix);
		console.log("Current SDK version:", sdk_major, "_", sdk_minor, "_", sdk_fix);
	}


	var require = function (fn) {
		var jsstr = FaceUnity.ReadFromCurrentItem(fn);
		if (jsstr == undefined) {
			console.log(fn, "is not exist");
			return undefined;
		}
		console.log("eval", fn, 'begin');
		return eval(jsstr);
	};

	var g_default_params = {
		// face color
		filter_name: "origin",
		filter_level: 0.0,
		filter_name1: "origin",
		filter_level1: 0.0,

		// AI demand status
		landmarks_type: FaceUnity.FUAITYPE_FACELANDMARKS75 | FaceUnity.FUAITYPE_IMAGE_BEAUTY
			| FaceUnity.FUAITYPE_FACELANDMARKS239 | FaceUnity.FUAITYPE_FACEPROCESSOR |
			FaceUnity.FUAITYPE_HUMAN_PROCESSOR_2D_IMGSLIM, //use FUAITYPE enum type

		use_landmark: 1,

		image_makeup: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		//image beauty
		image_blur: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //磨皮
		image_blur_nevus: [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], //磨皮是否留痣
		image_aver: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //匀肤
		image_wrinkle: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //去皱
		image_white: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5], //美白
		image_warmcold: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5], //肤色冷暖
		image_red: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //红润
		image_red_type: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],//0->默认 1->经典 2->质感 3->自然 4->日系 5->轻熟
		image_lighteye: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //亮眼
		image_sharpenbrown: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //眉毛增强
		image_sharpenbrown_type: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],//0->默认 1->经典 2->质感 3->自然 4->日系 5->轻熟
		image_threed: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //五官立体
		image_threed_type: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], 
		image_jili: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //皮肤肌理
		image_darkcircle: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //去黑眼圈
		image_decreepattern: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //去法令纹
		image_acencancel: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //祛斑祛痘
		image_teeth_white: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //美牙
		image_makeup_transfer: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //妆容迁移
		image_restore: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], //超清人像
		///image warp 
		image_eye_enlarging: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_cheek_thinning: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],          	//瘦脸
		image_cheek_v: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],          		//v脸 
		image_cheek_narrow: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],          //窄脸
		image_cheek_small: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],          //小脸
		image_cheek_oval: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],          //鹅蛋脸
		image_intensity_nose: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		image_intensity_forehead: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_mouth: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_chin: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_philtrum: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],      // 人中
		image_intensity_long_nose: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_eye_space: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_eye_rotate: [1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0],
		image_intensity_smile: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		image_intensity_canthus: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		image_intensity_cheekbones: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		image_intensity_lower_jaw: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		image_intensity_eye_circle: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		image_intensity_brow: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_temple: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_cheekbone: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_lowerjaw: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_nosetip: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_hairtop: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_plumplip: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_browspace: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],
		image_intensity_browloaction: [0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5],

		pet_auto_beauty: 0.0,
		pet_hair_bright: 0.0,
		pet_texture: 0.0,

		//bodyslim
		body_intensity_body_slim: 0.0,
		body_intensity_leg_stretch: 0.0,
		body_intensity_hip_stretch: 0.0,
		body_intensity_waist_slim: 0.0,
		body_intensity_shoulder_stretch: 0.5,
		body_intensity_swan_neck: 0.0,
		body_intensity_shoulder_angle: 0.0,
		body_intensity_head_slim: 0.0,
		body_intensity_leg_thin: 0.0,
		body_intensity_arm_thin: 0.0,
		body_debug: 0.0,
		TEST_FILE_NAME: "",
		body_manual_leg_slim: 1.0,
		body_manual_leg_center_offset: 0.5,
		body_manual_leg_top_offset: 0.7,
		body_manual_leg_bottom_offset: 0.2,
		update_manual_grid: 0.0,
		redo_manual_grid: 0.0,
		body_orientation: 0,

		debug_mask: 0,
		debug_landmark: 0,

		//manual beauty
		image_beauty_type: 0,//0->取消 1->祛斑祛痘 2->磨皮 3->祛皱
		image_beauty_manual_type: 0,//0->自动 1->手动
		is_draw_stroke: 1,


	};

	var beauty_num = 0;
	var g_jianbian_params = {};
	var warp_param_for_default = {
		face_param: 1.65,
		eye_param: 1.1,
		face_scale: 0,
		eye_scale: 0,
	};
	var warp_param_for_ziran = {
		face_param: 0.5,
		eye_param: 0.45,
		face_scale: 0.1,
		eye_scale: 0.05,
	};
	var face_mask;
	var g_params = JSON.parse(JSON.stringify(g_default_params));
	var prepareResource = [];
	//use 3D points
	var lerp = function (a, b, t) {
		return a + (b - a) * t;
	};

	//ruitaocc <0.5 for fshader warp, >0.5 for vshader warp

	g_params["enableVwarp"] = 1.0;

	var default_aitype = g_params.landmarks_type;

	var dummy_tex = FaceUnity.LoadTexture("black.png", 0);
	var overlay_shader = FaceUnity.ReadFromCurrentItem("stroke/overlay.fs.glsl");

	/////////
	return {
		aitype: default_aitype,
		need_skin_mask: 1,
		enable_trackerless: 1,
		GetImageBeautyController: function () {
			return image_beauty_controller;
		},
		SetParam: function (name, value) {
			//console.loge("image_beauty SetParam:", name, " ", value);
			if (g_params.debug_landmark && debugLandmarkJS != undefined) {
				debugLandmarkJS.SetParam(name,value);
			}
			if (g_params[name] != undefined && typeof (g_params[name]) == typeof (value)) {
				if (name == "filter_name" || name == "TEST_FILE_NAME") {
					image_beauty_controller.SetParamS(name, value);
					return 1;
				}
				if (name == "filter_name1" || name == "TEST_FILE_NAME") {
					image_beauty_controller.SetParamS(name, value);
					return 1;
				}

				g_params[name] = value;
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if (name == "image_pointsize") {
				image_beauty_controller.SetParamD(name, value);
				//return StrokeJS.SetParam("pointsize", value);
			} else if (name == "acen_gaussian_rate") {
				image_beauty_controller.SetParamD(name, value);
				//return StrokeJS.SetParam("pointsize", value);
			} else if (name == "wrikle_gaussian_rate") {
				image_beauty_controller.SetParamD(name, value);
				//return StrokeJS.SetParam("pointsize", value);
			} else if (name == "image_beginstroke") {
				image_beauty_controller.SetParamD(name, value);
				//return StrokeJS.SetParam(name, value);
			} else if (name == "image_endstroke") {
				image_beauty_controller.SetParamD(name, value);
				//return StrokeJS.SetParam(name, value);
			} else if (name == "image_undo") {
				image_beauty_controller.SetParamD(name, value);
				//return StrokeJS.SetParam(name, value);
			} else if (name == "image_redo") {
				image_beauty_controller.SetParamD(name, value);
				//return StrokeJS.SetParam(name, value);
			} else if (name == "image_clear") {
				image_beauty_controller.SetParamD(name, value);
				//return StrokeJS.SetParam(name, value);
			} else if (name == "image_clear_manual_undo_redo_stack") {
				image_beauty_controller.SetParamD(name, value);
				//return StrokeJS.SetParam(name, value);
			} else if (name == "redo_warp") {
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if (name == "undo_warp") {
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if (name == "undo_body_manual") {
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if (name == "redo_body_manual") {
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if (name == "clear_grid") {
				image_beauty_controller.SetParamD(name, 1.0);
				return 1;
			} else if (name == "debug_warp") {
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if (name == "image_manual_acen_data") {
				image_beauty_controller.SetParamManualAcen(value);
				return 1;
			} else if (name == "image_manual_blur_data") {
				image_beauty_controller.SetParamManualBlur(value);
				return 1;
			} else if (name == "image_manual_wrikle_data") {
				image_beauty_controller.SetParamManualWrikle(value);
				return 1;
			} else if (name == "image_manual_order") {
				image_beauty_controller.SetParamManualOrder(value);
				return 1;
			} else if (name == "image_manual_result_status") {
				image_beauty_controller.SetParamManualResultStatus(value);
				return 1;
			} else if (name == "image_red_color") {
				image_beauty_controller.SetParamRedColor(value);
				return 1;
			} else if (name == "image_stroke_color") {
				image_beauty_controller.SetParamStrokeColor(value);
				return 1;
			} else if (name == "image_face_rect") {
				image_beauty_controller.SetParamFaceRect(value);
				return 1;
			} else if (name == "image_manual_max_rect_size") {
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if (name == "image_second_undo") {
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if (name == "image_second_redo") {
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if (name == "image_acen_data") {
				image_beauty_controller.SetParamAcenData(value);
				return 1;
			} else if (name == "image_blur_data") {
				image_beauty_controller.SetParamBlurData(value);
				return 1;
			} else if (name == "image_wrinkle_data") {
				image_beauty_controller.SetParamWrinkleData(value);
				return 1;
			} else if (name == "image_face_data") {
				image_beauty_controller.SetParamFaceData(value);
				return 1;
			} else if (name == "image_do_perpare_wrinkle") {
				image_beauty_controller.SetParamD(name, value);
				return 1;
			} else if(name == "initparam"){
				image_beauty_controller.SetParamD(name, value);
				return 1;
			}

			else {
				try {
					var desc = JSON.parse(name);
					if (desc.param != undefined && g_params[desc.param] != undefined) {
						g_params[desc.param][desc.faceid] = value;
						image_beauty_controller.SetParamDIndexed(desc.param, value, desc.faceid);
						return 1;
					} else if (desc.x != undefined && desc.y != undefined) {
						//StrokeJS.AddPoint(desc.x, desc.y);
						image_beauty_controller.SetParamXY(Number(desc.x), Number(desc.y));
					}
					else {
						console.loge("js param error:", name)
						return 0;
					}
				} catch (err) {
					console.log(err.stack);
				}
				return 0;
			}
		},
		//NativeImpl: 1.0,
		GetParam: function (name) {
			if (g_params[name] != undefined) {
				return g_params[name];
			} else if (name == "image_flaw_stroke_mask") {
				//return StrokeJS.GetParam("image_flaw_stroke_mask");
			} else if (name == "image_manual_acen_data" || name == "image_manual_blur_data"
				|| name == "image_manual_wrikle_data" || name == "image_can_undo" || name == "image_can_redo"
				|| name == "image_do_manual" || name == "image_manual_order" || name == "image_face_rect"
				|| name == "image_acen_data" || name == "image_wrinkle_data" || name == "image_blur_data"
				|| name == "image_face_data") {
				return image_beauty_controller.GetParamManual(name);
			} else if (name == "image_do_body_beautify_pass") {
				return image_beauty_controller.JudgeDoBodyBeautify();
			}else if (name == "image_manual_body_rect") {
				return image_beauty_controller.GetManualOffsetWH();
			}
			else {
				try {
					var desc = JSON.parse(name);
					if (desc == undefined) {
						console.loge("param parse error,", name);
						return 0;
					}
					if (desc.param != undefined && desc.faceid != undefined) {
						var ret = image_beauty_controller.GetParamDIndexed(desc.param, desc.faceid);
						return ret;
					}
				} catch (err) {
					console.log(err.stack);
				}
				return 0;
			}
		},
		PreProcess: function (w, h) {
			if (is_version_verified == false) {
				console.log("vertion not match")
				return;
			}
			/*
			try {
				image_beauty_controller.Preprocess();
			} catch (err) {
				console.log(err.stack)
			}
			*/
		},

		OnRenderImage: function (params) {
			//for stroke overlay
			try {
				/* var strokeTex = StrokeJS.GetParam("image_flaw_stroke_mask");
				var intensity = 1.0;
				if (g_params["is_draw_stroke"]) {
					FaceUnity.PostProcess(
						"xdxas34a8de6fstrokeoverlayx",
						overlay_shader,
						"stroke_merge",
						{
							tex_stroke: strokeTex,
							timingIntensity: intensity
						});
				} */
			} catch (err) {
				console.log(err.stack);
			}
		},

		OnUpdate: function (params) {

			try {

				/* if(StrokeJS)
				{
					StrokeJS.OnUpdate(params);
				} */


				if (g_params.body_debug && bodyLandMarkJS != undefined) {
					bodyLandMarkJS.OnUpdate(params);
				}

				if (g_params.debug_landmark && debugLandmarkJS != undefined) {
					face_count = FaceUnity.GetFaceNumResult();
					if (face_count < 1) {
						return;
					}
					params.debug_landmarks = new Array(face_count);
					for (var i = 0; i < face_count; i++) {
						var tmp = image_beauty_controller.GetDebugLandmarks(i);
						params.debug_landmarks[i] = tmp;
					}
					debugLandmarkJS.OnUpdate(params);
				}

			} catch (err) {
				console.logi(err.stack)
			}



		},

		OnRender: function (params) {

			if (is_version_verified == false) {
				console.log("vertion not match")
				return;
			}

			try {

				//StrokeJS.OnRender(params);

				if (g_params.body_debug && bodyLandMarkJS != undefined) {
					bodyLandMarkJS.Render(params);
				}



				//	image_beauty_controller.Preview();
			} catch (err) {
				console.logi(err.stack)
			}
			return 1;
		},

		Render: function (params) {
			if (g_params.debug_landmark && debugLandmarkJS != undefined) {
				debugLandmarkJS.Render(params);
			}
			return 1;
		},

		RenderNonFace: function (params) {
			return 1;
		},
		Destroy: function (params) {

		},
		LoadResource: function(params){
			image_beauty_controller.setRepeatLoad(true);
			image_beauty_controller.LoadResourceFaceWarp();
			image_beauty_controller.setRepeatLoad(false);
		},
		FilterType: 8,
		name: "MiImageBeautyFaceWarp",
	};
})()