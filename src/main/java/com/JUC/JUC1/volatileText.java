package com.JUC.JUC1;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;


public class volatileText {
      int a=0;
      boolean flage=false;
     public void method01(){
           a=1;
         flage=true;
     }
     public  void  method02(){
         if (flage){
             a=a+5;
             System.out.println("aaaa:"+a);
         }
     }


     //volatile禁止指令重排
    public static void main(String[] args) {
        final volatileText volatileText = new volatileText();
        for (int i = 1; i <=2 ; i++) {
      new Thread(new Runnable() {
          public void run() {
           volatileText.method01();
           volatileText.method02();
              }


      }).start();
        }
    }
    //volatile的不保证原子性   出现丢失数据的情况。
    public static  void atomicvolatile(){
        final Data data = new Data();
        for (int i = 1; i <=20 ; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 1 ;j <=1000 ; j++) {
                        data.add1();
                        //                  data.add2();
                    }

                }
            }).start();

        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+data.a);
        //  System.out.println(Thread.currentThread().getName()+data.atomicInteger);
    }
    //volatile的可见性
public static  void  seeokvolatile(){
    final    Data da = new Data();
    new  Thread(new Runnable() {
        public void run() {
            System.out.println(da.a);
            try {
                sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            da.add();
            System.out.println(da.a);
        }
    }).start();

    while (da.a==0){
        //没有通知main线程的话，一直在循环无法执行下面的代码
    }
    System.out.println("xxx"+da.a);
}
}
class Data{

    volatile int a=0;
    public void add(){
        this.a=60;
    }
    public   void   add1(){
        a++;
    }
      AtomicInteger atomicInteger=new AtomicInteger();
    public   void add2(){
        atomicInteger.getAndIncrement();
    }
}


