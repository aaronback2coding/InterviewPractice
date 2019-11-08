package com.aaron;

//-----------------------------------------------------------------------------------------------------------------------
//  print project dependency
//      project a-> f
//      dependency ab, fb, bd, fa, dc,
//      output: feabdc
//      things to think through tomorrow:
//          implement pre-order traversal (depth first search) to detect loop - see whether i need to rescan the stack or
//                can just mark and unmark the visited
//          then understand the most efficient way to detect loop in a graph: Tarjan's strongly connected components algorithm
//          then think about difference between directional vs. undirectional graph
//          then solve this actual problem, using
//              a, brute force - DFS to detect whether there is any loop, and then BFS to print out content
//              b, try to implement the algorithm described in the book - identify and remove root node and their edges in iteration
//-----------------------------------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    String val;
    ArrayList<Node> children;
    boolean visited = false;

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

    public boolean printDependencyRoute() {

        Queue<Node> queue = new LinkedList<Node>();
        for(Node treeroot: trees) {
            treeroot.visited = true;
            queue.add(treeroot);
        }
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.val);
            for(Node child: cur.children) {

                if(!child.visited) {
                    queue.add(child);
                    child.visited = true;
                }
                else {
                    for(Node grandchild: child.children) {
                        if(grandchild.visited)
                            return false;
                    }
                }
            }
        }
        return true;
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
        Node g = graph.addNode("g");
        Node h = graph.addNode("h");
        Node i = graph.addNode("i");
        Node j = graph.addNode("j");
        Node k = graph.addNode("k");
        Node l = graph.addNode("l");

        graph.addEdge(a, b);
        graph.addEdge(b, c);
        graph.addEdge(a, d);
        graph.addEdge(d, e);
        graph.addEdge(e, f);
        graph.addEdge(f, g);
        graph.addEdge(g, h);
        graph.addEdge(h, i);
        graph.addEdge(i, b);




//        graph.addEdge(a, d);
//        graph.addEdge(f, b);
//        graph.addEdge(b, d);
//        graph.addEdge(f, a);
//        graph.addEdge(d, c);

//        graph.addEdge(f, c);
//        graph.addEdge(f, b);
//        graph.addEdge(f, a);
//        graph.addEdge(c, a);
//        graph.addEdge(b, a);
//        graph.addEdge(b, e);
//        graph.addEdge(a, e);
//        graph.addEdge(d, g);



//        Node r = graph.addNode("r");
//        Node a = graph.addNode("a");
//        Node b = graph.addNode("b");
//        Node c = graph.addNode("c");
//        graph.addEdge(r, a);
//        graph.addEdge(a, b);
//        graph.addEdge(b, c);
//        graph.addEdge(c, a);

        System.out.println(graph.printDependencyRoute());
    }
}
