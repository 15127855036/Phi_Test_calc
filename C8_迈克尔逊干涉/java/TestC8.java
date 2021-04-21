package com.augtons.tjuttestc8;

import java.lang.Math;

public class TestC8 {
    private final String[] angelNum = {"¹","²","³","⁴","⁵","⁶","⁷","⁸","⁹"};

    public double d1;
    public double d2;
    public double d3;
    public double d4;
    public double d5;
    public double d6;
    public double d7;
    public double d8;
    public double d9;
    public double d10;

    public double delta_d1;
    public double delta_d2;
    public double delta_d3;
    public double delta_d4;
    public double delta_d5;

    public double delta_d_average;
    public double lambda_average;

    public double ua;
    public double ub;
    public double u_d;
    public double u_lambda;

    public TestC8(double d1, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10){
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.d5 = d5;
        this.d6 = d6;
        this.d7 = d7;
        this.d8 = d8;
        this.d9 = d9;
        this.d10 = d10;
    }

    public void setU_lambda(double u_lambda) {
        this.u_lambda = u_lambda;
    }

    public void start(){
        delta_d1 = Math.abs(d6 - d1);
        delta_d2 = Math.abs(d7 - d2);
        delta_d3 = Math.abs(d8 - d3);
        delta_d4 = Math.abs(d9 - d4);
        delta_d5 = Math.abs(d10 - d5);

        delta_d_average = 0.2 * (delta_d1 + delta_d2 + delta_d3 + delta_d4 + delta_d5);
        lambda_average = 2 * delta_d_average * Math.pow(10, 6) / 250;

        //todo 求Ua
        double fenzi = Math.pow(delta_d1 - delta_d_average, 2) +
                Math.pow(delta_d2 - delta_d_average, 2) +
                Math.pow(delta_d3 - delta_d_average, 2) +
                Math.pow(delta_d4 - delta_d_average, 2) +
                Math.pow(delta_d5 - delta_d_average, 2);
        ub = 0.5 / 10000 / Math.pow(3, 0.5) * Math.pow(10, 6);
        ua = 1.14 * Math.sqrt(fenzi / 20) * Math.pow(10, 6);
        u_d = Math.sqrt(Math.pow(ua, 2) + Math.pow(ub, 2));
        u_lambda = 2 * u_d / 250;
    }

    public String getString(){
        if (getHig(u_lambda) < 1){
            if (my_up(u_lambda, 1-getHig(u_lambda)) < 1) {
                return new StringBuilder()
                        .append(get_lambda(u_lambda))
                        .append("±")
                        .append(my_up(u_lambda, 1 - getHig(u_lambda)))
                        .toString();
            }else {
                return new StringBuilder()
                        .append((int)(get_lambda(u_lambda)))
                        .append("±")
                        .append((int)(my_up(u_lambda, 1-getHig(u_lambda))))
                        .toString();
            }
        }else if (getHig(u_lambda) > 1) {
            int mangelNum = (getHig(u_lambda)) - 2;
            if (mangelNum >= 0 || mangelNum <= 8) {
                return new StringBuilder()
                        .append("(")
                        .append((int) (get_lambda(u_lambda) / Math.pow(10, (getHig(u_lambda) - 1))))
                        .append("±")
                        .append((int) ((int) (my_up(u_lambda, 1 - getHig(u_lambda))) * Math.pow(10, (1 - getHig(u_lambda)))))
//                    .append(") × 10^")
//                    .append((getHig(u_lambda)) - 1)
                        .append(") × 10")
                        .append(angelNum[mangelNum])
                        .toString();
            }else {
                return new StringBuilder()
                        .append("(")
                        .append((int) (get_lambda(u_lambda) / Math.pow(10, (getHig(u_lambda) - 1))))
                        .append("±")
                        .append((int) ((int) (my_up(u_lambda, 1 - getHig(u_lambda))) * Math.pow(10, (1 - getHig(u_lambda)))))
                        .append(") × 10^")
                        .append((getHig(u_lambda)) - 1).toString();
            }
        }else {
            return new StringBuilder()
                    .append((int)(get_lambda(u_lambda)))
                    .append("±")
                    .append((int)(my_up(u_lambda, 1-getHig(u_lambda))))
                    .toString();
        }
    }

    static int getHig(double s){
        if ((int)s == 0){
            return getHig(s * 10) - 1;
        }else{
            return String.valueOf((int)s).length();
        }
    }
    static double myRound(double a, int b){
        return Math.round(a * Math.pow(10, b)) / Math.pow(10, b);
    }
    private double get_lambda(double u){
        return myRound(lambda_average, 1 - getHig(u));
    }
    static double my_up(double a, int b){
        double a2 = (int)(a * Math.pow(10, b)) / Math.pow(10, b);
        if (a2 == a){
            return a2 ;
        }else {
            return myClear(a2 + Math.pow(10, -b), b);
        }
    }

    private static double myClear(double a, double b){
        int a2 = (int) (a * Math.pow(10, b));
        return a2 / Math.pow(10, b);
    }
}