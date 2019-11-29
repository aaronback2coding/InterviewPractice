package com.aaron;

public class Main {

    static String str1 = "res 1";

    static String str2 = "res 2";

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (str1) {
                System.out.println("locking " + str1 + "for 500 ms");
                try {
                    sleep(500);
                } catch (Exception e) {
                    System.out.println(e);
                }

                synchronized (str2) {
                    System.out.println("locking " + str2 + "for 500 ms");
                    try {
                        sleep(500);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (str2) {
                System.out.println("locking " + str2 + "for 500 ms");
                try {
                    sleep(500);
                } catch (Exception e) {
                    System.out.println(e);
                }

                synchronized (str1) {
                    System.out.println("locking " + str1 + "for 500 ms");
                    try {
                        sleep(500);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
	// write your code here

        Thread1 t1 = new Thread1();
        t1.start();
        Thread2 t2 = new Thread2();
        t2.start();
    }
}
