package com.company;

import java.sql.ResultSet;
import java.util.*;

public class Node<T extends Comparable<T>> {
    T val;
    Node<T> left = null;
    Node<T> right = null;

    public Node(T val) {
        this.val = val;
    }

    public Node<T> addChild(T val, boolean isLeftOrRight)
    {
        Node<T> newNode = new Node<>(val);
        if(isLeftOrRight)
            this.left = newNode;
        else
            this.right = newNode;
        return newNode;
    }

    public class MyIterator {
        Node<T> root = null;
        Stack<Node<T>> stack = null;
        HashSet<Node<T>> set = null;

        public MyIterator(Node<T>root) {
            stack = new Stack<>();
            set = new HashSet<>();
            this.root = root;
            stack.push(root);

        }

        public boolean hasNext() {
            System.out.println("max set size: " + set.size());
            return !stack.isEmpty();
        }

        //return current, move to next.
        public T next() {
            while (true) {
                Node<T> cur = stack.pop();
                if(set.contains(cur)) {
                    set.remove(cur);
                    return cur.val;
                }
                else {
                    set.add(cur);
                    stack.push(cur);
                    if(cur.right != null)
                        stack.push(cur.right);
                    if(cur.left != null)
                        stack.push(cur.left);
                }
            }
        }
    }

    public MyIterator iterator(Node<T> node) {
        MyIterator iterator = new MyIterator(node);
        return iterator;
    }

    static class LengthResults<T extends Comparable<T>>  {
        int depth = -1;
        Node<T> deepestNode = null;
        int length = -1;
        Node<T> start = null;
        Node<T> end = null;
    }

    public static <T extends Comparable<T>> LengthResults getLongestPath(Node<T> node) {
        LengthResults<T> results = new LengthResults<>();
        // base case
        if(node == null) {
            return results;
        }

        // recursion
        //deepest
        LengthResults<T> leftResults = getLongestPath(node.left);
        LengthResults<T> rightResults = getLongestPath(node.right);
        if(leftResults.depth >= rightResults.depth) {
            results.depth = leftResults.depth + 1;
            results.deepestNode = (leftResults.deepestNode == null) ? node : leftResults.deepestNode;
        } else {
            results.depth = rightResults.depth + 1;
            results.deepestNode = (rightResults.deepestNode == null) ? node : rightResults.deepestNode;
        }

        //longest
        int newLength = leftResults.depth + 1 + rightResults.depth + 1;
        if(leftResults.length >= newLength) {
            results.length = leftResults.length;
            results.start = leftResults.start;
            results.end = leftResults.end;
        } else if (rightResults.length >= newLength) {
            results.length = rightResults.length;
            results.start = rightResults.start;
            results.end = rightResults.end;
        } else {
            results.length = newLength;
            results.start = (leftResults.deepestNode == null) ? node : leftResults.deepestNode;
            results.end = (rightResults.deepestNode == null) ? node : rightResults.deepestNode;
        }
        return results;
    }

    Stack<Node<T>> getPathfromIterationStack(Stack<Node<T>> stack, HashSet<Node<T>> visited) {
        Stack<Node<T>> pathStack = new Stack<>();
        for(Node<T> node: stack) {
            if(visited.contains(node)) {
                pathStack.push(node);
            }
        }
        return pathStack;
    }

    public Node<T> getCommonAncestor(Node<T> node1, Node<T> node2) {
        Stack<Node<T>> stack  = new Stack<>();
        HashSet<Node<T>> visited = new HashSet<>();
        Stack<Node<T>> path1 = null;
        Stack<Node<T>> path2 = null;

        stack.add(this);
        while(!stack.empty()) {
            Node<T> node = stack.pop();
            if(visited.contains(node)) {
                if(node1 == node) {
                    path1 = getPathfromIterationStack(stack, visited);
                }
                if(node2 == node) {
                    path2 = getPathfromIterationStack(stack, visited);
                }
            } else {
                visited.add(node);
                stack.push(node);
                if(node.right != null)
                    stack.push(node.right);
                if(node.left != null)
                    stack.push(node.left);
            }
        }

        HashSet<Node<T>> ancesters1 = new HashSet<>();
        for(Node<T> node : path1) {
            ancesters1.add(node);
        }
        while(!path2.empty()) {
            Node<T> node = path2.pop();
            if(ancesters1.contains(node))
                return node;
        }
        return null;
    }

    public void printPath (Node<T> leaf) {
        if(leaf == null)
            return;

        Stack<Node<T>> stack  = new Stack<>();
        HashSet<Node<T>> visited = new HashSet<>();
        stack.push(this);
        Stack<Node<T>> path = null;

        while(!stack.empty()) {
            Node<T> node = stack.pop();
            if(visited.contains(node)) {
                if(leaf == node) {
                    path = getPathfromIterationStack(stack, visited);
                }
            } else {
                visited.add(node);
                stack.push(node);
                if(node.right != null)
                    stack.push(node.right);
                if(node.left != null)
                    stack.push(node.left);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Node<T> node: path) {
            sb.append(node.val);
        }
        sb.append(leaf.val);
        System.out.println(sb);
    }

    public void testPrintPath(Node<T> node) {
        if (node == null)
            return;
        printPath(node);
        testPrintPath(node.left);
        testPrintPath(node.right);
    }

    public void print() {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(this);
        while(!queue.isEmpty()) {
            Node<T> cur = queue.remove();
            System.out.println(cur.val);
            if(cur.left != null)
                queue.add(cur.left);
            if(cur.right != null)
                queue.add(cur.right);
        }
        return;
    }

//                    3
//                  / \
//                  2   5
//                  / \
//                  -1   4

    static <T extends Comparable<T>> boolean _isBST2(Node<T> node, T min, T max) {
        if(node == null)
            return true;

        return (min == null || node.val.compareTo(min) >= 0)
                && (max == null || node.val.compareTo(max) <= 0)
                && _isBST2(node.left, min, node.val)
                && _isBST2(node.right, node.val, max);
    }

    public boolean isBST() {
        return _isBST2(this, null, null);
    }
}
