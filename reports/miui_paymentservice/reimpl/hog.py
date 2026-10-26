"""Reference (numpy) implementation of CFEATURE_HOG::ExtractGradientOrientation
from libdigit_ocr.so (address 0x14fd8, size 0x478).

Recovered contract (confirmed against the shipped arm64 library, see verify.txt):

  input : 640-byte grayscale buffer, 20 wide x 32 high, 8-bit, row-major
          (the function returns -1 unless w == 20 and h == 32)
  1. binarise IN PLACE          img[i] = 255 if img[i] > 80 else 0
  2. horizontal gradient        gx[y][x] = img[y][x] - img[y][x-1] for x >= 1;
                                gx[y][0] = 0 (column 0 is never written)
  3. vertical gradient          gy[y][x] = img[y][x] - img[y-1][x] for y >= 1;
                                gy[0][x] = 0 (row 0 is never written)
                                (both confirmed pixel-exactly against the
                                 shipped library on random + shape images)
  4. per-pixel orientation      ang = atan2(gy, gx) * 180/pi + 180
                                if ang >= 360: ang -= 360
                                cell = (x/5) + (y/8)*4      # 4x4 cells of 5x8 px
                                bin  = cell*9 + int(ang/40)  # 9 bins of 40 deg
                                hist[bin] += 1
  5. normalise                  feat[bin] = hist[bin] / 40.0   # cell area = 40
  output: 144 floats (index 0..143, libsvm feature index 1..144).
"""
import numpy as np

W, H = 20, 32
BIN_THRESHOLD = 80        # 0x50
CELL_W, CELL_H = 5, 8
N_ORIENT = 9
ORIENT_DEG = 40.0         # 360 / 9
N_CELLS = (W // CELL_W) * (H // CELL_H)     # 16
N_FEATURES = N_CELLS * N_ORIENT             # 144


def binarize(img):
    img = np.asarray(img, dtype=np.uint8).reshape(-1).copy()
    return np.where(img > BIN_THRESHOLD, 255, 0).astype(np.uint8)


def gradients(binimg):
    b = binimg.astype(np.int32).reshape(H, W)
    gx = np.zeros((H, W), dtype=np.int32)
    gy = np.zeros((H, W), dtype=np.int32)
    gx[:, 1:] = b[:, 1:] - b[:, :-1]
    gy[1:, :] = b[1:, :] - b[:-1, :]
    return gx.reshape(-1), gy.reshape(-1)


def extract(img, dtype=np.float32):
    """HOG of one 20x32 digit patch -> float32[144].  Input is not modified."""
    b = binarize(img)
    gx, gy = gradients(b)
    gxf = gx.astype(np.float64)
    gyf = gy.astype(np.float64)

    ang = np.degrees(np.arctan2(gyf, gxf)) + 180.0
    ang = np.where(ang >= 360.0, ang - 360.0, ang)

    ys, xs = np.mgrid[0:H, 0:W]
    ys = ys.reshape(-1)
    xs = xs.reshape(-1)
    cell = (xs // CELL_W) + (ys // CELL_H) * (W // CELL_W)
    obin = (ang / ORIENT_DEG).astype(np.int64)      # trunc toward zero, ang>=0
    obin = np.clip(obin, 0, N_ORIENT - 1)
    bins = cell * N_ORIENT + obin

    hist = np.bincount(bins, minlength=N_FEATURES).astype(np.float64)
    return (hist / 40.0).astype(dtype)
