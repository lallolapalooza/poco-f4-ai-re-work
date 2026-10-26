// Verification oracle: run the *real* OpenCV CascadeClassifier with the
// cascade XML extracted from the Snapchat APK, so the JAX reimplementation in
// haar.py can be compared against the exact engine semantics that libcamplat
// embeds (its .rodata contains OpenCV's cascade parser error strings).
//
// build: g++ -O2 -o ref_haar ref_haar.cpp $(pkg-config --cflags --libs opencv5)
// usage: ./ref_haar <cascade.xml> <image.pgm> [min_size] [scale_factor] [min_neighbors]
#include <opencv2/xobjdetect.hpp>
#include <opencv2/imgcodecs.hpp>
#include <iostream>
#include <vector>

int main(int argc, char** argv) {
    if (argc < 3) {
        std::cerr << "usage: ref_haar cascade.xml image.pgm [min_size] [scale] [min_neighbors]\n";
        return 2;
    }
    cv::Mat img = cv::imread(argv[2], cv::IMREAD_GRAYSCALE);
    if (img.empty()) { std::cerr << "cannot read image\n"; return 2; }
    cv::CascadeClassifier cc;
    if (!cc.load(argv[1])) { std::cerr << "cannot load cascade\n"; return 2; }
    int min_size = argc > 3 ? std::atoi(argv[3]) : 24;
    double scale = argc > 4 ? std::atof(argv[4]) : 1.1;
    int min_neighbors = argc > 5 ? std::atoi(argv[5]) : 3;
    std::vector<cv::Rect> faces;
    cc.detectMultiScale(img, faces, scale, min_neighbors, 0, cv::Size(min_size, min_size));
    std::cout << "detections=" << faces.size() << "\n";
    for (const auto& r : faces)
        std::cout << r.x << " " << r.y << " " << r.width << " " << r.height << "\n";
    return 0;
}
