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
//
//

//      learning: You do not have to apply the algorithm you have learned. you are free to use the one that fit to the specific problem
//          in this case, while you can solve it by treating as a tree / forest, it is better to treat it as a graph, and also, instead of
//          doing traversal, grasp and build on the intuition that you only need to know partial information to solve the problem
//          you do not need to complete hte full traveral
//          in this case, you can do that through just count & decount the dependencies
//-----------------------------------------------------------------------------------------------------------------------

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    String val;
    int index;

    public Node(String val, int index) {
        this.val = val;
        this.index = index;
    }
}

class DependencyGraph {
    private LinkedList<Node> nodes = new LinkedList<>();
    private LinkedList<LinkedList<Node>> adjNodesList = new LinkedList<>();

    public Node addNode (String str) {
        Node node = new Node(str, nodes.size());
        LinkedList<Node> adjNodes = new LinkedList<>();
        nodes.add(node);
        adjNodesList.add(adjNodes);
        return node;
    }

    public void addEdge (Node src, Node dest) {
        if(src == null)
            return;
        if(dest == null)
            return;

        LinkedList<Node> adjNodes = adjNodesList.get(src.index);
        adjNodes.add(dest);

    }

    public boolean printDependencyRoute() {
        //initialize
        int length = nodes.size();
        int[] dependencyCounts = new int[length];
        int printedNodes = 0;
        for(LinkedList<Node> adjNodes: adjNodesList) {
            for(Node node: adjNodes) {
                dependencyCounts[node.index] ++;
            }
        }
        System.out.println(dependencyCounts);

        while(true) {

            ArrayList<Integer> rootNodes = new ArrayList<>();

            //find root nodes
            for(int i = 0; i < length; i++) {
                if(dependencyCounts[i] == 0) {
                    System.out.println(nodes.get(i).val);
                    rootNodes.add(i);
                    dependencyCounts[i] = -1;
                    printedNodes ++;
                }
            }
            if(rootNodes.size() == 0) {
                break;
            }

            for(Integer j: rootNodes) {
                for(Node node: adjNodesList.get(j)) {
                    dependencyCounts[node.index] --;
                }
            }
        }

        if(printedNodes == length) {
            return true;
        }
        return false;
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


//// question test case 1
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


        System.out.println(graph.printDependencyRoute());

    }
}
