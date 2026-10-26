(function () {

	var makeup_config = JSON.parse(FaceUnity.ReadFromCurrentItem("makeup_config.json"));
	for (key in makeup_config) {

		var value = makeup_config[key];
		//console.loge("debug-------------- ", key, value);
		if ((key == "tex_eyeBrow" || key == "tex_eye" || key == "tex_eye2" || key == "tex_eye3" || key == "tex_eye4" || key == "tex_pupil" ||
			key == "tex_eyelash" || key == "tex_lip" || key == "tex_highlight" || key == "tex_eyeLiner" || key == "tex_blusher" ||
			key == "tex_blusher2" || key == "tex_highlight" || key == "tex_foundation" || key == "tex_shadow" ||
			key == "tex_lip_highlight" || key == "tex_lip_mask_bite_zz" || key == "tex_lip_mask_bite_bz" || key == "tex_lip_mask_zz" || key == "tex_lip_mask_bz")) {
			var tex = FaceUnity.LoadTexture(value, 0);
			if (tex != undefined) {
				makeup_config[key] = tex;
			}
			else {
				makeup_config[key] = 0;
			}
		}

	}
	return {
		Render: function (params) {
			//nothing
		},
		name: "makeup_info",
		makeup_info: makeup_config,
		OnBind: function () {

		},
		OnUnbind: function (item) {

		}
	};
})()