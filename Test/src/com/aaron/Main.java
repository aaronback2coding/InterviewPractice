package com.aaron;

public class Main {




    public static void main(String[] args) {
        Graph graph = new Graph(10);


        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(1, 7);
        graph.addEdge(7, 5);

        graph.print();
        System.out.println(graph.breadthSearch(1, 5));

//        System.out.println(graph.depthSearch(1, 4));


    }
}
