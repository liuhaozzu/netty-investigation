package com.liuhaozzu.designpattern.command.impl;

import com.liuhaozzu.designpattern.command.Command;
import com.liuhaozzu.designpattern.command.Light;

import java.util.Iterator;

/**
 * @Author Administrator
 * @create 2019/5/4 0004 22:07
 */
public class LightOffCommand implements Command {
    private Light light;


    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

}
