package com.company;
//----------------------------------------------------------------------------------------------------------------
//given a sorted array with unique integer, write a algorithm to create a binary search tree with minimal height
//----------------------------------------------------------------------------------------------------------------
//traversal with recursion, with iteration, print, then BST


import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node<T> {
    T val;
    Node<T> left = null;
    Node<T> right = null;

    boolean visited = false;
    int x;
    int y;

    public Node(T val) {
        this.val = val;
    }
}

class Tree<T> {
    Node<T> root;

    // print tree
    class nodePrintInfo<T> {
        T val;
        int pos;

        public nodePrintInfo(T val, int pos) {
            this.val = val;
            this.pos = pos;
        }
    }

    private Node<T> _buildBST(T[] arr, int start, int end) {
        if(start>end)
            return null;
        int midIndex = (start + end)/2;
        Node<T> midNode = new Node<>(arr[midIndex]);
        if(start < end) {
            midNode.left = _buildBST(arr, start, midIndex - 1);
            midNode.right = _buildBST(arr, midIndex + 1, end);
        }
        return midNode;
    }

    public void buildBST(T[] arr) {
        int length = arr.length;
        root = _buildBST(arr, 0, length - 1);
    }

    private String scan(ArrayList<nodePrintInfo<T>> arr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (j < arr.size()) {
            if (arr.get(j).pos == i) {
                sb.append(arr.get(j).val.toString());
                j++;
            } else {
                sb.append("            ");
                i++;
            }
        }
        return sb.toString();
    }

    void printTree() {
        // generate coordinates through in order traversal
        if (root == null)
            return;
        Stack<Node<T>> stack = new Stack<>();
        int x = 0;
        root.y = 0;
        stack.push(root);
        while (!stack.empty()) {
            Node<T> cur = stack.pop();
            //if not visit, scan and visit
            if (!cur.visited) {
                if (cur.right != null) {
                    stack.push(cur.right);
                    cur.right.y = cur.y + 1;
                }
                stack.push(cur);
                if (cur.left != null) {
                    stack.push(cur.left);
                    cur.left.y = cur.y + 1;
                }
                cur.visited = true;
            } else {
                // if visited or no children, actual iterate
                cur.x = x;
                x++;
            }

        }
        resetVisited();


        // print through breadth first traversal
        System.out.println("................");
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(root);

        ArrayList<nodePrintInfo<T>> scanline = new ArrayList<>();
        int scanlineIndex = 0;

        while (!queue.isEmpty()) {
            Node<T> cur = queue.remove();
            if (cur.y != scanlineIndex) {
                // print previous line
                System.out.println(scan(scanline));
                // create new line
                scanline = new ArrayList<>();
                scanlineIndex++;
            }
            scanline.add(new nodePrintInfo<>(cur.val, cur.x));
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
        System.out.println(scan(scanline));
    }

    // print in order, implemented with iteration
    void printInorder() {
        System.out.println("................");
        if (root == null)
            return;
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node<T> cur = stack.pop();
            //if not visit, scan and visit
            if (!cur.visited) {
                if (cur.right != null)
                    stack.push(cur.right);
                stack.push(cur);
                if (cur.left != null)
                    stack.push(cur.left);
                cur.visited = true;
            } else {
                // if visited or no children, actual iterate
                System.out.println(cur.val);
            }
        }
        resetVisited();
    }

    void resetVisited() {
        if (root == null)
            return;
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> cur = queue.remove();
            cur.visited = false;
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    // print in breadth based search order
    void printBreathFirst() {
        System.out.println("................");
        if (root == null)
            return;
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> cur = queue.remove();
            System.out.println(cur.val);
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    // print in order, pre order and post order, implemented through recursion
    void print(int order) {
        System.out.println("................");
        _print(root, order);
    }

    private void _print(Node<T> node, int order) {
        switch (order) {
            //pre order
            case 1:
                System.out.println(node.val);
                if (node.left != null)
                    _print(node.left, order);
                if (node.right != null)
                    _print(node.right, order);
                break;
            //post order
            case 2:
                if (node.left != null)
                    _print(node.left, order);
                if (node.right != null)
                    _print(node.right, order);
                System.out.println(node.val);
                break;
            //in order
            default:
                if (node.left != null)
                    _print(node.left, order);
                System.out.println(node.val);
                if (node.right != null)
                    _print(node.right, order);
        }
    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here
//        Tree<Integer> tree = new Tree<>();
//        tree.root = new Node<>(1);
//        tree.root.left = new Node<>(2);
//        tree.root.right = new Node<>(3);
//        tree.root.left.left = new Node<>(4);
//        tree.root.left.right = new Node<>(5);
//        tree.root.right.left = new Node<>(6);
//        tree.root.right.right = new Node<>(7);
//        tree.printTree();
//
//        Tree<Integer> tree2 = new Tree<>();
//        tree2.root = new Node<>(1);
//        tree2.root.right = new Node<>(2);
//        tree2.root.right.right = new Node<>(3);
//        tree2.root.right.right.right = new Node<>(4);
//        tree2.root.right.right.right.right = new Node<>(5);
//        tree2.printTree();
//
        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Tree<Integer> tree = new Tree<>();
        tree.buildBST(arr);
        tree.printTree();

    }
}
