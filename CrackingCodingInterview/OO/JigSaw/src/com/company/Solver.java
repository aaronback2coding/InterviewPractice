package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class Solver {
    private ArrayList<Piece> unsolved = null;
    private Puzzle puzzle = null;

    private ConnectedPiece origin = null;
    private ArrayList<ConnectedPiece> connectedPieces;
    private Stack<ConnectedPiece> frontTier;

    public Solver(Puzzle puzzle) {
        this.puzzle = puzzle;
        connectedPieces = new ArrayList<>();
        frontTier = new Stack<>();
    }

    public boolean solve() {
        unsolved = puzzle.getPieces();
        if(unsolved.size() <= 1)
            throw new IllegalArgumentException("");
        Piece first = unsolved.remove(0);
        origin =new ConnectedPiece(first, 0, 0);
        connectedPieces.add(origin);
        frontTier.add(origin);

        int left = 0;
        int right = 0;
        int up = 0;
        int down = 0;

        while(!frontTier.empty()) {
            ConnectedPiece cur = frontTier.pop();
            ArrayList<Piece> newUnsolved = new ArrayList<>();
            for(Piece p: unsolved) {
                Direction dir = puzzle.connected(cur.piece, p);
                if(dir != null) {
                    //p is connected. so calculate the coordinates, put it in the connected pieces, put in front tier
                    ConnectedPiece cp = cur.createConnectedPiece(cur, p, dir);
                    connectedPieces.add(cp);
                    frontTier.add(cp);
                    right = cp.x > right ? cp.x : right;
                    left = cp.x < left ? cp.x : left;
                    up = cp.y < up ? cp.y : up;
                    down = cp.y > down ? cp.y : down;
                } else {
                    newUnsolved.add(p);
                }
            }
            unsolved = newUnsolved;
        }
        for(ConnectedPiece cp: connectedPieces) {
            puzzle.setPiece(cp.piece, cp.x - left, cp.y - up);
        }
        return true;
    }
}
