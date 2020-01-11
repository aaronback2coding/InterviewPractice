package com.aaron;



class Hello {
    int i = 0;

    private Hello(int i) {
        this.i = i;
    }

    class Subclass extends Hello {
        int j = 0;

        public Subclass(int i, int j) {
            super(i);
            this.j = j;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
            return true;
        if(((Hello)obj).i == this.i)
            return true;
        else
            return false;
    }
}


public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            int a = 0;
            return;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("lalala");
        }
    }
}
