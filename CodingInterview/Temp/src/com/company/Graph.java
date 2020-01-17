package com.company;

import java.util.*;

public class Graph {
    class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    int size = 0;
    ArrayList<Edge>[] edges = null;

    public Graph(int size) {
        this.size = size;
        edges = new ArrayList[size];
    }

    public void addEdges(int n1, int n2, int weight) {
        if(edges[n1] == null) {
            edges[n1] = new ArrayList<>();
        }
        Edge e = new Edge(n2, weight);
        edges[n1].add(e);
    }

    class Edge_Comparator implements Comparator<Edge> {
        public int compare(Edge e1, Edge e2)
        {
            return e1.weight - e2.weight;
        }
    }

    public boolean djk(int n1, int n2) {
        if(n1 >= size || n1 < 0 || n2 >= size || n2 < 0)
            return false;

        PriorityQueue<Edge> frontier = new PriorityQueue<>(new Edge_Comparator());
        int[] cameFrom = new int[size];
        int[] cost = new int[size];
        Edge e = new Edge(n1, 0);
        frontier.add(e);
        cameFrom[n1] = -1;
        cost[n1] = 0;

        while(!frontier.isEmpty()) {
            Edge cur = frontier.remove();

            System.out.println(cur.node);
            if(cur.node == n2) {
                int i = n2;
                Stack<Integer> path = new Stack<>();
                path.add(cur.node);
                while(i != 0 && i != -1) {
                    path.add(cameFrom[i]);
                    i = cameFrom[i];
                }
                StringBuilder sb = new StringBuilder();
                while(!path.empty()) {
                    sb.append(path.pop());
                    sb.append("->");
                }
                System.out.println(sb);
                return true;
            }

            ArrayList<Edge> newNodes = edges[cur.node];
            if(newNodes == null)
                continue;
            for(Edge newNode : newNodes) {
                int newNodeCost = cost[cur.node] + newNode.weight;
                if(cameFrom[newNode.node] == 0 || newNodeCost < cost[newNode.node]) {
                    cost[newNode.node] = newNodeCost;
                    cameFrom[newNode.node] = cur.node;
                    Edge newNode_withCost = new Edge(newNode.node, cost[newNode.node]);
                    frontier.add(newNode_withCost);
                }
            }
        }
        return false;
    }

    public boolean breadthSearch(int n1, int n2) {
        Queue<Integer> frontier = new LinkedList<>();
        int[] cameFrom = new int[size];
        frontier.add(n1);
        cameFrom[n1] = -1;

        while(!frontier.isEmpty()) {
            int cur = frontier.remove();

            System.out.println(cur);
            if(cur == n2) {
                int i = n2;
                Stack<Integer> path = new Stack<>();
                path.add(cur);
                while(i != 0 && i != -1) {
                    path.add(cameFrom[i]);
                    i = cameFrom[i];
                }
                StringBuilder sb = new StringBuilder();
                while(!path.empty()) {
                    sb.append(path.pop());
                    sb.append("->");
                }
                System.out.println(sb);
                return true;
            }

            ArrayList<Edge> newNodes = edges[cur];
            if(newNodes == null)
                continue;
            for(Edge edge : newNodes) {
                if(cameFrom[edge.node] == 0) {
                    frontier.add(edge.node);
                    cameFrom[edge.node] = cur;
                }
            }
        }
        return false;
    }
}
