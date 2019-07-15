package com.liuhaozzu.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegesDemo {
    @Test
    public void startEndTest() {
        //^代表待匹配字符串的起始位置，$代表待匹配字符串的结束位置，
        Pattern pattern = Pattern.compile("^\\d{5}$");
        System.out.println(pattern.pattern());
        Matcher m = pattern.matcher("12345a");
        boolean flag = m.matches();
        System.out.println(flag);
        //匹配的子字符串在字符串中的起始位置
        System.out.println(m.start());

        //匹配的子字符串在字符串中的最后一个位置
        System.out.println(m.end());

        //返回第二组匹配的到的字符串
        System.out.println(m.group());
    }

    /**
     * 分支条件
     */
    @Test
    public void fenzhitiaojian() {
        Pattern p=Pattern.compile("\\(?0\\d{2}[)  -]?\\d{8}");

        Matcher m1 = p.matcher("(010)88886666");
        System.out.println(m1.matches());

        //错误的匹配，应该返回false，却返回了true
        Matcher m2 = p.matcher("010)12345678");
        System.out.println(m2.matches());

        /*
        * 0\d{2}-\d{8}|0\d{3}-\d{7}
        *
        * \(0\d{2}\)[- ]?\d{8}|0\d{2}[- ]?\d{8}
        *
        * 分支条件匹配时，从左到右测试每个条件，如果满足某个分支的话，就会忽略之后的条件
        * \d{5}-\d{4}|\d{5}
        *
        *
        *
        * */

        Pattern p3=Pattern.compile("0\\d{2}[- ]?\\d{8}|\\(0\\d{2}\\)[- ]?\\d{8}");
        Matcher m3 = p3.matcher("010)12345678");
        System.out.println(m3.matches());
    }

    @Test
    public void fenzuTest() {
        Pattern p = Pattern.compile("(\\d{1,3}\\.){3}\\d{1,3}");
        Matcher m = p.matcher("123.123.123.123");
        System.out.println(m.matches());

        String illegalIp = "256.300.888.999";
        Matcher m0 = p.matcher(illegalIp);
        System.out.println(m0.matches());


        Pattern p1 = Pattern.compile("(2[0-4]\\d|25[0-5]|[01]?\\d\\d?\\.){3}(2[0-4]{2}|25[0-5]|[01]?\\d\\d?)");
        Matcher m1 = p1.matcher(illegalIp);
        System.out.println(m1.matches());
        Matcher m2 = p1.matcher("123.123.123.123");
        System.out.println(m2.matches());


    }
}
