package com.JUC.JVM;

import java.util.Random;


/*
       新生代配置了垃圾回收器，老年代会自动配置相应的垃圾回收器
      Serial  -   Serial old    -Xms10m -Xmx10m  -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseSerialGC
      ParNew      Serial old    -Xms10m -Xmx10m  -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParNewGC
      Parallel    Parallel old   -Xms10m -Xmx10m  -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseParallelGC
      ParNew      CMS+Serial old   -Xms10m -Xmx10m  -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseConcMarkSweepGC
      G1      -Xms10m -Xmx10m  -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseG1GC

 */
public class GCDemo {

    public static void main(String[] args) {

        System.out.println("**********Heloo GC");
        try {
            String str = null;
            while (true){
                 str+=str+new Random().nextInt(2222222)+new Random().nextInt(11111111);
                 str.intern();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
