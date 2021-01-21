package com.JUC.JVM;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
java.lang.OutOfMemoryError: GC overhead limit exceeded   大部分时间在用在GC并且回收效果差
 */
public class GCOverheadDemo {

    public static void main(String[] args) {
        int i=0;
        List<String>  list=new ArrayList<>();
        try {
            while (true){
                list.add(String.valueOf(++i).intern()+new Random().nextInt());
            }
        }catch (Throwable e){
            System.out.println(
                    "***********"+i
            );
            e.printStackTrace();
            throw  e;
        }

    }
}
