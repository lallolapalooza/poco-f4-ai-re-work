void main(){
						vec3 origin = texture2D(tex_origin, st1).rgb;
						
						float mask = texture2D(tex_mask, st2).a;
						if(use_second > 0.5)
						{
							origin = texture2D(tex_mask, st2).rgb;
							origin = origin * mask;
						}
						gl_FragColor = vec4(origin, mask);
					}