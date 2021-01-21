package com.JUC.JVM;


import java.util.HashMap;
import java.util.Map;

/*
  WeakHashMap 的使用
 */
public class WeakHashMap {

    public static void main(String[] args) {
        MyhashMap();
        MyWeakHashMap();
    }

    private static void MyWeakHashMap() {
        java.util.WeakHashMap<Integer,String> map=new java.util.WeakHashMap<>();
        Integer key=new Integer("1");
        String value="MyhashMap";
        map.put(key,value);
        System.out.println(map);
        key=null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }

    private static void MyhashMap() {
        Map<Integer,String> map=new HashMap<>();
        Integer key=new Integer("1");
        String value="MyhashMap";
        map.put(key,value);
        System.out.println(map);
        key=null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }
}
