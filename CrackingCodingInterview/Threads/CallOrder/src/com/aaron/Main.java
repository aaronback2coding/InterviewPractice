package com.aaron;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Main {


    static class BooleanLock {
        public boolean val = false;
    }


    static class Calls {

        ReentrantLock l1 = new ReentrantLock();
        Condition c1 = l1.newCondition();
        ReentrantLock l2 = new ReentrantLock();
        Condition c2 = l2.newCondition();
        boolean firstCalled = false;
        boolean secondCalled = false;

        public void first() {
            System.out.println("First");
        }

        public void second() {
            System.out.println("second");
        }

        public void third() {
            System.out.println("third");
        }
    }

    static class Caller extends Thread {
        Calls calls = null;

        public Caller(String name, Calls calls) {
            super(name);
            this.calls = calls;
        }

        @Override
        public void run() {
            if(this.getName() == "A") {
                calls.l1.lock();
                System.out.println("Thread A executed");
                calls.firstCalled = true;
                calls.c1.signal();
                calls.l1.unlock();
            }
            if(this.getName() == "B") {
                calls.l1.lock();
                while(!calls.firstCalled) {
                    try {
                        calls.c1.await();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                System.out.println("Thread B executed");

                //produce condition for C to start
                calls.l2.lock();
                calls.secondCalled = true;
                calls.c2.signal();
                calls.l2.unlock();

                calls.l1.unlock();
            }
            if(this.getName() == "C") {
                calls.l2.lock();
                while(!calls.secondCalled) {
                    try {
                        calls.c2.await();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                System.out.println("Thread C executed");
            }
        }
    }

    public static void main(String[] args) {
	// write your code here
        Calls calls = new Calls();
        Caller callerA = new Caller("A", calls);
        Caller callerB = new Caller("B", calls);
        Caller callerC = new Caller("C", calls);

        callerC.start();
        try {
            sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }

        callerB.start();

        try {
            sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }

        callerA.start();

    }
}
