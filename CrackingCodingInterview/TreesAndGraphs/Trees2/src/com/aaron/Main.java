package com.aaron;

//----------------------------------------------------------------------------------------------------------------
// Question 1: implement a function to check whether a binary tree is balanced or not -
//              balanced means diff of depth between left and right is <=1
//  side question: how to return multiple values? the best way is to use a class to warp
// question 2: check if a tree is a binary tree search tree
// question 3: write a function to find the next node (in order successor)  of a given node in a binary search tree.
//              you may assume that each node has a link to its parent
//----------------------------------------------------------------------------------------------------------------


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node<T extends Comparable<T>> {
    T val;
    Node<T> left = null;
    Node<T> right = null;
    Node<T> parent = null;

    boolean visited = false;
    int x;
    int y; // depth, start from 0 at root.

    int depth; //depth, start from leaf from 1.

    public Node(T val) {
        this.val = val;
    }
    public Node<T> getParent(boolean upRight) {
        boolean isParentRight = false;
        if(this.parent == null)
            return null;
        if(this.parent.left.equals(this))
            isParentRight = true;
        if(upRight && isParentRight)
            return this.parent;
        if (!upRight && !isParentRight)
            return this.parent;
        return null;
    }

    public Node<T> getInOrderNeighbor() {

        if(this.right != null)
            return this.right;

        Node<T> upRightParent = this.getParent(true);
        if(upRightParent != null)
            return upRightParent;

        Node<T> cur = this;
        Node<T> curLeftParent = this.getParent(false);

        while(curLeftParent != null) {
            Node<T> rightGrandParent = curLeftParent.getParent(true);
            if(rightGrandParent != null)
                return rightGrandParent;
            else {
                cur = curLeftParent;
                curLeftParent = curLeftParent.getParent(false);
            }
        }
        return null;
    }

}

class Tree<T extends Comparable<T>> {
    Node<T> root;

    //find next in order node
    public void fillParents() {
        if (root == null)
            return;
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> cur = queue.remove();
            if (cur.left != null){
                queue.add(cur.left);
                cur.left.parent = cur;
            }
            if (cur.right != null){
                queue.add(cur.right);
                cur.right.parent = cur;
            }
        }
    }

    // check whether a tree is balanced or not
    private boolean _isBalanced(Node<T> node) {

        if(node == null)
            return true;

        int leftDepth = 0;
        int rightDepth = 0;


        if(node.left != null) {
            if(!_isBalanced(node.left))
                return false;
            leftDepth = node.left.depth;
        }

        if(node.right != null) {
            if(!_isBalanced(node.right))
                return false;
            rightDepth = node.right.depth;
        }

        node.depth = Math.max(leftDepth, rightDepth) + 1;

        if (Math.abs(leftDepth - rightDepth) >= 2)
            return false;

        return true;
    }

    public boolean isBalanced() {
        return _isBalanced(root);
    }

    // is the tree a binary search tree?
    // in order iteration return false if the iteration is not in order
    public boolean isBinarySearchTree() {

        if (root == null)
            return false;

        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        T preVal = null;

        while (!stack.empty()) {
            Node<T> cur = stack.pop();
            if(!cur.visited) {
                if(cur.right != null)
                    stack.push(cur.right);
                stack.push(cur);
                cur.visited = true;
                if(cur.left != null)
                    stack.push(cur.left);
            } else {
                if (preVal != null && cur.val.compareTo(preVal) < 0)
                    return false;
                else
                    preVal = cur.val;
            }
        }
        return true;


    }

    // build a BST from a sorted array
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


    // get nodes per depth (level)
    public LinkedList<LinkedList<T>> getNodesPerDepth() {
        if(root == null)
            return null;

        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        LinkedList<LinkedList<T>> result = new LinkedList<LinkedList<T>>();
        queue.add(root);
        int depth = 0;
        root.y = 0;

        LinkedList<T> curList = new LinkedList<T>();
        result.add(curList);

        while (!queue.isEmpty()) {

            Node<T> cur = queue.remove();
            if(cur.y != depth) {
                curList = new LinkedList<T>();
                result.add(curList);
                depth ++;
            }
            curList.add(cur.val);

            if(cur.left != null) {
                cur.left.y = cur.y + 1;
                queue.add(cur.left);
            }
            if(cur.right != null) {
                cur.right.y = cur.y + 1;
                queue.add(cur.right);
            }
        }
        return result;

    }


    // print in tree structure
    class nodePrintInfo<T> {
        T val;
        int pos;

        public nodePrintInfo(T val, int pos) {
            this.val = val;
            this.pos = pos;
        }
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

    // print post order, implemented with iteration
    // this depth based search is ideal for loop detection in a tree
    void printPostOrder() {
        System.out.println("................");
        if (root == null)
            return;
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node<T> cur = stack.pop();
            //if not visit, scan and visit
            if (!cur.visited) {
                stack.push(cur);
                if (cur.right != null)
                    stack.push(cur.right);
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


    private void resetVisited() {
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

    public static void fuc (Integer i) {
        i = 10;
    }
    public static void main(String[] args) {
//        // write your code here
        Tree<Integer> tree = new Tree<>();
        tree.root = new Node<>(1);
        tree.root.left = new Node<>(2);
        tree.root.right = new Node<>(3);
        tree.root.left.left = new Node<>(4);
        tree.root.left.right = new Node<>(5);
        tree.root.right.left = new Node<>(6);
        tree.root.right.right = new Node<>(7);

        tree.print(0);
        tree.print(1);
        tree.print(2);

        tree.printPostOrder();
//        tree.printTree();
//        System.out.println(tree.isBalanced());
//
//
//        Tree<Integer> tree2 = new Tree<>();
//        tree2.root = new Node<>(1);
//        tree2.root.left = new Node<>(2);
//        tree2.root.left.left = new Node<>(4);
//        tree2.root.left.right = new Node<>(5);
//        tree2.printTree();
//        System.out.println(tree2.isBalanced());


//
//        Tree<Integer> tree2 = new Tree<>();
//        tree2.root = new Node<>(1);
//        tree2.root.right = new Node<>(2);
//        tree2.root.right.right = new Node<>(3);
//        tree2.root.right.right.right = new Node<>(4);
//        tree2.root.right.right.right.right = new Node<>(5);
//        tree2.printTree();

//        Integer[] arr = {-1, 1, 2, 3, 4, 5, 6};
//        Tree<Integer> tree = new Tree<>();
//        tree.buildBST(arr);
//        tree.printTree();
//
//        System.out.println(tree.isBinarySearchTree());

//        Tree<Integer> tree = new Tree<>();
//        tree.root = new Node<>(1);
//        tree.root.left = new Node<>(2);
//        tree.root.right = new Node<>(3);
//        tree.root.left.left = new Node<>(4);
//        tree.root.left.right = new Node<>(5);
//        tree.root.right.left = new Node<>(6);
//        tree.root.right.right = new Node<>(7);
//        tree.printTree();
//        tree.fillParents();
//
//        Node<Integer> node;
//        node = tree.root.right.left .getInOrderNeighbor(); //6, expect 3.
//        node = tree.root.right.right.getInOrderNeighbor(); // 7, expect null
//        node = tree.root.getInOrderNeighbor(); // 1, expect 3;




    }
}
