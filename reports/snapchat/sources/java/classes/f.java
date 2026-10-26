package defpackage;

import com.google.protobuf.nano.MessageNano;

/* JADX INFO: renamed from: f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes5.dex */
public final class C19630f extends HA5 {
    public static volatile C19630f[] S;
    public C18392e[] R;
    public int b = 0;
    public String c = "";

    public C19630f() {
        if (C18392e.T == null) {
            synchronized (AbstractC15160bO7.b) {
                if (C18392e.T == null) {
                    C18392e.T = new C18392e[0];
                }
            }
        }
        this.R = C18392e.T;
        this.a = null;
        this.cachedSize = -1;
    }

    @Override // defpackage.HA5, com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        int iComputeSerializedSize = super.computeSerializedSize();
        if ((this.b & 1) != 0) {
            iComputeSerializedSize += C17019ct2.s(1, this.c);
        }
        C18392e[] c18392eArr = this.R;
        if (c18392eArr != null && c18392eArr.length > 0) {
            int i = 0;
            while (true) {
                C18392e[] c18392eArr2 = this.R;
                if (i >= c18392eArr2.length) {
                    break;
                }
                C18392e c18392e = c18392eArr2[i];
                if (c18392e != null) {
                    iComputeSerializedSize += C17019ct2.m(2, c18392e);
                }
                i++;
            }
        }
        return iComputeSerializedSize;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(C15772bt2 c15772bt2) throws NP7 {
        while (true) {
            int iX = c15772bt2.x();
            if (iX == 0) {
                break;
            }
            if (iX == 10) {
                this.c = c15772bt2.w();
                this.b |= 1;
            } else if (iX == 18) {
                int iZ0 = AbstractC28720mL5.z0(c15772bt2, 18);
                C18392e[] c18392eArr = this.R;
                int length = c18392eArr == null ? 0 : c18392eArr.length;
                int i = iZ0 + length;
                C18392e[] c18392eArr2 = new C18392e[i];
                if (length != 0) {
                    System.arraycopy(c18392eArr, 0, c18392eArr2, 0, length);
                }
                while (length < i - 1) {
                    c18392eArr2[length] = new C18392e();
                    c15772bt2.m(c18392eArr2[length]);
                    c15772bt2.x();
                    length++;
                }
                c18392eArr2[length] = new C18392e();
                c15772bt2.m(c18392eArr2[length]);
                this.R = c18392eArr2;
            } else if (!f(c15772bt2, iX)) {
                break;
            }
        }
        return this;
    }

    @Override // defpackage.HA5, com.google.protobuf.nano.MessageNano
    public final void writeTo(C17019ct2 c17019ct2) throws QX5 {
        if ((this.b & 1) != 0) {
            c17019ct2.Z(1, this.c);
        }
        C18392e[] c18392eArr = this.R;
        if (c18392eArr != null && c18392eArr.length > 0) {
            int i = 0;
            while (true) {
                C18392e[] c18392eArr2 = this.R;
                if (i >= c18392eArr2.length) {
                    break;
                }
                C18392e c18392e = c18392eArr2[i];
                if (c18392e != null) {
                    c17019ct2.O(2, c18392e);
                }
                i++;
            }
        }
        super.writeTo(c17019ct2);
    }
}
