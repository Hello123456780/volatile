package com.JUC.JUC1;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
CyclicBarrier 当指定线程都到了，才可以启动主线程,先到的线程阻塞

 */
public class CyclicBarrierDeom {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,() -> {
            System.out.println("集齐了，开始走");
        });
        for (int i = 1; i <=7 ; i++) {

            final int temInt=i;
            new Thread(() -> {

                try {

                    System.out.println(Thread.currentThread().getName()+"第几颗"+temInt);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }

}
