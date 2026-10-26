void main(){
						vec2 st= (uv1-aabb_xy)/aabb_wh;
						gl_Position = vec4(st*2.0-1.0, 0.0, 1.0);
						st1 = uv1;
						st2 = uv2;
					}