package com.JUC.JUC1;

public class SingletonDeom {
    private  static  volatile SingletonDeom Instance=null;
    private   SingletonDeom(){
        System.out.println("构造方法");
    }

    //DCL（doouble check lock 双端检锁机制）
    public  static  SingletonDeom getInstance(){
        if (Instance==null){

             synchronized (SingletonDeom.class){
                 if (Instance ==null) {
                     Instance = new SingletonDeom();
                 }
             }

        }
        return  Instance;
    }

    public static void main(String[] args) {
//        System.out.println(SingletonDeom.getInstance()==SingletonDeom.getInstance());
//        System.out.println(SingletonDeom.getInstance()==SingletonDeom.getInstance());
//        System.out.println(SingletonDeom.getInstance()==SingletonDeom.getInstance());

        for (int i = 1; i <=10 ; i++) {

            new Thread(new Runnable() {
                public void run() {
                  SingletonDeom.getInstance();
                }
            },String.valueOf(i)).start();
        }


    }
}
