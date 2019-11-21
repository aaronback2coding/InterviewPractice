package com.company;

public class ConnectedPiece {
    Piece piece;
    int x = 0;
    int y = 0;

    public ConnectedPiece(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }
    public ConnectedPiece createConnectedPiece(ConnectedPiece origin, Piece newPiece, Direction dir) {
        if (origin == null || dir == null || newPiece == null)
            return null;
        int x = origin.x;
        int y = origin.y;
        switch (dir) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        return new ConnectedPiece(newPiece, x, y);
    }
}
