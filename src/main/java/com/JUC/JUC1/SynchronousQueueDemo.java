package com.JUC.JUC1;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static java.lang.Thread.sleep;

/*
SynchronousQueue
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String>  blockingQueue=new SynchronousQueue<>();
        new Thread(() -> {
            try {

                System.out.println(Thread.currentThread().getName()+"put 1");
              blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"take 1");
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"take 2");
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"take 3");
            try {
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
