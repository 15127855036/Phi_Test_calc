package com.augtons.tjuttestc9;

import android.os.Parcel;
import android.os.Parcelable;

public class TestC9 implements Parcelable {
    public double[] d30;
    public double[] d29;
    public double[] d28;
    public double[] d27;
    public double[] d26;
    public double[] d20;
    public double[] d19;
    public double[] d18;
    public double[] d17;
    public double[] d16;

    public double[] dl;
    public double[] dr;

    public double d30d20;
    public double d29d19;
    public double d28d18;
    public double d27d17;
    public double d26d16;

    public double dmdn_average;
    public double r_average;
    public double udmdn;
    public double u_r;

    public TestC9(double[] dl, double[] dr){
        this.dl = dl;
        this.dr = dr;
    }

    protected TestC9(Parcel in) {
        d30 = in.createDoubleArray();
        d29 = in.createDoubleArray();
        d28 = in.createDoubleArray();
        d27 = in.createDoubleArray();
        d26 = in.createDoubleArray();
        d20 = in.createDoubleArray();
        d19 = in.createDoubleArray();
        d18 = in.createDoubleArray();
        d17 = in.createDoubleArray();
        d16 = in.createDoubleArray();
        dl = in.createDoubleArray();
        dr = in.createDoubleArray();
        d30d20 = in.readDouble();
        d29d19 = in.readDouble();
        d28d18 = in.readDouble();
        d27d17 = in.readDouble();
        d26d16 = in.readDouble();
        dmdn_average = in.readDouble();
        r_average = in.readDouble();
        udmdn = in.readDouble();
        u_r = in.readDouble();
    }

    public static final Creator<TestC9> CREATOR = new Creator<TestC9>() {
        @Override
        public TestC9 createFromParcel(Parcel in) {
            return new TestC9(in);
        }

        @Override
        public TestC9[] newArray(int size) {
            return new TestC9[size];
        }
    };

    public void start(){
        d30 = new double[]{dl[0] - dr[0], Math.pow(dl[0] - dr[0], 2)};
        d29 = new double[]{dl[1] - dr[1], Math.pow(dl[1] - dr[1], 2)};
        d28 = new double[]{dl[2] - dr[2], Math.pow(dl[2] - dr[2], 2)};
        d27 = new double[]{dl[3] - dr[3], Math.pow(dl[3] - dr[3], 2)};
        d26 = new double[]{dl[4] - dr[4], Math.pow(dl[4] - dr[4], 2)};
        d20 = new double[]{dl[5] - dr[5], Math.pow(dl[5] - dr[5], 2)};
        d19 = new double[]{dl[6] - dr[6], Math.pow(dl[6] - dr[6], 2)};
        d18 = new double[]{dl[7] - dr[7], Math.pow(dl[7] - dr[7], 2)};
        d17 = new double[]{dl[8] - dr[8], Math.pow(dl[8] - dr[8], 2)};
        d16 = new double[]{dl[9] - dr[9], Math.pow(dl[9] - dr[9], 2)};

        d30d20 = d30[1] - d20[1];
        d29d19 = d29[1] - d19[1];
        d28d18 = d28[1] - d18[1];
        d27d17 = d27[1] - d17[1];
        d26d16 = d26[1] - d16[1];

        dmdn_average = 0.2 * (d30d20 + d29d19 + d28d18 + d27d17 + d26d16);
        r_average = dmdn_average / 0.023572; //毫米

        udmdn = Math.sqrt(
                (
                        Math.pow(d30d20 - dmdn_average, 2) +
                                Math.pow(d29d19 - dmdn_average, 2) +
                                Math.pow(d28d18 - dmdn_average, 2) +
                                Math.pow(d27d17 - dmdn_average, 2) +
                                Math.pow(d26d16 - dmdn_average, 2)
                ) / 20
        );
        u_r = udmdn / 0.023572; //毫米
    }

    public String getString(int depth){
        int needed = MyMath.getSignificantFigure(u_r, depth);
        double u = MyMath.superCeil(u_r, needed);
        double r = MyMath.superRound(r_average, needed);
        if (needed > 0){
            // 小数位置
            return ("(" + r + " ± " + u +") mm");
        }else if (needed == 0){
            return ("(" + (int)r + " ± " + (int)u +") mm");
        }else{
            double r2 = r * Math.pow(10, needed);
            double u2 = u * Math.pow(10, needed);
            return ("(" + (int)r2 + " ± " + (int)u2 +")×10" + MyMath.getSmallNum((-1) * needed - 1) +" mm");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDoubleArray(d30);
        dest.writeDoubleArray(d29);
        dest.writeDoubleArray(d28);
        dest.writeDoubleArray(d27);
        dest.writeDoubleArray(d26);
        dest.writeDoubleArray(d20);
        dest.writeDoubleArray(d19);
        dest.writeDoubleArray(d18);
        dest.writeDoubleArray(d17);
        dest.writeDoubleArray(d16);
        dest.writeDoubleArray(dl);
        dest.writeDoubleArray(dr);
        dest.writeDouble(d30d20);
        dest.writeDouble(d29d19);
        dest.writeDouble(d28d18);
        dest.writeDouble(d27d17);
        dest.writeDouble(d26d16);
        dest.writeDouble(dmdn_average);
        dest.writeDouble(r_average);
        dest.writeDouble(udmdn);
        dest.writeDouble(u_r);
    }
}
