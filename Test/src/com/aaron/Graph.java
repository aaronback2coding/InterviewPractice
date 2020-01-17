package com.aaron;

import java.lang.reflect.Array;
import java.util.*;

public class Graph {
    ArrayList<Integer>[] edges;

    public Graph(int nodeSize) {
        edges = new ArrayList[nodeSize];
    }

    void addEdge(int n1, int n2) {
        if(edges[n1] == null) {
            edges[n1] = new ArrayList<>();
        }
        edges[n1].add(n2);
    }

    void print() {
        for(int i = 0; i < edges.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append("->");
            if(edges[i] == null)
                continue;
            for(Integer j: edges[i]) {
                sb.append(j);
                sb.append(",");
            }
            System.out.println(sb);
        }
    }

    boolean breadthSearch(int n1, int n2) {
        Queue<Integer> queue = new LinkedList<>(); // explore to do
        Queue<ArrayList> paths = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>(); // visited
        queue.add(n1); // init

        ArrayList<Integer> firstPath = new ArrayList<>();
        paths.add(firstPath);

        while (!queue.isEmpty()) {
            int cur = queue.remove();
            ArrayList<Integer> curPath = paths.remove();
            //visit
            System.out.println(cur);
            if(cur == n2) {
                curPath.add(cur);
                System.out.println(curPath);
                return true;
            }
            visited.add(cur);

            //breadth search
            ArrayList<Integer> curEdges = edges[cur];
            if(curEdges == null)
                continue;
            for(int nextNode : curEdges) {
                if(!visited.contains(nextNode)) {
                    ArrayList<Integer> newPath = (ArrayList<Integer>) curPath.clone();
                    newPath.add(cur);
                    queue.add(nextNode);
                    paths.add(newPath);
                }
            }
        }
        return false;
    }

    boolean depthSearch(int n1, int n2) {
        Stack<Integer> stack = new Stack<>(); // explore to do
        HashSet<Integer> visited = new HashSet<>(); // visited
        stack.push(n1); // init
        while (!stack.isEmpty()) {
            int cur = stack.pop();

            //visit
            System.out.println(cur);
            if(cur == n2)
                return true;
            visited.add(cur);

            //depth search
            ArrayList<Integer> curEdges = edges[cur];
            if(curEdges == null)
                continue;
            for(int nextNode : curEdges) {
                if(!visited.contains(nextNode)) {
                    stack.push(nextNode);
                }
            }
        }
        return false;
    }

//    ArrayList<Integer> breadthSearchRoute (int n1, int n2) {
//        Queue<>
//
//    }

}
