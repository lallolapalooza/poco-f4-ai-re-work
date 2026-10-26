(function () {
  var image_beauty_controller = new ImageBeautyController;
  // image_beauty_controller.LoadResourceImageEditor(true);
  var claim_version_major = 8;
  var claim_version_minor = 8;
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
    console.loge("This bundle claims at least sdk version:", claim_version_major, "_", claim_version_minor, "_", claim_version_fix);
    console.loge("Current SDK version:", sdk_major, "_", sdk_minor, "_", sdk_fix);
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
    exposure: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 曝光
    brightness: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 亮度
    contrast: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], // 对比度
    saturation: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 饱和度
    vibrance: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 自然饱和度
    temperature: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 色温
    tint: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], // 色调
    highlight: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 高光
    shadow: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 阴影
    sharpen: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 锐化
    white: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 白色色阶
    black: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], // 黑色色阶
    clarity: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 清晰
    luminance_denoise: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 降噪明度
    color_denoise: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],  // 降噪色彩
    noise_amount: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],   // 噪点程度
    noise_highlight: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],    // 噪点高光
    noise_size: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],   // 噪点大小
    noise_roughness: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],    // 噪点粗糙度
    blue_highlight: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    blue_shadow: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    green_highlight: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    green_shadow: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    orange_highlight: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    orange_shadow: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    purple_highlight: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    purple_shadow: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    red_highlight: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    red_shadow: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    yellow_highlight: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    yellow_shadow: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],

    show_mask: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],
    hide_label: [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0],

    fade: 0.0,      // 褪色
    grain: 0.0,     // 颗粒
    halation: 0.0,  // 晕影

    dispersion_disperse: 0.0,
    dispersion_hue: 0.0,
    dispersion_scale: 0.0,

    blue_hue: 0.0,
    blue_lightness: 0.0,
    blue_saturation: 0.0,
    green_hue: 0.0,
    green_lightness: 0.0,
    green_saturation: 0.0,
    orange_hue: 0.0,
    orange_lightness: 0.0,
    orange_saturation: 0.0,
    purple_hue: 0.0,
    purple_lightness: 0.0,
    purple_saturation: 0.0,
    red_hue: 0.0,
    red_lightness: 0.0,
    red_saturation: 0.0,
    yellow_hue: 0.0,
    yellow_lightness: 0.0,
    yellow_saturation: 0.0,

    distortion_amount: 0.0,
    distortion_horizontal: 0.0,
    distortion_vertical: 0.0,

    do_curve_modify: 1.0,
    hdr_level: 0.0,
    hdr_light_type: 0.0,
    hdr_use_fix_light_type: 0.0,
    hdr_force_light_type: 0.0,
    light_perecption: 0.0,
		dehaze: 0.0,
    id_viewport_width: 0.0,
    id_viewport_height: 0.0,
    id_offset_x: 0.0,
    id_offset_y: 0.0,
    id_scale: 1.0,
    id_bg_color_r: 1.0,
    id_bg_color_g: 1.0,
    id_bg_color_b: 1.0,
    id_bg_color_a: 1.0,
    id_layout: 0.0,
    id_layout_width: 0.0,
		id_layout_height: 0.0,
		id_disable_transform:0.0,

    art_mode: 0.0,
    portrait_color_focus_gray: 0.0,

    disable_preview: 1.0
  }

  var g_params = JSON.parse(JSON.stringify(g_default_params));

  return {
    aitype: 0,
    GetImageBeautyController: function () {
      return image_beauty_controller;
		},
		SetParam: function (name, value) {
			//console.loge("image_beauty SetParam:", name, " ", value);
			if(name == "initparam"){
				image_beauty_controller.SetParamD(name, value);
				return 1;
			}
			if (g_params[name] != undefined && typeof (g_params[name]) == typeof (value)) {
				g_params[name] = value;
				image_beauty_controller.SetParamDImageEditor(name, value);
				return 1;
			} else if(name == "red_color_curve" || name == "green_color_curve" || name == "blue_color_curve" || name == "rgb_color_curve" || name == "color_curves"){
				image_beauty_controller.SetParamDImageEditorCurves(name, value);
				return 1;
			} else if (name == "exposure" || name == "brightness" || name == "contrast" || name == "saturation" || name == "vibrance" || name == "temperature"
					|| name == "tint" || name == "highlight" || name == "shadow" || name == "sharpen" || name == "white" || name == "black"|| name == "clarity"
					|| name == "luminance_denoise" || name == "color_denoise" || name == "noise_amount" || name == "noise_size" || name == "noise_roughness" || name == "noise_highlight"
          || name == "blue_highlight"|| name == "blue_shadow"|| name == "green_highlight"|| name == "green_shadow"|| name == "orange_highlight"|| name == "orange_shadow"
          || name == "purple_highlight"|| name == "purple_shadow"|| name == "red_highlight"|| name == "red_shadow"|| name == "yellow_highlight"|| name == "yellow_shadow") {
				g_params[name][0] = value;
				image_beauty_controller.SetParamDIndexedImageEditor(name, value, 0);
				return 1;
			} else if(name == "add_label" || name == "delete_label" || name == "update_label" || name == "begin_stroke" || name == "add_stroke" 
					|| name == "erase_stroke" || name == "end_stroke" || name == "hide_label"){
				image_beauty_controller.SetMaunualLabelType(name, value);
				return 1;
			} else if(name == "label_undo" || name == "label_redo" || name == "stroke_undo" || name == "stroke_redo" || name == "clear_label"){
				image_beauty_controller.SetMaunualUndoRedoLabel(name);
				return 1;
			} else if(name == "scene_index") {
				image_beauty_controller.SetSceneIndex(value);
        return 1;
			} else if(name == "preview_wh") {
        image_beauty_controller.SetPreviewWH(value[0],value[1]);
      } else {
				try {
					var desc = JSON.parse(name);
					//console.loge("desc param:",desc.param,"x:",desc.x,"y:",desc.y);
					if (desc.param != undefined && g_params[desc.param] != undefined) {
						//console.loge("SetParam 0");
						g_params[desc.param][desc.maskid] = value;
						image_beauty_controller.SetParamDIndexedImageEditor(desc.param, value, desc.maskid);
						return 1;
					} else if (desc.param != undefined && desc.x != undefined && desc.y != undefined) {
						//console.loge("SetParam 2");
						image_beauty_controller.SetParamDImageEditorTransform(desc.param, Number(desc.x), Number(desc.y));
					} else {
						console.loge("js param error:", name)
						return 0;
					}
				} catch (err) {
					console.log(err.stack);
				}
				return 0;
			}
		},
		GetParam: function (name) {
			console.logd("image_beauty GetParam:", name);
			if (name == "exposure" || name == "brightness" || name == "contrast" || name == "saturation" || name == "vibrance" || name == "temperature"
					|| name == "tint" || name == "highlight" || name == "shadow" || name == "sharpen" || name == "white" || name == "black"|| name == "clarity"
					|| name == "luminance_denoise"|| name == "color_denoise"|| name == "noise_amount" || name == "noise_size" || name == "noise_roughness" || name == "noise_highlight"
          || name == "blue_highlight"|| name == "blue_shadow"|| name == "green_highlight"|| name == "green_shadow"|| name == "orange_highlight"|| name == "orange_shadow"
          || name == "purple_highlight"|| name == "purple_shadow"|| name == "red_highlight"|| name == "red_shadow"|| name == "yellow_highlight"|| name == "yellow_shadow") {
				return g_params[name][0];
			} else if (name == "hdr_light_type") {
				return image_beauty_controller.GetParamImageEditorI(name);
			} else if (g_params[name] != undefined) {
				return g_params[name];
			} else if(name == "ai_label_inform" || name == "current_label_inform") {
				return image_beauty_controller.GetParamImageEditor(name);
			} else if(name == "current_circle_tex" || name == "label_can_undo" || name == "label_can_redo" || name == "local_is_change") {
				return image_beauty_controller.GetParamImageEditorI(name);
			} else if(name == "liquify_can_undo" || name == "liquify_can_redo") {
				return image_beauty_controller.GetParamImageLiquifyI(name);
			} else if(name == "crop_box") {
				return image_beauty_controller.GetCropBox();
			} else if(name == "crop_angle") {
				return image_beauty_controller.GetRotation();
			} else if(name == "dect_portrait") {
				return image_beauty_controller.GetDectPortrait();
			} else if(name == "scene_index") {
				return image_beauty_controller.GetSceneIndex();
			} else if(name == "id_crop_image_width") {
				return image_beauty_controller.GetCropWidth();
			} else if(name == "id_crop_image_height") {
				return image_beauty_controller.GetCropHeight();
			} else if(name == "most_color") {
				return image_beauty_controller.getMostColor();
			} else {
				try {
					var desc = JSON.parse(name);
					if (desc == undefined) {
						console.loge("param parse error,", name);
						return 0;
					}
					if (desc.param != undefined && desc.maskid != undefined) {
						return g_params[desc.param][desc.maskid];
					}
					return image_beauty_controller.GetParamImageEditorI(name);
				} catch (err) {
					console.log(err.stack);
				}
				return 0;
			}
		},
		
		Destroy: function (params) {

		},
		FilterType: 8,
		name: "ImageEditor",
	};
})()


