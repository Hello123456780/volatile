package com.JUC.JUC1;


import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/*
当其他线程跑完，你才能开始跑

 */
public class CountDownLatchDeom {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);

        for (int i = 1; i <=6 ; i++) {

            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"\t"+"");
                    countDownLatch.countDown();

                }
            },CountryEnum.add(i).getName()).start();
        }
//        System.out.println(CountryEnum.ONE);
//        System.out.println(CountryEnum.ONE.getName());
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"秦最终统一了六国");
    }


    /*
    非完整版,虽然可以达到指定一个线程最后走，但是其余6个线程会顺序变乱，不知道是哪一个，需要进行一个一个判断。
     */
  public   void   CuntDownLatchText() throws InterruptedException {
      CountDownLatch countDownLatch=new CountDownLatch(6);
      for (int i = 1; i <=6 ; i++) {

          new Thread(new Runnable() {

              @Override
              public void run() {
                  System.out.println(Thread.currentThread().getName()+"\t"+"");
                  countDownLatch.countDown();
              }
          },String.valueOf(i)).start();
      }
      countDownLatch.await();
      System.out.println(Thread.currentThread().getName()+"我是最后一个走的");
  }
}
