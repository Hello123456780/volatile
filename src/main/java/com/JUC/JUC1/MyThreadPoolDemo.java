package com.JUC.JUC1;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/*
第4种获得、使用java多线程的方式，线程池
 */
public class MyThreadPoolDemo {


    //自制线程池
    public static void main(String[] args) {

        ExecutorService  executorService=new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 1; i <=10 ; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"处理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }


    //JDK自带线程池
   public void   ThreadPoolJDK(){
       //   ExecutorService executorService= Executors.newFixedThreadPool(5);//指定线程数
       //  ExecutorService executorService = Executors.newSingleThreadExecutor();//只有一个线程
       ExecutorService executorService = Executors.newCachedThreadPool();//根据需求创建线程数
       try {
           for (int i = 1; i <=10 ; i++) {
               executorService.execute(() -> {
                   System.out.println(Thread.currentThread().getName()+"处理业务");
               });
               sleep(1000);
           }

       }catch (Exception e){
           e.printStackTrace();
       }finally {
           executorService.shutdown();
       }

   }
}
