package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Puzzle {
    private int size = 0;
    private ArrayList<Piece> pieces;

    public Puzzle(int size) {
        this.size = size;
        pieces = new ArrayList<>();
        int k = 0;
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                k++;
                Piece p = new Piece(k);
                pieces.add(p);
            }
            k += size;
        }
    }

    //return a clone copy of pieces
    public ArrayList<Piece> getPieces() {
        return (ArrayList<Piece>)pieces.clone();
    }

    //set piece
    public void setPiece(Piece p, int x, int y) {
        pieces.set(y * size + x, p);
    }

    private void swap(int index1, int index2) {
        if(index1 >= pieces.size() || index2 >= pieces.size())
            throw new IllegalArgumentException("");
        Piece temp = pieces.get(index1);
        pieces.set(index1, pieces.get(index2));
        pieces.set(index2, temp);
    }

    public void shuffle() {
        Random rand = new Random();
        int arrayLength = size * size;
        for(int i = 0; i < arrayLength; i++) {
            int j = rand.nextInt(arrayLength-1);
            swap(i, j);
        }
    }

    public void print() {
        System.out.println("--------------puzzle--------------");
        for(int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size; j++) {
                sb.append(pieces.get(i * size + j).getContent());
                sb.append(" ");
            }
            System.out.println(sb);
        }
    }

    public Direction connected(Piece first, Piece second) {
        if(first == null || second == null)
            return null;
        if(second.getContent() - first.getContent() == 1)
            return Direction.RIGHT;
        if(second.getContent() - first.getContent() == -1)
            return Direction.LEFT;
        if(second.getContent() - first.getContent() == size * 2)
            return Direction.DOWN;
        if(second.getContent() - first.getContent() == size * -2)
            return Direction.UP;
        return null;
    }

    public boolean solve() {
        Solver solver = new Solver(this);
        return solver.solve();
    }
}
