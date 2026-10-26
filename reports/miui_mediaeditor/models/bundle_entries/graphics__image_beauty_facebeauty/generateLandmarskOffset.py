from genericpath import getctime
import cv2 as cv
import os
import json

base_path = os.path.dirname(__file__)
json_path = os.path.join(base_path, 'template_landmarks_uv.json')
result_path = os.path.join(base_path, 'landmarks_offset.json')

def resolveUVFromJson():
    with open(json_path) as fp:
        warp_json = json.load(fp)
        uv = warp_json["uv"]
        return uv

def calculateImgSpacePositionFromUV(uv_set, w, h):
    positions = []
    for i in range(0, int(len(uv_set) / 2)):
        u = uv_set[2*i]
        v = uv_set[2*i+1]
        x_p = u * w
        y_p = v * h
        positions.append(x_p)
        positions.append(y_p)
    return positions

def getColor(positions, img):
    colors = []
    for i in range(0, int(len(positions) / 2)):
        x = positions[2*i]
        y = positions[2*i+1]
        color = img[round(y), round(x)]
        colors.append(int(color[2]))
        colors.append(int(color[1]))
    return colors

def getoffset(uv, img):
    pos_set = calculateImgSpacePositionFromUV(uv, img.shape[1], img.shape[0])
    offset = getColor(pos_set, img)
    return offset


face_thinning_img=cv.imread(os.path.join(base_path, 'face_thinning3.png'))
face_narrow_img=cv.imread(os.path.join(base_path, 'face_narrow.png'))
face_small_img=cv.imread(os.path.join(base_path, 'face_small4.png'))
mouth_template_img=cv.imread(os.path.join(base_path, 'mouth_template_gs.png'))
eye_template_img=cv.imread(os.path.join(base_path, 'eye_template.png'))
nose_template_img=cv.imread(os.path.join(base_path, 'nose_template.png'))
nose_thinning_img=cv.imread(os.path.join(base_path, 'nose_thinning.png'))
chin_template_img=cv.imread(os.path.join(base_path, 'chin_template.png'))
brow_img=cv.imread(os.path.join(base_path, 'meimao.png'))
face_uv = resolveUVFromJson()
# print(template_img.shape)
# print(template_img[0,0])
# print(template_img[0,template_img.shape[1]-1])
# print(template_img[template_img.shape[0]-1,0])
# print(template_img[template_img.shape[0]-1,template_img.shape[1]-1])
face_thinning_offset = getoffset(face_uv, face_thinning_img)
face_narrow_offset = getoffset(face_uv, face_narrow_img)
face_small_offset = getoffset(face_uv, face_small_img)
mouth_offset = getoffset(face_uv, mouth_template_img)
eye_offset = getoffset(face_uv, eye_template_img)
nose_long_offset = getoffset(face_uv, nose_template_img)
nose_thinning_offset = getoffset(face_uv, nose_thinning_img)
chin_offset = getoffset(face_uv, chin_template_img)
brow_offset = getoffset(face_uv, brow_img)

offset_dic = {}
offset_dic["face_thinning"] = face_thinning_offset
offset_dic["face_narrow"] = face_narrow_offset
offset_dic["face_small"] = face_small_offset
offset_dic["mouth"] = mouth_offset
offset_dic["eye"] = eye_offset
offset_dic["nose_long"] = nose_long_offset
offset_dic["nose_thinning"] = nose_thinning_offset
offset_dic["chin"] = chin_offset
offset_dic["brow"] = brow_offset

with open(result_path,"w") as f:
    json.dump(offset_dic,f)
    print("finished!!!")
    





