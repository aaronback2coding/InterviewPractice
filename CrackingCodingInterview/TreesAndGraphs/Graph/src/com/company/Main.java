package com.company;
//------------------------------------------------------------------------------------------------------------
//give a directed graph, design an algorithm to find out whether there is a route between two nodes
//------------------------------------------------------------------------------------------------------------

import java.util.*;

class GraphNodeNotInGraph extends RuntimeException
{
    public GraphNodeNotInGraph()
    {
    }

    public GraphNodeNotInGraph(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}


class Graph<T> {

    ArrayList<T> nodes;
    HashMap<T, ArrayList<T>> edges;

    public Graph() {
        this.nodes = new ArrayList<T>();
        this.edges = new HashMap<T, ArrayList<T>>();
    }

    public void addNode(T val) {
        nodes.add(val);
        edges.put(val, new ArrayList<T>());
    }

    public void addEdge(T src, T dest) {
        edges.get(src).add(dest);
    }

    public void print() {
        int i= 0;
        for(T node: nodes) {
            StringBuilder sb = new StringBuilder();
            sb.append(node.toString());
            sb.append("->");
            for(T edgeNode: edges.get(node)) {
                sb.append(edgeNode.toString());
                sb.append(", ");
            }
            System.out.println(sb);
            i++;
        }
    }

    public boolean findRoute(T src, T dest) {

        Queue<LinkedList<T>> queue = new LinkedList<LinkedList<T>>();
        LinkedList<T> routeSrc = new LinkedList<>();
        HashSet<T> visitedNodes = new HashSet<>();

        routeSrc.add(src);
        visitedNodes.add(src);
        queue.add(routeSrc);

        while(!queue.isEmpty()) {
            LinkedList<T> cur =  queue.remove();
            visitedNodes.add(cur.peekLast());
            ArrayList<T> nextNodes = edges.get(cur.peekLast());
            for(T node: nextNodes) {
                if (visitedNodes.contains(node))
                    continue;
                if (node.equals(dest)) {
                    cur.add(dest);
                    System.out.println(cur);
                    return true;
                } else {
                    LinkedList<T> newRoute = (LinkedList<T>) cur.clone();
                    newRoute.add(node);
                    queue.add(newRoute);
                }
            }
        }
        return false;
    }


}

public class Main {

    public static void main(String[] args) {
	// write your code here
        Graph<String> graph = new Graph<>();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("X");
        graph.addNode("Y");
        graph.addNode("Z");


        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "G");
        graph.addEdge("B", "D");
        graph.addEdge("D", "A");

        graph.print();
        System.out.println(graph.findRoute("A", "M"));

    }
}
