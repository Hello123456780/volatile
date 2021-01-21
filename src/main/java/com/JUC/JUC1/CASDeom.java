package com.JUC.JUC1;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDeom {

   /*

   CAS是什么？  ==>  compareAndSet
    比较并交换
    */

    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 222222)+"\t"+atomicInteger.get());
        atomicInteger.getAndIncrement();
    }

}
