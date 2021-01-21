package com.JUC.JUC1;


/*
阻塞队列  ArrayBlockingQueue
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue=new ArrayBlockingQueue(3);
          blockingQueue.offer("a",2, TimeUnit.SECONDS);
          blockingQueue.offer("b",2, TimeUnit.SECONDS);
          blockingQueue.offer("c",2, TimeUnit.SECONDS);
        //  blockingQueue.offer("d",2, TimeUnit.SECONDS);//到一定时间自己推出阻塞
        blockingQueue.poll(2,TimeUnit.SECONDS);
        blockingQueue.poll(2,TimeUnit.SECONDS);
        blockingQueue.poll(2,TimeUnit.SECONDS);
      //  blockingQueue.poll(2,TimeUnit.SECONDS);///到一定时间自己退出阻塞

    }
    //阻塞
    public  void Excepton3() throws InterruptedException {

        BlockingQueue blockingQueue=new ArrayBlockingQueue(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        //   blockingQueue.put("c");//一直阻塞
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // System.out.println(blockingQueue.take());//一直阻塞
    }
    //特殊值
public  void Excepton2(){
    BlockingQueue blockingQueue=new ArrayBlockingQueue(3);
    System.out.println(blockingQueue.offer("a"));
    System.out.println(blockingQueue.offer("b"));
    System.out.println(blockingQueue.offer("c"));
    //  System.out.println(blockingQueue.offer("d"));  //false
    System.out.println(blockingQueue.peek());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    System.out.println(blockingQueue.poll());
    //  System.out.println(blockingQueue.poll());//null
}
    //抛出异常
  public  void Exception1(){
      BlockingQueue blockingQueue=new ArrayBlockingQueue(3);
      System.out.println(blockingQueue.add("a"));
      System.out.println(blockingQueue.add("b"));
      System.out.println(blockingQueue.add("c"));

      System.out.println(blockingQueue.element());//
      // System.out.println(blockingQueue.add("x"));//java.lang.IllegalStateException: Queue full

      System.out.println(blockingQueue.remove("a"));
      System.out.println(blockingQueue.remove("b"));
      System.out.println(blockingQueue.remove("c"));
      //System.out.println(blockingQueue.remove("x"));//false

  }
}
