package com.looksery.sdk.domain;

import defpackage.AbstractC19915fE3;
import defpackage.CZe;

/* JADX INFO: loaded from: classes2.dex */
public final class Size {
    private static final int ALIGN = 32;
    private static final int MIN_ENCODER_BUFFER_SIZE = 25600;
    private static final Size OPTIMAL_FOR_PROCESSING = new Size(600, 800);
    public final int height;
    public final int width;

    public Size(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public Size adjustForVideoEncoding() {
        int iCeil = this.width / 16;
        int iCeil2 = this.height / 16;
        int i = iCeil * iCeil2 * 16 * 16;
        if (i < MIN_ENCODER_BUFFER_SIZE) {
            double d = i;
            Double.isNaN(d);
            double dSqrt = Math.sqrt(25600.0d / d);
            double d2 = iCeil;
            Double.isNaN(d2);
            iCeil = (int) Math.ceil(d2 * dSqrt);
            double d3 = iCeil2;
            Double.isNaN(d3);
            iCeil2 = (int) Math.ceil(d3 * dSqrt);
        }
        return new Size(iCeil * 16, iCeil2 * 16);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Size.class != obj.getClass()) {
            return false;
        }
        Size size = (Size) obj;
        return this.width == size.width && this.height == size.height;
    }

    public Size flipAxes() {
        return new Size(this.height, this.width);
    }

    public float getAspectRatio() {
        return getHeight() / getWidth();
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (this.width * 31) + this.height;
    }

    public Size optimizeForProcessing() {
        Size size = OPTIMAL_FOR_PROCESSING;
        double d = size.width;
        double d2 = size.height;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = d * d2;
        double d4 = this.width;
        double d5 = this.height;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double dSqrt = Math.sqrt(d3 / (d4 * d5));
        if (dSqrt >= 1.0d) {
            return this;
        }
        int i = this.width;
        double d6 = i;
        Double.isNaN(d6);
        int i2 = (((int) (d6 * dSqrt)) / 32) * 32;
        double d7 = i2;
        double d8 = i;
        Double.isNaN(d7);
        Double.isNaN(d8);
        double d9 = d7 / d8;
        double d10 = this.height;
        Double.isNaN(d10);
        return new Size(i2, (((int) (d9 * d10)) / 4) * 4);
    }

    public String toString() {
        StringBuilder sbD = AbstractC19915fE3.d("Size { ");
        sbD.append(this.width);
        sbD.append("x");
        return CZe.s(sbD, this.height, " }");
    }
}
