package com.aaron;

public class Main {

    public static int multiply(int a, int b) {
        System.out.println(a + " x " + b);

        if(a == 1)
            return b;
        if(a == 0)
            return 0;
        if(b == 1)
            return a;
        if(b == 0)
            return 0;

        int aRest = a >> 1;
        int aLastDigit = a & 1;
        int bRest = b >> 1;
        int bLastDigit = b & 1;
        int result = multiply(aRest, bRest) << 2;
        if (aLastDigit == 1) {
            result += bRest << 1;
        }
        if (bLastDigit == 1) {
            result += aRest << 1;
        }
        if (aLastDigit == 1 && bLastDigit == 1) {
            result += 1;
        }
        return result;

    }

    public static void main(String[] args) {
	// write your code here
        int a = multiply(73, 22);
        System.out.println(a);

    }
}
