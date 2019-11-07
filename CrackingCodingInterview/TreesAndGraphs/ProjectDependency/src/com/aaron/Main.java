package com.aaron;

//-----------------------------------------------------------------------------------------------------------------------
//  print project dependency
//      project a-> f
//      dependency ab, fb, bd, fa, dc,
//      output: feabdc
//-----------------------------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.LinkedList;

class Node {
    String val;
    ArrayList<Node> children;

    public Node(String val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

class DependencyGraph {
    private LinkedList<Node> trees = new LinkedList<>();

    public Node addNode (String str) {
        Node node = new Node(str);
        trees.add(node);
        return node;
    }

    public void addEdge (Node src, Node dest) {
        if(src == null)
            return;
        if(dest == null)
            return;

        // add edge
        src.children.add(dest);

        // remove tree head if necessary
        if(trees.contains(dest)) {
            trees.remove(dest);
        }
    }

    public void printDependencyRoute() {

    }

}

public class Main {

    public static void main(String[] args) {
	// write your code here
        DependencyGraph graph = new DependencyGraph();

        Node a = graph.addNode("a");
        Node b = graph.addNode("b");
        Node c = graph.addNode("c");
        Node d = graph.addNode("d");
        Node e = graph.addNode("e");
        Node f = graph.addNode("f");

        graph.addEdge(a, b);
        graph.addEdge(f, b);
        graph.addEdge(b, d);
        graph.addEdge(f, a);
        graph.addEdge(d, c);

        graph.printDependencyRoute();
    }
}
