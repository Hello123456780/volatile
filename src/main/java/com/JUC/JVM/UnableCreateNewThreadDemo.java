package com.JUC.JVM;


import static java.lang.Thread.sleep;

/*
   一个进程创建的线程超出系统承载极限
  不能在window上做实验

 */
public class UnableCreateNewThreadDemo {

    public static void main(String[] args) {
        for ( int i=0;;i++){
            System.out.println("********"+i);
            new Thread(()->{
                try {
                    sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}
