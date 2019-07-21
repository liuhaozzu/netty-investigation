package com.liuhaozzu.designpattern.state;

/**
 * @Author Administrator
 * @create 2019/5/12 0012 10:38
 */
public class GumballMachineNoStatePattern {
    final static int SOLD_OUT=0;
    final static int NO_QUARTER=1;
    final static int HAS_QUARTER=2;
    final static int SOLD=3;

    int state=SOLD_OUT;
    int count = 0;

}
