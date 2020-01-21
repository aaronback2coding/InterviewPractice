package com.company;

import java.util.ArrayList;
import java.util.HashMap;

class Vertice<T extends DeepCopyAble<T>> {
    T data;
    ArrayList<Vertice<T>> edges;
}

public class Graph2<T extends DeepCopyAble<T>> {
//    G := {
//  [V1,...,Vn]
//    }
//(a "Graph" is just a container of vertices), and
//    V_i := {
//        Data data,
//  [&Vi_1,...,&Vi_k]
//    }


    ArrayList<Vertice<T>> vertices = null;

    public Graph2() {
        vertices = new ArrayList<>();
    }

    public Vertice<T> addNode(T data) {
        Vertice<T> node = new Vertice<>();
        // shallow copy only
        node.data = data.deepCopy();
        vertices.add(node);
        return node;
    }

    public void addEdge(Vertice<T> v1, Vertice<T> v2) {
        if(v1 == null || v2 == null)
            return;
        if(v1.edges == null)
            v1.edges = new ArrayList<>();
        v1.edges.add(v2);
    }

    Vertice<T> getNewV(HashMap<Vertice<T>, Vertice<T>> map, Graph2<T> clone, Vertice<T> v) {
        Vertice<T> newV = null;
        if(!map.containsKey(v)) {
            newV = clone.addNode(v.data);
            map.put(v, newV);
        } else {
            newV = map.get(v);
        }
        return newV;
    }

    public Graph2<T> clone() {
        Graph2<T> clone = new Graph2<>();
        HashMap<Vertice<T>, Vertice<T>> map = new HashMap<>();
        //create nodes
        for(Vertice<T> v : this.vertices) {
            Vertice<T> newV = getNewV(map, clone, v);
            if(v.edges == null)
                continue;
            for(Vertice<T> neighbour : v.edges) {
                Vertice<T> new_neighbour = getNewV(map, clone, neighbour);
                clone.addEdge(newV, new_neighbour);
            }
        }
        return clone;
    }


}
