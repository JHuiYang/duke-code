package com.example.demo.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你的身份证号码：");
        String s = input.next();
        while(s.length()!=18)           //输入个数判断，初步验证身份证号码的真假
        {
            System.out.println("你的输入有误，请重新输入！");
            s = input.next();

        }
        //  通过String[]的substring方法来读取信息
        String yy1 = s.substring(6,10);          //出生的年份
        String mm1 = s.substring(10,12);       //出生的月份
        String dd1 = s.substring(12,14);         //出生的日期
        System.out.println("你的生日为：");
        String birthday = yy1.concat("-").concat(mm1).concat("-").concat(dd1);
        //使用replace替换掉字符串中出现的0
        System.out.println(mm1.replace("0","")+"月"+dd1.replace("0","")+"号");
        System.out.println("你的出生日期:");
        System.out.println(birthday);
        //计算年龄
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
            String s1 = sdf.format(date);
            Date today = sdf.parse(s1);
            Date birth = sdf.parse(birthday);
            System.out.println("你的年龄：");
            System.out.println(today.getYear() - birth.getYear());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
