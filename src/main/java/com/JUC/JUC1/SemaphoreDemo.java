package com.JUC.JUC1;


import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

/*
Semaphore    多个线程对多个资源，5个线程抢三个资源，  剩下的两个资源等待占用的资源释放。在进入资源
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);//三个停车位


        for (int i = 1; i <=6 ; i++) {
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "进入车位");
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + "释放车位");
            }


        }).start();
        }
    }
}
