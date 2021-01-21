package com.JUC.JUC1;


/*
   死锁:各自拥有各自需要的资源
 */
class  Mylock  implements  Runnable{
     private String lockA;
     private  String  lockB;

    public Mylock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"获取了"+lockA+"尝试去获取lockB");
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"获取了"+lockB+"尝试去获取lockA");
            }

        }
    }
}


public class DeadLockDemo {

    public static void main(String[] args) {
       Mylock mylock=new Mylock("lockA","lockB");
        Mylock mylock1=new Mylock("lockB","lockA");

       new Thread(mylock,"AAA").start();
       new Thread(mylock1,"BBB").start();
    }


}
