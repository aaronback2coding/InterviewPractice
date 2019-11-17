package aaron.javaramp;

//--------------------------------------------------------------------------------------------------------
// print prime
//--------------------------------------------------------------------------------------------------------
// learning - practice the basic operation so well that it becomes your natural instinct and reaction.
// leanring - if you will have data structure and multiple functions accessing the same data structure, it might be better
// to just keep then together in a class.
// learning - build up you intuition to spot waste (unnecessary duplicated operation) and design your algorithm efficiently
// the old way - for every number, iterate through all existing primes and validate whether it is a prime
// the waste - first, you do not need to validate every number. you only need to worry about the ones that cannot be mupltiplied
// by the existing primes.



import java.util.ArrayList;

// algorithm 1: use an array list to store primes. go through all the numbers, if they can be divided by the primes till
// square, then it is not a prime

class PrimeFinder {
    int n = 0;
    ArrayList<Integer> primes = null;

    public PrimeFinder(int n) {
        this.n = n;
        primes = new ArrayList<>();
    }

    private boolean isPrime(int number) {

        for (int i = 0; i < primes.size(); i++) {
            int cur = primes.get(i);
            if(cur > Math.sqrt((double) number))
                break;
            if (cur == 1)
                continue;
            if(number % cur == 0)
                return false;
        }
        return true;
    }

    public ArrayList<Integer> getPrimes() {
        if (n <= 0)
            return null;
        primes.add(1);
        if (n  == 1)
            return primes;
        primes.add(2);
        if (n == 2)
            return primes;

        for(int i = 3; i <= n; i++ ) {
            if(isPrime(i))
                primes.add(i);
//            if(i>200000)
//                System.out.println("20000");
//            if(i>300000)
//                System.out.println("300000");
//            if(i>500000)
//                System.out.println("500000");
//            if(i>700000)
//                System.out.println("700000");
//            if(i>900000)
//                System.out.println("900000");
//            if(i>990000)
//                System.out.println("990000");

        }
        return primes;
    }


}

//number -> isPrime[number - 1]
public class Main {

    public static ArrayList<Integer> getPrimes(int number) {
        ArrayList<Integer> results = new ArrayList<>();

        if(number <= 0)
            return null;
        if(number == 1) {
            results.add(1);
            return results;
        }
        if(number == 2) {
            results.add(1);
            results.add(2);
            return results;
        }

        boolean[] isPrime = new boolean[number];
        for( int i = 2; i <= Math.sqrt((double) number); i++) {
            for (int j = i * 2; j <= number; j += i) {
                isPrime[j - 1] = true;
            }
        }

        for(int i = 1; i <= number; i++) {
            if(!isPrime[i - 1])
                results.add(i);
        }
        return results;
    }


    public static void main(String[] args) {
        int number = 1000000;

        System.out.println("------------------------------");
        System.out.println("Method 2");
        long start2 = System.currentTimeMillis();
        ArrayList<Integer> primes2 = getPrimes(number);
        long end2 = System.currentTimeMillis();
        if (primes2 != null) {
            System.out.println(primes2.size());
            System.out.println(primes2.get(0));
//            System.out.println(primes2);
        }
        System.out.println(end2 - start2);
        System.out.println("------------------------------");

        System.out.println("------------------------------");
        System.out.println("Method 1");
        long start1 = System.currentTimeMillis();
        PrimeFinder finder = new PrimeFinder(number);
        ArrayList<Integer> primes = finder.getPrimes();
        long end1 = System.currentTimeMillis();
        if (primes != null) {
            System.out.println(primes.size());
            System.out.println(primes.get(0));
//            System.out.println(primes);
        }
        System.out.println(end1 - start1);
        System.out.println("------------------------------");





    }

}
