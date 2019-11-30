package com.aaron;

// things to write
//  1. no strategy - validate whether the race will happen or not
//  2. strategy implemenation

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Main {

    enum Side {
        LEFT,
        RIGHT
    }

    static class Philosopher extends Thread{
        int ID = 0;
        int dinnerSize = 0;
        int bites = 10;
        ReentrantLock[] chopsticks = null;

        private void mySleep(int ms) {
            try {
                sleep(ms);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public Philosopher(int ID, int dinnerSize, ReentrantLock[] chopsticks) {
            this.ID = ID;
            this.dinnerSize = dinnerSize;
            this.chopsticks = chopsticks;
        }

        //left is same ID, right is same ID+1
        void pick(Side side) {
            if(side == Side.LEFT)
                chopsticks[ID].lock();
            else
                chopsticks[(ID + 1) % dinnerSize].lock();
        }
        void drop(Side side) {
            if(side == Side.LEFT)
                chopsticks[ID].unlock();
            else
                chopsticks[(ID + 1) % dinnerSize].unlock();
        }
        void eat() {
            mySleep(500);
            System.out.println("Philosopher " + this.ID + " bite " + bites);
            bites--;
        }

        @Override
        public void run() {
            while (bites >0) {
                if(ID == 0) {
                    pick(Side.RIGHT);
                    mySleep(100);
                    pick(Side.LEFT);
                } else {
                    pick(Side.LEFT);
                    mySleep(100);
                    pick(Side.RIGHT);
                }
                eat();
                drop(Side.LEFT);
                drop(Side.RIGHT);
            }
        }
    }


    public static void main(String[] args) {
        int dinnerSize = 5;
        ReentrantLock[] chopsticks = new ReentrantLock[5];
        Philosopher[] philosophers = new Philosopher[5];
        for(int i = 0; i < dinnerSize; i++) {
            chopsticks[i] = new ReentrantLock();
            philosophers[i] = new Philosopher(i, dinnerSize, chopsticks);
        }

        for(int i = 0; i < dinnerSize; i++) {
            philosophers[i].start();
        }
    }
}
