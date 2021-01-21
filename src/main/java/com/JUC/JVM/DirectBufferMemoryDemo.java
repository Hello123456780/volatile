package com.JUC.JVM;


import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

/*
Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) {
        System.out.println("MaxDirectMemory："+(sun.misc.VM.maxDirectMemory()/(double)1024 / 1024)+"MB");
        //创造OS内存
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(6*1024*1024);
    }
}
