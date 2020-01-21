package com.company;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

interface DirectedAcyclicGraph<T> {
    public Iterable<T> getNodesWithNoIncomingEdges();
    public Iterable<T> getOutgoingNeighborsFor(T node);
}

public class DAG<T> implements DirectedAcyclicGraph<T> {
    @java.lang.Override
    public java.lang.Iterable<T> getNodesWithNoIncomingEdges() {
        return null;
    }

    @java.lang.Override
    public java.lang.Iterable<T> getOutgoingNeighborsFor(T node) {
        return null;
    }

    public void print() {
        Iterable<T> initialNodes = getNodesWithNoIncomingEdges();
        if(initialNodes == null)
            return;
        Queue<T> frontier = new LinkedList<>();
        HashSet<T> visited = new HashSet<>();
        for(T node : initialNodes) {
            frontier.add(node);
            visited.add(node);
        }
        while(!frontier.isEmpty()) {
            T cur = frontier.remove();
            System.out.println(cur);
            Iterable<T> newNodes = getOutgoingNeighborsFor(cur);
            if(newNodes == null)
                continue;
            for(T node : newNodes) {
                if(!visited.contains(node)) {
                    frontier.add(node);
                    visited.add(node);
                }
            }
        }
    }
}


