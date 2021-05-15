package com.augtons.tjuttestc1;


import android.os.Parcel;
import android.os.Parcelable;

public class TestC1 implements Parcelable {
    public static final double captal_F = 58.8;// 单位N
    public static final double uL = 0.5; //单位毫米
    public static final double ux = 0.5; //单位毫米
    public static final double ub = 0.5; //单位毫米

    public double[] m0;
    public double[] m2;
    public double[] m4;
    public double[] m6;
    public double[] m8;
    public double[] m10;

    public double m0_average;
    public double m2_average;
    public double m4_average;
    public double m6_average;
    public double m8_average;
    public double m10_average;

    public double[] ds; // 修正值
    public double average_D;

    public double captal_L;
    public double x;
    public double b;

    public double captal_Y;

    public double delta_n;
    public double delta_n1;
    public double delta_n2;
    public double delta_n3;

    public double uD;
    public double u_delta_n;

    public double uY;

    public TestC1(double[] m0, double[] m2, double[] m4, double[] m6, double[] m8, double[] m10,
                  double[] ds, double captal_L, double x, double b){
        this.m0 = m0;
        this.m2 = m2;
        this.m4 = m4;
        this.m6 = m6;
        this.m8 = m8;
        this.m10 = m10;

        this.ds = ds;

        this.captal_L = captal_L;
        this.b = b;
        this.x = x;
    }

    protected TestC1(Parcel in) {
        m0 = in.createDoubleArray();
        m2 = in.createDoubleArray();
        m4 = in.createDoubleArray();
        m6 = in.createDoubleArray();
        m8 = in.createDoubleArray();
        m10 = in.createDoubleArray();
        m0_average = in.readDouble();
        m2_average = in.readDouble();
        m4_average = in.readDouble();
        m6_average = in.readDouble();
        m8_average = in.readDouble();
        m10_average = in.readDouble();
        ds = in.createDoubleArray();
        average_D = in.readDouble();
        captal_L = in.readDouble();
        x = in.readDouble();
        b = in.readDouble();
        captal_Y = in.readDouble();
        delta_n = in.readDouble();
        delta_n1 = in.readDouble();
        delta_n2 = in.readDouble();
        delta_n3 = in.readDouble();
        uD = in.readDouble();
        u_delta_n = in.readDouble();
        uY = in.readDouble();
    }

    public static final Creator<TestC1> CREATOR = new Creator<TestC1>() {
        @Override
        public TestC1 createFromParcel(Parcel in) {
            return new TestC1(in);
        }

        @Override
        public TestC1[] newArray(int size) {
            return new TestC1[size];
        }
    };

    public void start(){
        m0_average = (m0[0] + m0[1]) / 2;
        m2_average = (m2[0] + m2[1]) / 2;
        m4_average = (m4[0] + m4[1]) / 2;
        m6_average = (m6[0] + m6[1]) / 2;
        m8_average = (m8[0] + m8[1]) / 2;
        m10_average = (m10[0] + m10[1]) / 2;

        for (int i = 0; i < ds.length; i++){
            ds[i] *= 1e-3; // 化成米
        }
        average_D = (ds[0] + ds[1] + ds[2] + ds[3] + ds[4] + ds[5]) / 6;

        delta_n = (m6_average + m8_average + m10_average - m0_average - m2_average - m4_average) / 3;
        delta_n1 = m6_average - m0_average;
        delta_n2 = m8_average - m2_average;
        delta_n3 = m10_average - m4_average;

        captal_Y = (8 * captal_L * x * captal_F) / (Math.PI * average_D *average_D * b * delta_n);
        u_delta_n = 1.32 * Math.sqrt((
                Math.pow(delta_n1 - delta_n, 2) +
                        Math.pow(delta_n2 - delta_n, 2) +
                        Math.pow(delta_n3 - delta_n, 2)
        ) / 6);
        uD = 1.11 * Math.sqrt((
                Math.pow(ds[0] - average_D, 2) +
                        Math.pow(ds[1] - average_D, 2) +
                        Math.pow(ds[2] - average_D, 2) +
                        Math.pow(ds[3] - average_D, 2) +
                        Math.pow(ds[4] - average_D, 2) +
                        Math.pow(ds[5] - average_D, 2)
        ) / 30);

        uY = captal_Y * Math.sqrt(
                25e-8 / (captal_L * captal_L)+
                        25e-8 / (x * x) +
                        25e-8 / (b * b) +
                        4*uD*uD / (average_D * average_D) +
                        u_delta_n * u_delta_n / (delta_n * delta_n)
        );
    }

    public String getString(int depth){
        int needed = MyMath.getSignificantFigure(uY, depth);
        double u = MyMath.superCeil(uY, needed);
        double r = MyMath.superRound(captal_Y, needed);
        if (needed > 0){
            // 小数位置
            return ("(" + MyMath.superRoundString(r, needed) + " ± " + MyMath.superCeilString(u, needed) +") N/m²");
        }else if (needed == 0){
            return ("(" + (int)r + " ± " + (int)u +") N/m²");
        }else{
            double r2 = r * Math.pow(10, needed);
            double u2 = u * Math.pow(10, needed);
            return ("(" + (int)r2 + " ± " + (int)u2 +")×10" + MyMath.getSmallNum((-1) * needed) +" N/m²");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDoubleArray(m0);
        dest.writeDoubleArray(m2);
        dest.writeDoubleArray(m4);
        dest.writeDoubleArray(m6);
        dest.writeDoubleArray(m8);
        dest.writeDoubleArray(m10);
        dest.writeDouble(m0_average);
        dest.writeDouble(m2_average);
        dest.writeDouble(m4_average);
        dest.writeDouble(m6_average);
        dest.writeDouble(m8_average);
        dest.writeDouble(m10_average);
        dest.writeDoubleArray(ds);
        dest.writeDouble(average_D);
        dest.writeDouble(captal_L);
        dest.writeDouble(x);
        dest.writeDouble(b);
        dest.writeDouble(captal_Y);
        dest.writeDouble(delta_n);
        dest.writeDouble(delta_n1);
        dest.writeDouble(delta_n2);
        dest.writeDouble(delta_n3);
        dest.writeDouble(uD);
        dest.writeDouble(u_delta_n);
        dest.writeDouble(uY);
    }
}
