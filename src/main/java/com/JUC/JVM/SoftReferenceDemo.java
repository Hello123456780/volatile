package com.JUC.JVM;


/*
软引用
 */

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
      //内存够用
    public static void  Soft_memory_eouth(){
           Object o1=new Object();
        SoftReference<Object> softReference=new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());


    }

   //内存不够用
    public static void  Soft_memory_Notenouth(){
        Object o1=new Object();
        SoftReference<Object > softReference=new SoftReference<Object>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1=null;
        try {
               Byte[] bytes=new Byte[30*1024*1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
     Soft_memory_eouth();
     //  Soft_memory_Notenouth();
    }
}
