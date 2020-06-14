package com.company;

/**
 * @description: 用此类讲述java运行时数据区
 * @Date: 2020-06-14 09:02
 * @author: liufeng
 *
 * 反汇编：
 *      javap -c Demo.class > Demo.txt
 *      javap -v Demo.class > Demo1.txt
 *
 **/
public class Demo {
    public static final int initA = 888;

    private static User1 user = new User1();

    public int add() { //一个方法对应一个栈帧内存区域
        int a = 10;
        int b = 20;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Demo demo1 = new Demo();
        Demo demo2 = new Demo();
        demo1.add();
    }

}
