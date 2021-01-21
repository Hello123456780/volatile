package com.JUC.JUC1;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

/*
线程接口 Callable的使用

 */
class  Mycallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        System.out.println(Thread.currentThread().getName()+"进入Callable");
        sleep(3000);
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask的父类是runable，FutureTask的构造方法可以传入Callable实现类，用FutureTask来连接两接口。
        FutureTask<Integer> futureTask=new FutureTask<>(new Mycallable());
        new Thread(futureTask,"AA").start();  //多个线程对应多个futureTask，  多个线程对应一个futureTask相当于一个线程进入此futureTask。其他线程被复用了

        int result=100;
        int result1=futureTask.get();//建议放在最后
        System.out.println("一起多少"+(result+result1));


    }
}
