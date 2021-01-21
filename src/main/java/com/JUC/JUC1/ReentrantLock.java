package com.JUC.JUC1;


import java.util.concurrent.locks.Lock;

class  phone  implements  Runnable{
    public synchronized  void  sendMSM(){
        System.out.println(Thread.currentThread().getName() + "\t" + "sendMSM");
        sendEmail();
    }
    public synchronized  void  sendEmail(){
        System.out.println(Thread.currentThread().getName() + "\t" + "sendEmail");
    }

    @Override
    public void run() {
        set();
    }
     Lock lock=new java.util.concurrent.locks.ReentrantLock();
    private void set() {
        lock.lock();
        lock.lock();
        try {

            System.out.println(Thread.currentThread().getName() + "\t" + "set");
            get();
        }finally {
            lock.unlock();
            lock.unlock();
        }


    }
    private void get() {
        lock.lock();
        try {

            System.out.println(Thread.currentThread().getName() + "\t" + "get");
        }finally {
            lock.unlock();
        }

    }
}

public class ReentrantLock {



    public static void main(String[] args) {
        phone phone=new phone();
         new Thread(new Runnable() {
             @Override
             public void run() {
                 phone.sendMSM();
             }
         }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                phone.sendMSM();
            }
        }).start();

        Thread thread=new Thread(phone);
        Thread thread1=new Thread(phone);
        thread.start();
        thread1.start();
    }

}
