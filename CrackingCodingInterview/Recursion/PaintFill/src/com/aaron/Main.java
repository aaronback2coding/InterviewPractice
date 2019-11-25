package com.aaron;

public class Main {

    public enum Color {
        Black, White, Red, Yellow, Green
    }

    public static String PrintColor(Color c) {
        switch(c) {
            case Black:
                return "B";
            case White:
                return "W";
            case Red:
                return "R";
            case Yellow:
                return "Y";
            case Green:
                return "G";
        }
        return "X";
    }

    public static void PrintScreen(Color[][] screen) {
        for (int r = 0; r < screen.length; r++) {
            for (int c = 0; c < screen[0].length; c++) {
                System.out.print(PrintColor(screen[r][c]));
            }
            System.out.println();
        }
    }

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }


    public static boolean PaintFill(Color[][] screen, int r, int c, Color newColor) {
        Color oldColor = screen[r][c];
        int N = screen[0].length;

        screen[r][c] = newColor;
        if(r+1 < N && screen[r + 1][c].equals(oldColor))
            PaintFill(screen, r + 1, c, newColor);

        if(c+1 < N && screen[r][c + 1].equals(oldColor))
            PaintFill(screen, r, c + 1, newColor);

        if(r-1 >= 0 && screen[r - 1][c].equals(oldColor))
            PaintFill(screen, r - 1, c, newColor);

        if(c-1 >= 0  && screen[r][c - 1].equals(oldColor))
            PaintFill(screen, r, c - 1, newColor);

        return false;
    }

    public static void main(String[] args) {
        int N = 10;
        Color[][] screen = new Color[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                screen[i][j] = Color.Black;
            }
        }
        for (int i = 0; i < 100; i++) {
            screen[randomInt(N)][randomInt(N)] = Color.Green;
        }
        PrintScreen(screen);
        PaintFill(screen, 2, 2, Color.White);
        System.out.println();
        PrintScreen(screen);
    }
}
