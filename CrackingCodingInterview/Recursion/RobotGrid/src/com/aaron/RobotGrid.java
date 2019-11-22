package com.aaron;

import java.lang.reflect.Array;
import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RobotGrid {
    private int m = 0;
    private int n = 0;
    private int numberofBlockers = 0;
    private char[][] grid = null;
    private HashMap<Coordinate, ArrayList<Direction>> mem = null;

    class Coordinate {
        int x = 0;
        int y = 0;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public RobotGrid(int m, int n, int numberofBlockers) {
        this.m = m;
        this.n = n;
        this.numberofBlockers = numberofBlockers;
        grid = new char[n][m];
    }

    public void init() {
        // generate random number of blockers
        Random rand = new Random();
        for (int i = 0; i < numberofBlockers; i ++) {
            int x = rand.nextInt(m);
            int y = rand.nextInt(n);
            grid[y][x] = 'X';
        }
        grid[0][0] = 0;
        grid[n-1][m-1] = 0;
    }

    public void print() {

        for(int y = 0; y < n; y++) {
            StringBuilder sb  = new StringBuilder();
            for(int x = 0; x < m; x++) {
//                char val = grid[y][x] == 0 ? ' ' : grid[y][x];
                char val = grid[y][x];
                sb.append(val);
                sb.append(' ');
            }
            System.out.println(sb);
        }
    }

    public void markPath() {
        ArrayList<Direction> path = getPath();
        if(path == null)
            return;
        if(m <= 0 || n <= 0)
            throw new IllegalArgumentException();
        grid[0][0] = '*';
        int x = 0;
        int y = 0;
        for(int i = getPath().size() - 1; i >= 0; i--) {
            Direction dir = path.get(i);
            switch(dir) {
                case DOWN:
                    y++;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
            grid[y][x] = '*';
        }
    }

    public ArrayList<Direction> getPath() {
        mem = new HashMap<Coordinate, ArrayList<Direction>>();
        return _getPath(0, 0);
    }

    private boolean memorize(int x, int y, ArrayList<Direction> result) {
        if(mem == null || result ==  null)
            return false;
        Coordinate c = new Coordinate(x, y);
        ArrayList<Direction> resultCopy = (ArrayList<Direction>)result.clone();
        mem.put(c, resultCopy);
        return true;
    }

    private ArrayList<Direction> _getPath(int x, int y) {

        if(x >= m || y >= n)
            return null;

        if(grid[y][x] == 'X')
            return null;

        if(x == m - 1 && y == n - 1) {
            ArrayList<Direction> result = new ArrayList<>();
            return result;
        }

        Coordinate c = new Coordinate(x, y);
        ArrayList<Direction> memorizedResult = mem.get(c);
        if(memorizedResult != null)
            return memorizedResult;

        ArrayList<Direction> rightResult = _getPath(x + 1, y);
        if(rightResult != null) {
            memorize(x+1, y, rightResult);
            rightResult.add(Direction.RIGHT);
            return rightResult;
        }

        ArrayList<Direction> downResult = _getPath(x, y+1);
        if(downResult != null) {
            memorize(x, y+1, downResult);
            downResult.add(Direction.DOWN);
            return downResult;
        }

        return null;
    }

}
