(function () {
	var g_default_params = {
		is_makeup_on: 1.0,
		makeup_intensity: 1.0,     // intensity for all makeup
		makeup_intensity_lip: 1.0,		//kouhong
		makeup_intensity_pupil: 1.0,		//meitong
		makeup_intensity_eye: 1.0,  		//yanying
		makeup_intensity_eyeLiner: 1.0,  		//yanxian
		makeup_intensity_eyelash: 1.0,  		//jiemao
		makeup_intensity_eyeBrow: 1.0,  		//meimao 
		makeup_intensity_blusher: 1.0,		//saihong
		makeup_intensity_foundation: 1.0,		//fendi
		makeup_intensity_highlight: 1.0,		//gaoguang
		makeup_intensity_shadow: 1.0,		//yinying
		makeup_lip_occlusion: 1.0,		//lip occlusion, > 0.5 enable, < 0.5 disable.
		makeup_occlusion: 1.0,		//occlusion, > 0.5 enable, < 0.5 disable.
		makeup_occlusion_type: 2.0,
		is_use_fix: 0.0,           //above 
		fix_makeup_data: [],
		reverse_alpha: 0,    //ios android set 1,pc set 0
		makeup_lip_color: [0.0, 0.0, 0.0, 0.0],
		makeup_lip_color2: [0.0, 0.00, 0.00, 0.0],
		makeup_lip_highlight: 0.0,
		makeup_lip_mask: 1.0,
		isMultiMask: 0,

		//mask params
		is_bite_lip: 0.0,
		is_two_color: 0.0,
		lip_type: 6,
		is_use_update: 1.0,
		is_use_warp: 0.0,
		show_grid: 1.0,
		//brow_warp
		brow_warp: 0.0,
		brow_warp_type: 0,
		//flip
		is_flip_points: 0,
		//change color for makeup
		makeup_eye_color: [0.0, 0.0, 0.0, 0.0],
		makeup_eye_color2: [0.0, 0.0, 0.0, 0.0],
		makeup_eye_color3: [0.0, 0.0, 0.0, 0.0],
		makeup_eye_color4: [0.0, 0.0, 0.0, 0.0],
		makeup_eyeLiner_color: [0.0, 0.0, 0.0, 0.0],
		makeup_eyelash_color: [0.0, 0.0, 0.0, 0.0],
		makeup_eyeBrow_color: [0.0, 0.0, 0.0, 0.0],
		makeup_blusher_color: [0.0, 0.0, 0.0, 0.0],
		makeup_blusher_color2: [0.0, 0.0, 0.0, 0.0],
		makeup_foundation_color: [0.0,
			0.0,
			0.0,
			0.0],
		makeup_highlight_color: [0.0, 0.0, 0.0, 0.0],
		makeup_shadow_color: [0.0, 0.0, 0.0, 0.0],
		makeup_pupil_color: [0.0, 0.0, 0.0, 0.0],
		blend_type_tex_eye: 0,
		blend_type_tex_eye2: 0,
		blend_type_tex_eye3: 0,
		blend_type_tex_eye4: 0,
		blend_type_tex_brown: 0,
		blend_type_tex_eyeLash: 0,
		blend_type_tex_eyeLiner: 0,
		blend_type_tex_blusher: 0,
		blend_type_tex_blusher2: 0,
		blend_type_tex_highlight: 2,
		blend_type_tex_shadow: 0,
		blend_type_tex_pupil: 1,
		blend_type_tex_lip: 0,
		use_brow_v2: 0,
		use_eye_v2: 0,
		brow_mask_v: 1.0,
		eye_mask_v: 1.0,
		lip_mask_v: 1.0,
		face_mask_v: 1.0,
		makeup_lip_color_v2: [0.0, 0.0, 0.0, 0.0],
		makeup_lip_color_mix_strength: 0,
		makeup_lip_highlight_strength: 0,
		makeup_lip_highlight_enable: 0,
		landmarks_type: FaceUnity.FUAITYPE_FACELANDMARKS239,
		is_clear_makeup: 0,

		makeup_config_out: undefined,
		makeup_name: "",
		bindedArray: [],
		use_occe_segment: false,
		lip_blend_weight_type:0.0
	};
	var g_params = JSON.parse(JSON.stringify(g_default_params));
	var brow_makeup_data = JSON.parse(FaceUnity.ReadFromCurrentItem("face_makeup/brow_makeup.json") || "{}");
	var brow_makeup_data_old = JSON.parse(FaceUnity.ReadFromCurrentItem("face_makeup/brow_makeup_old.json") || "{}");
	var NativeController = getMakeUpControllerInstance();
	NativeController.LoadResource(brow_makeup_data, brow_makeup_data_old, 0);
	NativeController.SetParamD("brow_mask_v", 1.0);
	NativeController.SetParamD("eye_mask_v", 1.0);
	NativeController.SetParamD("lip_mask_v", 1.0);
	NativeController.SetParamD("face_mask_v", 1.0);
	NativeController.SetParamD("is_makeup_on", 1);
	var default_aitype = FaceUnity.FUAITYPE_FACELANDMARKS209;
	if (g_params.landmarks_type == FaceUnity.FUAITYPE_FACELANDMARKS209) default_aitype = FaceUnity.FUAITYPE_FACELANDMARKS209;
	else if (g_params.landmarks_type == FaceUnity.FUAITYPE_FACELANDMARKS239) default_aitype = FaceUnity.FUAITYPE_FACELANDMARKS239;
	return {
		aitype: g_params.landmarks_type,
		GetMakeupController: function () {
			return NativeController;
		},
		//转换下名称，主要原先大小写贼恶心，Creator导出规范化了
		FilterName: function (name) {

			var retName = name;

			switch (name) {
				case "tex_eyeBrow":
					{
						retName = "tex_brow";
					}
					break;
				case "tex_eyelash":
					{
						retName = "tex_eyeLash";
					}
					break;
				default:
					retName = name;
					break;
			}

			return retName;
		},

		SetParam: function (name, value) {
			//console.loge('name:',name,' value:',value);
			name = this.FilterName(name);


			if (name == "landmarks_type") {
				//console.log("this.aitype",this.aitype);
				g_params.landmarks_type = value;
				var new_aitype = FaceUnity.FUAITYPE_FACELANDMARKS209;
				if (g_params.landmarks_type == FaceUnity.FUAITYPE_FACELANDMARKS209) new_aitype = FaceUnity.FUAITYPE_FACELANDMARKS209;
				else if (g_params.landmarks_type == FaceUnity.FUAITYPE_FACELANDMARKS239) new_aitype = FaceUnity.FUAITYPE_FACELANDMARKS239;
				this.aitype = new_aitype;
				//need_init = 1;
				NativeController.SetParamD("landmarks_type", value);
				NativeController.SetParamD("need_init", 1.0);
				//console.log("this.aitype2",this.aitype);
			}
			//console.log('name:',name,' value:',value);
			if ((name == "tex_brow" || name == "tex_eye" || name == "tex_eye2" ||
				name == "tex_eye3" || name == "tex_eye4" || name == "tex_pupil" || name == "tex_eyeLash" ||
				name == "tex_lip" || name == "tex_highlight" || name == "tex_eyeLiner" || name == "tex_blusher"
				|| name == "tex_blusher2" || name == "tex_highlight" || name == "tex_lip_highlight"
				|| name == "tex_foundation" || name == "tex_shadow" || name == "tex_lip_mask_bz" || name == "tex_lip_mask_zz"
				|| name == "tex_lip_mask_bite_bz" || name == "tex_lip_mask_bite_zz"|| name == "tex_lip_mask_highlight_bz" || name == "tex_lip_mask_highlight_zz") && value.length == undefined) {
				console.log('set name:', name, ' value:', value);
				var tex_image;
				if (0 == value) {
					tex_image = undefined;
				}
				else {
					tex_image = value;
				}
				console.log('set name --- :', name, ' value:', tex_image);
				return NativeController.SetParamTex(name, tex_image);
			}
			if (name == "tex_brow" || name == "tex_eye" || name == "tex_pupil" || name == "tex_eyeLash" || name == "tex_lip" || name == "tex_highlight" || name == "tex_eyeLiner" || name == "tex_blusher") {
				return 1;

			}
			//console.log('here1!!');
			if (name == "is_flip_points" && g_params[name] != value) {
				//need_init = 1;
				NativeController.SetParamD("need_init", 1.0);
			}
			if(name == "machine_level") {
				if(value > 0.5) {
					if(!g_params.use_occe_segment){
						if(g_params.makeup_occlusion_type == 2.0){
							this.aitype += FaceUnity.FUAITYPE_FACEPROCESSOR_FACEOCCUSEGMENT;
						}else if(g_params.makeup_occlusion_type == 1.0){
							this.aitype += FaceUnity.FUAITYPE_FACEPROCESSOR_LIPSOCCUSEGMENT;
						}
						g_params.use_occe_segment = true;
					}
					NativeController.SetParamD("makeup_lip_occlusion", 0.0);				
				}else{
					if(g_params.use_occe_segment){
						if(g_params.makeup_occlusion_type == 2.0){
							this.aitype -= FaceUnity.FUAITYPE_FACEPROCESSOR_FACEOCCUSEGMENT;
						}else if(g_params.makeup_occlusion_type == 1.0){
							this.aitype -= FaceUnity.FUAITYPE_FACEPROCESSOR_LIPSOCCUSEGMENT;
						}
						g_params.use_occe_segment = false;
					}
					NativeController.SetParamD("makeup_lip_occlusion", 1.0);		
				}
			}
			if(name == "makeup_occlusion_type"){
				if(value > 1.5){
					if(g_params.use_occe_segment && g_params.makeup_occlusion_type == 1.0){
						this.aitype = this.aitype - FaceUnity.FUAITYPE_FACEPROCESSOR_LIPSOCCUSEGMENT 
							+ FaceUnity.FUAITYPE_FACEPROCESSOR_FACEOCCUSEGMENT;
					}
				}else if(value > 0.5){
					if(g_params.use_occe_segment && g_params.makeup_occlusion_type == 2.0){
						this.aitype = this.aitype - FaceUnity.FUAITYPE_FACEPROCESSOR_FACEOCCUSEGMENT 
							+ FaceUnity.FUAITYPE_FACEPROCESSOR_LIPSOCCUSEGMENT;
					}
				}
				g_params.makeup_occlusion_type = value;
			}
			if(name == "image_makeup"){
				g_params["makeup_intensity"] = value;
				NativeController.SetParamD("makeup_intensity", value);
				return 1;
			}else if(name == "image_eye_makeup"){
				g_params["makeup_intensity_eye"] = value;
				//g_params["makeup_intensity_eyeLiner"] = value;
				//g_params["makeup_intensity_eyelash"] = value;
				NativeController.SetParamD("makeup_intensity_eye", value);
				//NativeController.SetParamD("makeup_intensity_eyeLiner", value);
				//NativeController.SetParamD("makeup_intensity_eyelash", value);
				return 1;
			}else if(name == "image_eyeLiner_makeup"){
				g_params["makeup_intensity_eyeLiner"] = value;
				NativeController.SetParamD("makeup_intensity_eyeLiner", value);
				return 1;
			}else if(name == "image_eyelash_makeup"){
				g_params["makeup_intensity_eyelash"] = value;
				NativeController.SetParamD("makeup_intensity_eyelash", value);
				return 1;
			}else if(name == "image_brow_makeup"){
				g_params["makeup_intensity_eyeBrow"] = value;
				NativeController.SetParamD("makeup_intensity_eyeBrow", value);
				return 1;
			}else if(name == "image_blush_makeup"){
				g_params["makeup_intensity_blusher"] = value;
				NativeController.SetParamD("makeup_intensity_blusher", value);	
				return 1;			
			}else if(name == "image_lip_makeup"){
				g_params["makeup_intensity_lip"] = value;
				NativeController.SetParamD("makeup_intensity_lip", value);	
				return 1;			
			}else if(name == "image_highlight_makeup"){
				g_params["makeup_intensity_highlight"] = value;
				NativeController.SetParamD("makeup_intensity_highlight", value);
				return 1;
			}else if(name == "image_shadow_makeup"){
				g_params["makeup_intensity_shadow"] = value;
				NativeController.SetParamD("makeup_intensity_shadow", value);	
				return 1;
			}
			if (g_params[name] != undefined && typeof (g_params[name]) == typeof (value)) {
				g_params[name] = value;
				//console.log('here2!!');
				if (value.length != undefined) {
					NativeController.SetParamDV(name, value);
				} else {
					NativeController.SetParamD(name, value);	
				}
				return 1;
			} else {
				return 0;
			}
		},
		GetParam: function (name) {

			name = this.FilterName(name);

			if (name == "tex_brow" || name == "tex_eye" || name == "tex_eye2" || name == "tex_eye3"
				|| name == "tex_eye4" || name == "tex_pupil" || name == "tex_eyeLash" || name == "tex_lip" ||
				name == "tex_eyeLiner" || name == "tex_blusher" || name == "tex_highlight" || name == "tex_lip_highlight"
				|| name == "tex_foundation" || name == "tex_shadow" || name == "tex_lip_mask_bz" || name == "tex_lip_mask_zz"
				|| name == "tex_lip_mask_bite_bz" || name == "tex_lip_mask_bite_zz") {
				return NativeController.GetParamTex(name);
			}
			if (g_params[name] != undefined) {
				return g_params[name];
			} else {
				return 0;
			}
		},
		Destroy: function (params) {
			this.Clear();
		},
		MakeupFilter: function (params) {
			NativeController.MakeupFilter(params);
		},
		Render: function (params) {
			try {

			}
			catch (err) {
				console.log(err.stack);
			}
		},

		UpdateParm: function (updateTypeFlag) {

			var bFullChangeAndShow = false;
			var bDisappear = false;

			switch (updateTypeFlag) {
				case (1 << 10):
					{
						bFullChangeAndShow = true;
					}
					break;
				case (1 << 11):
					{
						bDisappear = true;
					}
					break;
			}

			if (bFullChangeAndShow) {
				this.Clear();

				this.SetParam("is_makeup_on", 1);

				for (var key in g_params.makeup_config_out) {
					var value = g_params.makeup_config_out[key];
					this.SetParam(key, value);
				}

			} else if (bDisappear) {

				if (g_params.makeup_config_out.makeup_name == g_params.makeup_name) {

					//故事线执行美妆消失时由于没有unbind所以需要自行执行unbind内容
					this.ClearConfigInDic(g_params.makeup_config_out);
					this.Clear();
					this.SetParam("is_makeup_on", 0);

				}

			}

		},

		OnUpdate: function (param) {
			try {

				//优先在facemakeup里面执行状态机操作，避免闪烁，其实最好还是合并，但是facemakeup素材太多，比较乱，暂时搁置
				for (var i = 0; i < g_params.bindedArray.length; i++) {

					if (g_params.bindedArray[i] && g_params.bindedArray[i].OnUpdate) {
						g_params.bindedArray[i].OnUpdate(param);
					}

				}

				this.ExcuteStbFlag();

			} catch (error) {
				console.logi(err.stack);
			}
		},

		ExcuteStbFlag: function () {
			if (g_params.makeup_config_out == undefined || g_params.makeup_config_out.makeup_update_flag == undefined
				|| g_params.makeup_config_out.makeup_update_flag == 0) {
				return;
			}

			//console.logi("liufei ExcuteStbFlag");
			//var testArray = [0x1, 0x2, 0x4, 0x8, 0x10, 0x20, 0x40, 0x80, 0x100, 0x200];
			//目前只管消失和出现
			var testArray = [1 << 10, 1 << 11];
			var flag = g_params.makeup_config_out.makeup_update_flag;

			for (var i = 0; i < testArray.length; i++) {
				this.UpdateParm(flag & testArray[i]);
			}
			g_params.makeup_config_out.makeup_update_flag = 0;
		},

		InsertToArray: function (item) {
			if (!this.IsFindInArray(item)) {
				g_params.bindedArray.push(item);
			}
		},

		IsFindInArray: function (item) {
			var bFind = false;
			bFind = g_params.bindedArray.indexOf(item) != -1;
			return bFind;
		},

		DelFromArray: function (item) {
			var index = g_params.bindedArray.indexOf(item);
			if (index > -1) {
				g_params.bindedArray.splice(index, 1);
			}
		},

		OnBind: function (item) {
			if (item.makeup_info != undefined) {

				//console.logi("liufei bind in");

				g_params.makeup_config_out = item.makeup_info;

				//兼容老版本，默认显示。新版本默认关闭
				if (g_params.makeup_config_out.makeup_update_flag == undefined) {

					this.SetParam("is_makeup_on", 1.0);

				} else {

					this.SetParam("is_makeup_on", 0.0);

				}

				NativeController.SetParamD("lip_blend_weight_type", 0.0);
				for (var key in item.makeup_info) {
					//console.loge("OnBind key: ",key,"value: ",value);
					var value = item.makeup_info[key];
					this.SetParam(key, value);
				}

				NativeController.SetParamD("brow_mask_v", 1.0);
				NativeController.SetParamD("eye_mask_v", 1.0);
				NativeController.SetParamD("lip_mask_v", 1.0);
				NativeController.SetParamD("face_mask_v", 1.0);

				NativeController.SetParamD("makeup_intensity", 0.0);  
				// NativeController.SetParamD("makeup_intensity_eye", 0.0);
				// NativeController.SetParamD("makeup_intensity_eyeLiner", 0.0);
				// NativeController.SetParamD("makeup_intensity_eyelash", 0.0);
				// NativeController.SetParamD("makeup_intensity_eyeBrow", 0.0);  
				// NativeController.SetParamD("makeup_intensity_blusher", 0.0);  
				// NativeController.SetParamD("makeup_intensity_lip", 0.0);  
				// NativeController.SetParamD("makeup_intensity_highlight", 0.0);  
				// NativeController.SetParamD("makeup_intensity_shadow", 0.0); 

				this.InsertToArray(item);
			} else {
				g_params.makeup_config_out = undefined;
			}
		},
		OnUnbind: function (item) {

			//console.logi("liufei unbind in");

			if (item.makeup_info != undefined) {

				g_params.makeup_config_out = undefined;

				this.ClearConfigInDic(item.makeup_info);

				this.DelFromArray(item);

				//if (g_params.is_clear_makeup) {
					this.Clear();
				//}
				NativeController.SetParamD("brow_mask_v", 1.0);
				NativeController.SetParamD("eye_mask_v", 1.0);
				NativeController.SetParamD("lip_mask_v", 1.0);
				NativeController.SetParamD("face_mask_v", 1.0);
			}
		},

		ClearConfigInDic: function (makeup_info_dict) {
			for (var key in makeup_info_dict) {
				var start = key.indexOf('intensity');
				if (start >= 0) {
					//console.log('set ',key,'=0');
					this.SetParam(key, 0);
				}
				start = key.indexOf('brow_warp');
				if (start >= 0) {
					//console.log('set ',key,'=0');
					this.SetParam(key, 0);
				}
				var s = key.startsWith('tex_');
				if (s) {
					NativeController.SetParamTex(key, undefined);
				}
			}
		},

		Clear: function () {
			this.SetParam("makeup_intensity_lip", 0.0);
			this.SetParam("brow_warp", 0.0);
			NativeController.Clear();
		},
		LoadResource: function(params){
			NativeController.setRepeatLoad(true);
			NativeController.LoadResource(brow_makeup_data, brow_makeup_data_old, 1);
			NativeController.setRepeatLoad(false);
		},
		name: "face_makeup",
	};
})()