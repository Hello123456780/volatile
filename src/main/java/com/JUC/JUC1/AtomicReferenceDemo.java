package com.JUC.JUC1;


import lombok.*;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

import static java.lang.Thread.sleep;

@Data
@ToString
@AllArgsConstructor
class  user{
    String  username;
    Integer age;

}
public class AtomicReferenceDemo {

    //引用原子性     AtomicReference
//    public static void main(String[] args) throws InterruptedException {
////         AtomicReference<user>  atomicReference=new AtomicReference<user>();
////         user z3=new user("z3",24);
////         user l4=new user("l4",25);
////
////         atomicReference.set(z3);
////
////       System.out.println(atomicReference.compareAndSet(z3, l4)+"\t"+Thread.currentThread().getName()+atomicReference.get());
////        System.out.println(atomicReference.compareAndSet(z3, l4)+"\t"+Thread.currentThread().getName()+atomicReference.get());
////    }
    public static void main(String[] args) throws InterruptedException {
        final AtomicReference<Integer>  atomicReference=new AtomicReference<Integer>(10);
        final AtomicStampedReference<Integer>  atomicStampedReference=new AtomicStampedReference<Integer>(100,1);

        //出现ABA问题
           new Thread(new Runnable() {
               @SneakyThrows
               public void run() {

             atomicReference.compareAndSet(10,100);
             atomicReference.compareAndSet(100,10);

               }
           },"t1").start();
        new Thread(new Runnable() {
            @SneakyThrows
            public void run() {
                sleep(1000);
                System.out.println(atomicReference.compareAndSet(10,2021)+"\t"+Thread.currentThread().getName()+"\t"+atomicReference.get());

            }
        },"t2").start();

     //解决ABA的问题 atomicStampedReference
        new Thread(new Runnable() {
            @SneakyThrows
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                System.out.println("第一次版本号\t"+stamp);
                sleep(1000);
                atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println("第2次版本号\t"+atomicStampedReference.getStamp());
                System.out.println(atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
                System.out.println("第3次版本号\t"+atomicStampedReference.getStamp());

            }
        },"t3").start();
        new Thread(new Runnable() {
            @SneakyThrows
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                System.out.println("第一次版本号\t"+stamp);
                sleep(3000);
                System.out.println("是否成功\t"+atomicStampedReference.compareAndSet(100, 2021, stamp, stamp + 1));
                System.out.println(Thread.currentThread().getName()+"\t"+atomicStampedReference.getStamp()+"\t"+atomicStampedReference.getReference());
            }
        },"t4").start();

    }

}
