package com.JUC.JUC1;
/*
集合不安全的问题
ArrayList
 */

import sun.awt.windows.ThemeReader;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ContainerNotSafeDemo
{
    public static void main(String[] args) {
       Map<String, String> map =new  ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());//new HashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(map);
                }
            }).start();
        }
     /*
     线程不安全：
  解决：Collections.synchronizedMap(new HashMap<>())
        ConcurrentHashMap<>();


      */
    }

    public static  void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>(); //Collections.synchronizedSet(new HashSet<>());
        new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    set.add(UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(set);
                }
            }).start();
        }
        /*
        HashSet线程不安全  底层是hashMap,hashSet加入的值为 hashmap的一个key，值为objeck的对象。
    CopyOnWriteArraySet<>() 底层实现是 CopyOnWriteArrayList

         */
    }
    public static void listNotSafe(){
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <=30 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(list);
                }
            }).start();

        }
         /*
    1  错误   java.util.ConcurrentModificationException
    2   导致原因
        并发争抢修改导致。（一个人正在写入，另外一个同学过来强夺，导致数据不一致异常，并发修改异常）
    3 解决方案
       1 new Vector<>()
       2 Collections.synchronizedList(new ArrayList<>())
       3  new CopyOnWriteArrayList<>()   读时复制     原理:
    4   优化建议
读写分离，写时加锁，获取数组，获取数组的长度，复制一个新的数组，数组长度 是原来的+1  把数据加入新的数组，把原来的引用断掉，指向新创建的数组的引用。
       旧的数组用来读  写时复制一个数组进行写，写完在进行新的引用。
    public boolean add(E e) {
         final ReentrantLock lock = this.lock;
         lock.lock();
         try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

     */
    }

}
