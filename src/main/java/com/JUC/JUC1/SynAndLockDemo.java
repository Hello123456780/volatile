package com.JUC.JUC1;



/*
syn 和 lock的区别？ lock有什么好处举例说明

A-B-C线程  依次打印 5 次  10 次 15 次
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class  Resouce{
    private  int number=1;
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    public  void  print5(){

        lock.lock();
        try {
            //判断
            while (number!=1){
                c1.await();
            }
            //操作
            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            //设置标志位
            number=2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public  void  print10(){

        lock.lock();
        try {
            //判断
            while (number!=2){
                c2.await();
            }
            //操作
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            //设置标志位
            number=3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public  void  print15(){

        lock.lock();
        try {
            //判断
            while (number!=3){
                c3.await();
            }
            //操作
            for (int i = 0; i <15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            //设置标志位
            number=1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }


}

public class SynAndLockDemo {
    public static void main(String[] args) {
        Resouce resouce=new Resouce();
        for (int i = 1; i <= 5; i++){
            new Thread(() -> {

                try {
                    resouce.print5();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "AA").start();
        }
        for (int i = 1; i <= 5; i++){
            new Thread(() -> {

                try {
                    resouce.print10();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "BB").start();
        }
        for (int i = 1; i <= 5; i++){
            new Thread(() -> {

                try {
                    resouce.print15();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "CC").start();
        }
    }
}
