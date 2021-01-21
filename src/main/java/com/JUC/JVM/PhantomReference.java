package com.JUC.JVM;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/*

 虚引用：类似一种通知机制，在引用对象的内存被销毁的时候，可以做一些自己想做的事，或者接收一个系统通知
 */
public class PhantomReference {

    public static void main(String[] args) {
        Object o1=new Object();
        ReferenceQueue referenceQueue=new ReferenceQueue<>();
        java.lang.ref.PhantomReference<Object> PhantomReference=new java.lang.ref.PhantomReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(PhantomReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("==============");

        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(PhantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
