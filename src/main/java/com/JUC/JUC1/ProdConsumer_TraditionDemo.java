package com.JUC.JUC1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
   线程   操作  资源类

   判断   操作  通知

   防止虚假唤醒    await一定要在 while循环里

 */
class  resouce{

    private  Integer number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public void  Increm() throws InterruptedException {
       lock.lock();
       try {
       //判断
           while (number!=0){
               condition.await();
           }
           //操作
           number++;
           System.out.println(Thread.currentThread().getName()+"\t"+number);
           //通知
           condition.signalAll();
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           lock.unlock();
       }
    }
    public void  Decrem() throws InterruptedException {
        lock.lock();
        try {

            while (number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        resouce resouce = new resouce();
        for (int i = 1; i <= 5; i++){
            new Thread(() -> {

                try {
                    resouce.Increm();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "AA").start();
    }
    for (int i = 1; i <= 5; i++){
        new Thread(() -> {
            try {
                resouce.Decrem();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }}
}
