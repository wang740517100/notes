package cn.wangkf.monday.course.other;

import java.text.DecimalFormat;

/**
 * Created by stanley.wang on 2020/6/29.
 *
 * 求一个数的立方根
 */
public class Exe1 {

    public static void main(String[] args) {
        System.out.println(getLiFangGen(81));
    }


    // 牛顿迭代法
    public static double getLiFangGen(double num) {
        if (num == 0) {
            return num;
        }

        double num1,num2;
        num1 = num;
        num2 = (2*num1/3)+(num/(num1*num1*3));//利用牛顿迭代法求解
        while(Math.abs(num2-num1)>0.000001){
            num1=num2;
            num2=(2*num1/3)+(num/(num1*num1*3));
        }
        DecimalFormat df = new DecimalFormat("#.0");
        return Double.parseDouble(df.format(num2));
    }
}
