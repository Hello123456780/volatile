package com.JUC.JUC1;


import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

/*
   手动写自旋锁
   优点：减少线程切换消耗
   缺点:一直在消耗cpu
   线程不会阻塞，一直会循环

 */
public class SpinklockDeom {
        AtomicReference<Thread>  atomicReference=new AtomicReference<>();

        public  void  mylock(){
            Thread thread = Thread.currentThread();
            System.out.println(Thread.currentThread().getName() + "\t" + "上锁");
            while (!atomicReference.compareAndSet(null,thread)){

            }

        }
        public  void myUnlock(){
            Thread thread = Thread.currentThread();
            System.out.println(Thread.currentThread().getName() + "\t" + "解锁");
            atomicReference.compareAndSet(thread,null);
        }

    public static void main(String[] args) throws InterruptedException {

            SpinklockDeom spinklockDeom=new SpinklockDeom();

            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    spinklockDeom.mylock();
                    sleep(5000);
                    spinklockDeom.myUnlock();
                }
            }).start();
          sleep(1000);
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                spinklockDeom.mylock();
                sleep(1000);
                spinklockDeom.myUnlock();
            }
        }).start();
    }

}
