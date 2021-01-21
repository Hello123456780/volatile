package com.JUC.JUC1;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

/*
读写锁

写时只允许一个线程写，读可以多个线程

作为缓存  要加volatile 保证可见性和禁止指令重排
 */
public class ReadWritLockDeom {

    static class MyMap{
        private  volatile  Map<String,Object> map=new HashMap<>();
        private ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
        public  void put(String key,Object value){
            reentrantReadWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "正在写入"+key);
                try {
                    sleep(3000);
                } catch (InterruptedException e) {

                }
                map.put(key,value);
                System.out.println(Thread.currentThread().getName() + "\t" + "写入完成");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                reentrantReadWriteLock.writeLock().unlock();
            }



        }
        public   void get(String key){
            reentrantReadWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "正在读取");
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                }
                Object o = map.get(key);
                System.out.println(Thread.currentThread().getName() + "\t" + "读取完成"+o);
            }catch (Exception e){
                 e.printStackTrace();
            }finally {
                reentrantReadWriteLock.readLock().unlock();
            }


        }

    }

    public static void main(String[] args) {
        MyMap myMap=new MyMap();
        for (int i = 1; i <=5 ; i++) {


            final int tempInt= i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                   myMap.put(tempInt+"",tempInt+"");
                }
            }).start();
        }

        for (int i = 1; i <=5 ; i++) {


            final int tempInt= i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myMap.get(tempInt+"");
                }
            }).start();
        }


    }
}
