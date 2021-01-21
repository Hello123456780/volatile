package com.JUC.JVM;


import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/*

弱引用
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1=new Object();
        WeakReference<Object> softReference=new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }
}
