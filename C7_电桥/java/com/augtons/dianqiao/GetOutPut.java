package com.augtons.dianqiao;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.Math;

public class GetOutPut implements Parcelable {
    public double[] list = {0.1, 0.1, 0.5, 1, 2, 5};
    public double r1;
    public double r2;
    public double r0;
    public double delta_r0_;
    public double delta_r0;
    public double delta_n;

    public double cap_s;
    public double delta_s;
    public double delta_a;
    public double delta_rx;

    public double cap_Er;
    public double rx;

    public double delta_r1;
    public double delta_r2;
    public GetOutPut(double r1, double r2, double r0, double delta_r0_, double delta_n){
        this.r1 = r1;
        this.r2 = r2;
        this.r0 = r0;
        this.delta_r0_ = delta_r0_;
        this.delta_n = delta_n;
    }

    protected GetOutPut(Parcel in) {
        list = in.createDoubleArray();
        r1 = in.readDouble();
        r2 = in.readDouble();
        r0 = in.readDouble();
        delta_r0_ = in.readDouble(); //deltaR0撇
        delta_r0 = in.readDouble();
        delta_n = in.readDouble();
        cap_s = in.readDouble();
        delta_s = in.readDouble();
        delta_a = in.readDouble();
        delta_rx = in.readDouble();
        cap_Er = in.readDouble();
        rx = in.readDouble();
        delta_r1 = in.readDouble();
        delta_r2 = in.readDouble();
    }

    public static final Creator<GetOutPut> CREATOR = new Creator<GetOutPut>() {
        @Override
        public GetOutPut createFromParcel(Parcel in) {
            return new GetOutPut(in);
        }

        @Override
        public GetOutPut[] newArray(int size) {
            return new GetOutPut[size];
        }
    };

    public void start(){
        rx = r1 * r0 / r2;
        delta_r1 = getDeltaR(r1);
        delta_r2 = getDeltaR(r2);
        delta_r0 = getDeltaR(r0);
        //Er = ((delta_R1 / R1) ** 2 + (delta_R2 / R2) ** 2 + (delta_R0 / R0) ** 2) ** 0.5
        cap_Er =Math.sqrt(Math.pow(delta_r1 / r1, 2) + Math.pow(delta_r2 / r2, 2) + Math.pow(delta_r0 / r0, 2));
        cap_s = r0 * delta_n / delta_r0_;
        delta_s = 0.2 * rx / cap_s;
        delta_a = rx * cap_Er;
        delta_rx = Math.sqrt(Math.pow(delta_a, 2) + Math.pow(delta_s, 2));
    }
    private double getDeltaR(double r){
        double rr = 10 * r;
        double delta_r = 0;
        int len = 0;
        while ((int)(rr / Math.pow(10, len)) > 0){
            len+=1;
        }
        for (int i = 0;i<len;i++){
            //delta_r += int(rr / 10 ** (len - 1 - i)) * list_a[6 - len + i] * 10 ** (len - 1 - i)
            //        rr -= int(rr / 10 ** (len - 1 - i)) * 10 ** (len - 1 - i)
            delta_r += list[6-len+i] * (int)(rr / Math.pow(10, len - 1 - i)) * Math.pow(10, len - 1 - i);
            rr -= Math.pow(10, len - 1 - i) * (int)(rr / Math.pow(10, len - 1 - i));
        }
        return (delta_r / 1000);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDoubleArray(list);
        dest.writeDouble(r1);
        dest.writeDouble(r2);
        dest.writeDouble(r0);
        dest.writeDouble(delta_r0_); //deltaR0撇
        dest.writeDouble(delta_r0);
        dest.writeDouble(delta_n);
        dest.writeDouble(cap_s);
        dest.writeDouble(delta_s);
        dest.writeDouble(delta_a);
        dest.writeDouble(delta_rx);
        dest.writeDouble(cap_Er);
        dest.writeDouble(rx);
        dest.writeDouble(delta_r1);
        dest.writeDouble(delta_r2);
    }
}
