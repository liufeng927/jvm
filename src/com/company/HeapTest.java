package com.company;

import java.util.ArrayList;

/**
 * @description: 根据此类 运行jvisualvm查看堆区的内存情况
 * @Date: 2020-06-14 10:15
 * @author: liufeng
 **/
public class HeapTest {

    byte[] bytes = new byte[1024*100];//100kb

    public static void main(String[] args) {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true){
            heapTests.add(new HeapTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
