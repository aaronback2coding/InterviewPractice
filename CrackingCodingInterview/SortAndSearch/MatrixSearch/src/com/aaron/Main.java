// Learning
//  have confidence on your thinking capability. be patient, think clearly about the ambiguity you are dealing with, and you
//  can and will turn them into clarity / assumptions
//   you have your code right when you can think clearly what you are doing with your code.
//      your intuition is important. 

package com.aaron;

import java.util.ArrayList;

public class Main {

    static class Coordinate {
        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString() {
            String str = new String("");
            str += "(";
            str += row;
            str += ", ";
            str += col;
            str += ")";
            return str;
        }
    }

    private static int getVal(Coordinate c, int[][] matrix) {
        return matrix[c.row][c.col];
    }

    private static void search(int val, int[][] matrix, ArrayList<Coordinate> results, Coordinate start, Coordinate end) {

        if(val > getVal(end, matrix))
            return;
        if(val < getVal(start, matrix))
            return;

        int midR = (start.row + end.row) / 2;
        int midC = (start.col + end.col) / 2;
        Coordinate mid = new Coordinate(midR, midC);
        int midVal = getVal(mid, matrix);

        if(val == midVal) {
            results.add(mid);
            return;
        }

        if(val < midVal) {
            search(val, matrix, results, start, mid);
        }

        Coordinate rightTopStart = new Coordinate(start.row, mid.col + 1);
        Coordinate rightTopEnd = new Coordinate(mid.row, end.col);
        if(val >= getVal(rightTopStart, matrix) && val <=getVal(rightTopEnd, matrix))
            search(val, matrix, results, rightTopStart, rightTopEnd);


        Coordinate leftBottomStart = new Coordinate(mid.row + 1, start.col);
        Coordinate leftBottomEnd = new Coordinate(end.row, mid.col);
        if(val >= getVal(leftBottomStart, matrix) && val <=getVal(leftBottomEnd, matrix))
            search(val, matrix, results, leftBottomStart, leftBottomEnd);

        Coordinate rightBottomStart = new Coordinate(mid.row + 1, mid.col + 1);
        if(val >= getVal(rightBottomStart, matrix))
            search(val, matrix, results, rightBottomStart, end);
    }

    public static ArrayList<Coordinate> search(int val, int[][] matrix) {
        if(matrix == null)
            return null;

        int rowLen = matrix.length;
        if(rowLen == 0)
            return null;

        int colLen = matrix[0].length;
        if(colLen == 0)
            return null;


        ArrayList<Coordinate> results = new ArrayList<>();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(rowLen - 1, colLen - 1);

        search(val, matrix, results, start, end);

        return results;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {15, 20, 40, 85},
                {20, 35, 80, 95},
                {30, 55, 95, 105},
                {40, 80, 100, 120}
        };

//        ArrayList<Coordinate> results = search(130, matrix);
//        for(Coordinate result: results) {
//            System.out.println(result.toString());
//        }
//
        ArrayList<Coordinate> results2 = search(31, matrix);
        for(Coordinate result: results2) {
            System.out.println(result.toString());
        }

//        for(int i = 0; i < 4; i++) {
//            for(int j = 0; j < 4; j++) {
//                ArrayList<Coordinate> results3 = search(matrix[i][j], matrix);
//                StringBuilder sb = new StringBuilder();
//                sb.append(matrix[i][j]);
//                sb.append(" is at ");
//                for(Coordinate result: results3) {
//                    sb.append(result);
//                }
//                System.out.println(sb);
//            }
//        }
    }
}
