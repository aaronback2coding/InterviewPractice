package com.company;


public class Main {


    public static void main(String[] args) {
//        Graph g = new Graph(10);
//        g.addEdges(1, 2, 1);
//        g.addEdges(2, 3, 1);
//        g.addEdges(1, 3, 100);
//        System.out.println(g.djk(1, 3));


//        Graph g = new Graph(10);
//        g.addEdges(1, 2, 1);
//        g.addEdges(2, 3, 1);
//        g.addEdges(3, 4, 1);
//        g.addEdges(4, 5, 1);
//        g.addEdges(5, 6, 1);
//        g.addEdges(1, 7, 1);
//        g.addEdges(7, 6, 3);
//
//        System.out.println(g.djk(1, 9));
        int[] a = {10, 5, 8, 7, 2, 55, 6, 9};
        Heap hp = new Heap(a, 10);
        hp.add(0);
        for(int i = 0; i < 9; i++) {
            System.out.println(hp.remove());
        }
    }
}
