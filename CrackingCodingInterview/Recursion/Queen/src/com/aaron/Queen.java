package com.aaron;

import java.util.ArrayList;

public class Queen {
    private int n = 0;

    public Queen(int n) {
        this.n = n;
    }

    private boolean solve(int row, ArrayList<Integer> allocated, boolean[] occupied, ArrayList<ArrayList<Integer>> results) {

        for(int i = 0; i < n; i++) {
            if(!occupied[i]) {

                ArrayList<Integer> nextAllocated = (ArrayList<Integer>) allocated.clone();
                nextAllocated.add(i);

                if(row == n - 1) {
                    results.add(nextAllocated);
                    return true;
                }

                boolean[] nextOccupied = new boolean[n];
                for(Integer j: nextAllocated) {
                    nextOccupied[j] = true;
                }
                if(i + 1 < n)
                    nextOccupied[i + 1] = true;
                if(i - 1 >= 0)
                    nextOccupied[i - 1] = true;

                solve(row + 1, nextAllocated, nextOccupied, results);
            }
        }
        return false;
    }

    public void print(ArrayList<Integer> queens) {
        char[][] grid = new char[n][n];
        int row = 0;
        for(Integer i:queens) {
            grid[row][i] = '*';
            row++;
        }
        for(int j = 0; j < n; j++) {
            System.out.println(grid[j]);
        }
    }

    public boolean solve() {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> allocated = new ArrayList<>();
        boolean[] ocupied  = new boolean[n];
        solve(0, allocated, ocupied, results);
        print(results.get(50));
//        for(ArrayList<Integer>result: results) {
//            System.out.println(result);
//        }
        return true;
    }

}
