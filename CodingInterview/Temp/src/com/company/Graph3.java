package com.company;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class Graph3 {
    int size = 0;
    ArrayList<Integer>[] edges = null;
    ArrayList<Integer> rootNodes = null;

    public Graph3(int size) {
        this.size = size;
        edges = new ArrayList[size];
        rootNodes = new ArrayList<>();
    }

    public void addEdges(int n1, int n2) {
        if(edges[n1] == null) {
            edges[n1] = new ArrayList<>();
        }
        if(edges[n2] == null) {
            edges[n2] = new ArrayList<>();
        }
        edges[n1].add(n2);
        edges[n2].add(n1);

    }

    public void addRootNodes(int n) {
        rootNodes.add(n);
    }

    public Iterable<Integer> getNodesWithNoIncomingEdges() {
        return rootNodes;
    }

    public Iterable<Integer> getOutgoingNeighborsFor(int n) {
        return edges[n];
    }

    public void print() {
        Iterable<Integer> initialNodes = getNodesWithNoIncomingEdges();
        if(initialNodes == null)
            return;
        Queue<Integer> frontier = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        for(Integer node : initialNodes) {
            frontier.add(node);
            visited.add(node);
        }
        while(!frontier.isEmpty()) {
            Integer cur = frontier.remove();
            System.out.println(cur);
            Iterable<Integer> newNodes = getOutgoingNeighborsFor(cur);
            if(newNodes == null)
                continue;
            for(Integer node : newNodes) {
                if(!visited.contains(node)) {
                    frontier.add(node);
                    visited.add(node);
                }
            }
        }
    }

    public boolean isBiPartition() {
        int[] color = new int[size]; // 0 = not visited, 1 = first set, 2, second set. if cur is in set 1 and new nodes are also in set 1, then it fail.
            //this served as both visited and recording of the color history.
        for(int i = 0; i < size; i++) {
            Queue<Integer> frontier = new LinkedList<>();
            if(color[i] != 0)
                continue;
            frontier.add(i);
            color[i] = 1;
            while(!frontier.isEmpty()) {
                int cur = frontier.remove();
                ArrayList<Integer> newNodes = edges[cur];
                if(newNodes == null)
                    continue;
                for(Integer newNode : newNodes) {
                    if(color[newNode] == color[cur])
                        return false;
                    if(color[newNode] == 0) {
                        frontier.add(newNode);
                        color[newNode] = 3 - color[cur];
                    }
                }
            }
        }
        return true;
    }

    public ArrayList<Integer> getPotentialFriends(int n) {
        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<Integer> friends = edges[n];
        HashSet<Integer> friendsAndMe = new HashSet<>();
        HashMap<Integer, Integer> potentialFriends = new HashMap<>();
        friendsAndMe.add(n);

        if(friends == null)
            return null;
        for(Integer friend : friends) {
            friendsAndMe.add(friend);
            ArrayList<Integer> fofs = edges[friend];
            if(fofs == null)
                continue;
            for(Integer fof : fofs) {
                if(friendsAndMe.contains(fof))
                    continue;
                if(!potentialFriends.containsKey(fof)) {
                    potentialFriends.put(fof, 1);
                } else {
                    potentialFriends.put(fof, potentialFriends.get(fof) + 1);
                }
            }
        }

        Comparator<Map.Entry<Integer, Integer>> valueComparator = new Comparator<Map.Entry<Integer,Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                return e2.getValue() - e1.getValue();
            }
        };
        List<Map.Entry<Integer, Integer>> listOfEntries = new ArrayList<Map.Entry<Integer, Integer>>(potentialFriends.entrySet());
        Collections.sort(listOfEntries, valueComparator);
        for(Map.Entry<Integer, Integer> entry : listOfEntries) {
            results.add(entry.getKey());
        }
        return results;
    }
}
