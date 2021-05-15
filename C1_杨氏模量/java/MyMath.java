package com.augtons.tjuttestc1;

import java.lang.Math;


public class MyMath{
    private static final String[] smallNum = {"⁰", "¹","²","³","⁴","⁵","⁶","⁷","⁸","⁹"};
    /**
     *
     * @param val 想要取舍的数
     * @param where 保留几位小数。0为保留整数，小于零为保留到小数点之前
     * @return 之后的
     */
    public static double superRound(double val, int where){
        double pow = Math.pow(10, where);
        return Math.round(val * pow) / pow;
    }
    public static String superRoundString(double val, int where){
        String str = String.valueOf(superRound(val, where));
        StringBuilder str2 = new StringBuilder();
        for (int i = 0;i < where - str.split("\\.")[1].length(); i++){
            str2.append("0");
        }
        return str + str2;
    }
    /**
     *
     * @param val 想要取舍的数
     * @param where 在第几位小数上。0为整数，小于零为保留到小数点之前
     * @return 进位之后的
     */
    public static double superFloor(double val, int where){
        double pow = Math.pow(10, where);
        return Math.floor(val * pow) / pow;
    }
    public static String superFloorString(double val, int where){
        String str = String.valueOf(superFloor(val, where));
        StringBuilder str2 = new StringBuilder();
        for (int i = 0;i < where - str.split("\\.")[1].length(); i++){
            str2.append("0");
        }
        return str + str2;
    }
    /**
     *
     * @param val 想要取舍的数
     * @param where 在第几位小数上。0为整数，小于零为保留到小数点之前
     * @return 退位之后的
     */
    public static double superCeil(double val, int where){
        double pow = Math.pow(10, where);
        return Math.ceil(val * pow) / pow;
    }
    public static String superCeilString(double val, int where){
        String str = String.valueOf(superCeil(val, where));
        StringBuilder str2 = new StringBuilder();
        for (int i = 0;i < where - str.split("\\.")[1].length(); i++){
            str2.append("0");
        }
        return str + str2;
    }
    /**
     *
     * @param val 想要判断的数
     * @return 整数返回true
     */
    public static boolean isInt(double val){
        return val % (int)val == 0;
    }

    public static String getSmallNum(int val) throws IndexOutOfBoundsException{

        String string = "";
        if(val<0){
            string = "⁻";
        }
        val = Math.abs(val);
        if (val <= 9 && val >=0) {
            return string + smallNum[val];
        }else if (val >= 10 && val <= 99){
            return string + smallNum[val/10] + smallNum[val - 10 * (val/10)];
        }
        else throw new IndexOutOfBoundsException();
    }

    /**
     * 求某数第几位处是第depth位有效数字
     * @param val
     * @param depth
     * @return
     */
    public static int getSignificantFigure(double val, int depth){
        int temp = getHig(val);
        return getHig(val) + depth - 1;
    }

    /**
     * 判断有效数字最高位。小数部分为正。个位数为0
     * @param val
     * @return
     */
    private static int getHig(double val){
        int count = 0;
        if (val > 0){
            for (count = 0; ; count++){
                if ((val / Math.pow(10, count)) < 1){
                    break;
                }
            }
            return 1 - count;
        }else {
            for (count = 0; ;count ++){
                if (val * Math.pow(10, count) > 1){
                    break;
                }
            }
            return count;
        }
    }

    public static String replaceE(double val, int where){
        String stringY = String.valueOf(val);
        String[] stringsY;
        if(stringY.contains("E")){
            stringsY = stringY.split("E");
            stringsY[0] = stringsY[0].substring(0, where + 1);
            return stringsY[0] + "×10"+MyMath.getSmallNum(Integer.parseInt(stringsY[1]));
        }else{
            return MyMath.superRoundString(val, where);
        }
    }
}
