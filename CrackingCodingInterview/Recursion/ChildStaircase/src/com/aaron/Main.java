package com.aaron;
// Things to think through
//  different way to think about recursion: bottom up vs. top down
    //recursion is top down, while iteration can be top down vs. bottom up
// different way to think about where the data will be created: bottom up vs.top down + different ways to pass parameters through input parameter vs. return value
    // option 1: create result data structure from the top and pass down through input arguments, and being modified --> this is not fit for dynamic programing, as i cannot remember (n, result)
    // option 2: create result data structure from the bottom and pass back through return value ---> this is fit for dynamic programing, as i can remember the n->result mapping
//  when to create a new copy and why
// Time vs. space cost - get the result in the end vs. product the result as we go.
// dymamic programing is really trading space with time
    // this is what mem cache is about - for a function, remember the input parameter and result result through some extra data structure.


import java.util.ArrayList;

//solution: recursion & dynamic programming
class Solver{
    private int N = 0;
    ArrayList<ArrayList<Integer>>[] mem = null;

    public Solver(int n) {
        this.N = n;
        this.mem = new ArrayList[n];
    }

    private ArrayList<ArrayList<Integer>> add(int n, ArrayList<ArrayList<Integer>> solutions) {
        if(solutions == null)
            return null;

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(ArrayList<Integer> list: solutions) {
            ArrayList<Integer> newList = (ArrayList<Integer>) list.clone();
            newList.add(n);
            result.add(newList);
        }

        return result;
    }

    // every add is a deep copy and modification. this ensures that result can be remembered and not changed as we build up the solution
    private ArrayList<ArrayList<Integer>> getStairCaseSolutions(int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if(n == 3 || n == 2 || n == 1) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(n);
            result.add(list);
            return result;
        }

        if(mem[n-1] == null) {
            ArrayList<ArrayList<Integer>> result1 = add(1, getStairCaseSolutions(n-1));
            ArrayList<ArrayList<Integer>> result2 = add(2, getStairCaseSolutions(n-2));
            ArrayList<ArrayList<Integer>> result3 = add(3, getStairCaseSolutions(n-3));

            result.addAll(result1);
            result.addAll(result2);
            result.addAll(result3);

            mem[n-1] = result;
        }
        return mem[n-1];
    }

    public ArrayList<ArrayList<Integer>> getStairCaseSolutions() {
        if(N <= 0)
            return null;
        return getStairCaseSolutions(N);
    }

}


public class Main {

    // solution: recursion
    private static void add(int n, ArrayList<ArrayList<Integer>> solutions) {
        if(solutions == null)
            return;
        for(ArrayList<Integer> list: solutions) {
            list.add(n);
        }
    }

    //only creates solution instances for every node at the bottom. the instance will be modfiied as it comes to the top,
    // but should be fine since we only need the value to be correct once.
    public static ArrayList<ArrayList<Integer>> getStairCaseSolutions(int n) {

        if(n == 3 || n == 2 || n == 1) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(n);
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();
            result.add(list);
            return result;
        }

        ArrayList<ArrayList<Integer>> result1 = getStairCaseSolutions(n-1);
        add(1, result1);

        ArrayList<ArrayList<Integer>> result2 = getStairCaseSolutions(n-2);
        add(2, result2);

        ArrayList<ArrayList<Integer>> result3 = getStairCaseSolutions(n-3);
        add(3, result3);

        result1.addAll(result2);
        result1.addAll(result3);
        return result1;
    }

    // basic solution: recursion and print
    private static void _printStairCase(int n, ArrayList<Integer> history) {
        if(history == null)
            return;
        if(n == 3 || n == 2 || n == 1) {
            history.add(n);
            System.out.println(history);
            return;
        }

        ArrayList<Integer> history2 = (ArrayList<Integer>) history.clone();
        ArrayList<Integer> history3 = (ArrayList<Integer>) history.clone();

        history.add(1);
        history2.add(2);
        history3.add(3);

        _printStairCase(n-1, history);
        _printStairCase(n-2, history2);
        _printStairCase(n-3, history3);

    }

    public static void printStairCase(int n) {
        ArrayList<Integer> history = new ArrayList<>();
        _printStairCase(n, history);
    }

    public static void main(String[] args) {

//        printStairCase(30);

        int number = 30;

        long time1 = System.currentTimeMillis();
        ArrayList<ArrayList<Integer>> result = getStairCaseSolutions(number);
        time1 = System.currentTimeMillis() - time1;
        System.out.println(time1);


        long time2 = System.currentTimeMillis();
        Solver solver = new Solver(number);
        ArrayList<ArrayList<Integer>> result2 = solver.getStairCaseSolutions();
        time2 = System.currentTimeMillis() - time2;
        System.out.println(time2);



//        for(ArrayList<Integer> list: result) {
//            System.out.println(list);
//        }
    }
}
