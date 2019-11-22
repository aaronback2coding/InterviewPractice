package com.aaron;

public class Main {

    public static void main(String[] args) {
	// write your code here
        RobotGrid grid = new RobotGrid(20, 10, 30);
        grid.init();
        grid.print();
        System.out.println("-------------");
        grid.markPath();
        grid.print();
    }
}
