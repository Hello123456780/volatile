package com.JUC.JUC1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/*
  阻塞队列的生产消费者   用  ture fasle 来控制 停止生产和消费
 */
class  MyRescouce{

    //为了保存可见性  加入volatile
    private  volatile boolean  FLAG=true;
    private BlockingQueue<String> blockingQueue=null;
    //为了保证原子性,操作资源的数据
    private AtomicInteger atomicInteger=new AtomicInteger();

    public MyRescouce(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    //生产者
    public  void prod() throws InterruptedException {

        //为true我就生产
        String data=null;
        Boolean result;
        while (FLAG){
            data=atomicInteger+"";
            result=blockingQueue.offer(data,2, TimeUnit.SECONDS);
            if (result){
                System.out.println(Thread.currentThread().getName()+"生产蛋糕成功"+result);
            }else {
                System.out.println(Thread.currentThread().getName()+"生产蛋糕失败");
            }
            sleep(1000);
        }
        System.out.println(Thread.currentThread().getName()+"停止生产蛋糕");
    }
    //消费者
    public  void Consumer() throws InterruptedException {

        String  result=null;
        while (FLAG){
            result=blockingQueue.poll(2,TimeUnit.SECONDS);
            if (result==null||result.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"超时了2秒钟没有蛋糕可以消费了"+result);
                 return;
            }
                System.out.println(Thread.currentThread().getName()+"消费蛋糕成功"+result);

        }
    }
    //停止
    public  void  stop(){
         this.FLAG=false;
    }

}


public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
     MyRescouce rescouce=new MyRescouce(new ArrayBlockingQueue<>(3));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"我开始生产蛋糕了");
            try {
                rescouce.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"我开始消费蛋糕了");
            try {
                rescouce.Consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();


        TimeUnit.SECONDS.sleep(5);

        System.out.println(Thread.currentThread().getName()+"5秒之后停止生产");
        rescouce.stop();
    }
}
