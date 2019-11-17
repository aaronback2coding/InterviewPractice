package aaron.javaramp;

//--------------------------------------------------------------------------------------------------------
// print prime
//--------------------------------------------------------------------------------------------------------


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
        }
        return primes;
    }
}

public class Main {

    public static void main(String[] args) {
        PrimeFinder finder = new PrimeFinder(10000);
        ArrayList<Integer> primes = finder.getPrimes();
        if (primes != null) {
            System.out.println(primes);
        }

    }

}
