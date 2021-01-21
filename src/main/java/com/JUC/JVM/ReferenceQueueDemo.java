package com.JUC.JVM;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/*

弱引用与引用队列
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) {

        Object o1=new Object();
        ReferenceQueue referenceQueue=new ReferenceQueue<>();
        WeakReference<Object>  weakReference=new WeakReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("==============");

        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());


    }
}
