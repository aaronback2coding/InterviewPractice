package com.aaron;

//-----------------------------------------------------------------------------------------------------------------------
//  print project dependency
//      project a-> f
//      dependency ab, fb, bd, fa, dc,
//      output: feabdc
//      things to think through tomorrow:
//          implement post-order traversal (depth first search) to detect loop - see whether i need to rescan the stack or
//                can just mark and unmark the visited
//          then understand the most efficient way to detect loop in a graph: Tarjan's strongly connected components algorithm
//          then think about difference between directional vs. undirectional graph
//          then solve this actual problem, using
//              a, brute force - DFS to detect whether there is any loop, and then BFS to print out content
//              b, try to implement the algorithm described in the book - identify and remove root node and their edges in iteration
//-----------------------------------------------------------------------------------------------------------------------

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
    private ArrayList<Node> nodes = new ArrayList<>();
    public Node addNode (String str) {
        Node node = new Node(str);
        trees.add(node);
        nodes.add(node);
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

    private void resetVisited() {
        for(Node node: nodes)
            node.visited = false;

    }

// post order based loop detection

    public boolean includeLoop () {
        Stack<Node> stack = new Stack<>();
        for(Node treeroot: trees) {
            stack.push(treeroot);
        }
        while (!stack.empty()) {
            Node cur = stack.pop();
            //if not visit, scan and visit
            if (!cur.visited) {
                stack.push(cur);
                for(Node child: cur.children) {
                    if (child.visited) {
                        resetVisited();
                        return true;
                    }
                    stack.push(child);
                }
                cur.visited = true;
            } else {
                cur.visited = false;
            }
        }
        resetVisited();
        return false;
    }

    public boolean printDependencyRoute() {

        if(includeLoop())
            return false;

        Queue<Node> queue = new LinkedList<Node>();

        for(Node treeroot: trees) {
            queue.add(treeroot);
        }
        while(!queue.isEmpty()) {
            Node cur = queue.remove();
            if(cur.visited)
                continue;
            cur.visited = true;
            System.out.println(cur.val);
            for(Node child: cur.children) {
                if(!child.visited) {
                    queue.add(child);
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


//test case for 1 route super long
//        Node a = graph.addNode("a");
//        Node b = graph.addNode("b");
//        Node c = graph.addNode("c");
//        Node d = graph.addNode("d");
//        Node e = graph.addNode("e");
//        Node f = graph.addNode("f");
//        Node g = graph.addNode("g");
//        Node h = graph.addNode("h");
//        Node i = graph.addNode("i");
//        Node j = graph.addNode("j");
//        Node k = graph.addNode("k");
//        Node l = graph.addNode("l");
//        graph.addEdge(a, b);
//        graph.addEdge(b, c);
//        graph.addEdge(a, d);
//        graph.addEdge(d, e);
//        graph.addEdge(e, f);
//        graph.addEdge(f, g);
//        graph.addEdge(g, h);
//        graph.addEdge(h, i);
//        graph.addEdge(i, b);


//question test case 1
//        Node a = graph.addNode("a");
//        Node b = graph.addNode("b");
//        Node c = graph.addNode("c");
//        Node d = graph.addNode("d");
//        Node e = graph.addNode("e");
//        Node f = graph.addNode("f");
//        graph.addEdge(a, d);
//        graph.addEdge(f, b);
//        graph.addEdge(b, d);
//        graph.addEdge(f, a);
//        graph.addEdge(d, c);

//question test case 2
//        Node a = graph.addNode("a");
//        Node b = graph.addNode("b");
//        Node c = graph.addNode("c");
//        Node d = graph.addNode("d");
//        Node e = graph.addNode("e");
//        Node f = graph.addNode("f");
//        Node g = graph.addNode("g");
//        graph.addEdge(f, c);
//        graph.addEdge(f, b);
//        graph.addEdge(f, a);
//        graph.addEdge(c, a);
//        graph.addEdge(b, a);
//        graph.addEdge(b, e);
//        graph.addEdge(a, e);
//        graph.addEdge(d, g);



// loop test case
//        Node r = graph.addNode("r");
//        Node a = graph.addNode("a");
//        Node b = graph.addNode("b");
//        Node c = graph.addNode("c");
//        graph.addEdge(r, a);
//        graph.addEdge(a, b);
//        graph.addEdge(b, c);
//        graph.addEdge(c, a);

        // parallel test case
        Node r = graph.addNode("r");
        Node a = graph.addNode("a");
        Node b = graph.addNode("b");
        Node c = graph.addNode("c");
        graph.addEdge(r, a);
        graph.addEdge(a, c);
        graph.addEdge(r, b);
        graph.addEdge(b, c);


        System.out.println(graph.includeLoop());


//        System.out.println(graph.printDependencyRoute());

    }
}
