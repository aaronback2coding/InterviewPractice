package com.aaron;
//----------------------------------------------------------------------------------------------------------------------------
//Find the first common ancestor of two nodes, avoid storing additional nodes in a data structure
// Things to practice:
//    print node full path using post order traversal - done
//    stack overflow using recursion - done.
//    basic stack iteration vs recursion conversation - done.
//    solution 1: depth, shift to same depth, and then backtrack to the common ancestor - done.
//    solution 2: backtrack and see whether parents covers the other node
//    solution 3: could not use back track (link to parent), iterate from the root, check whether they are on different sides
//                implemented through recursion - done.
//    solution 3.1: optimize by combining the cover function and the check function into one. - done!
//----------------------------------------------------------------------------------------------------------------------------




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


    //assuming have access to parent
    //get depth first
    //then move the deeper one to the same level
    //then move them up together. when they meet, it is the common ancestor
    private int getDepth(Node<T> node) {
        int i = 0;
        Node<T> cur = node;
        while (cur != null) {
            cur = cur.parent;
            i++;
        }
        return i;
    }

    public Node<T> getCommonAncestor1(Node<T> node1, Node<T> node2) {
        int depth1 = getDepth(node1);
        int depth2 = getDepth(node2);
        int delta = Math.abs(depth1 - depth2);
        Node<T> lower = depth1 >= depth2 ? node1 : node2;
        Node<T> higher = depth1 >= depth2 ? node2 : node1;

        while(delta!= 0) {
            lower = lower.parent;
            delta--;
        }
        while(!lower.equals(higher)) {
            lower = lower.parent;
            higher = higher.parent;
        }
        return lower;
    }

    //backtrack and see whether it covers.
    private boolean cover(Node<T> ancestor, Node<T> node) {
        if(ancestor == null)
            return false;
        if(node == null)
            return false;

        if(ancestor.equals(node))
            return true;

        return cover(ancestor.left, node) || cover(ancestor.right, node);
    }

    public Node<T> getCommonAncestor2(Node<T> node1, Node<T> node2) {
        if(node1 == null)
            return null;
        if(node2 == null)
            return null;

        Node<T> cur = node1;
        while(cur != null) {
            if(cover(cur, node2)) {
                return cur;
            }
            cur = cur.parent;
        }
        return null;
    }
    //could not back track, have to come from the root
    private Node<T> _getCommonAncestor (Node<T> ancestor, Node<T> node1, Node<T> node2) {
        if(ancestor == null)
            return null;
        if(node1 == null)
            return null;
        if(node2 == null)
            return null;

        boolean isChild1 = node1.equals(ancestor.left) || node1.equals(ancestor.right);
        boolean isChild2 = node2.equals(ancestor.left) || node2.equals(ancestor.right);
        if(isChild1 && isChild2)
            return ancestor;

        if(isChild1 && !isChild2)
            return node1;

        if(!isChild1 && isChild2)
            return node2;

        Node<T> leftresult = _getCommonAncestor(ancestor.left, node1, node2);
        Node<T> rightresult = _getCommonAncestor(ancestor.right, node1, node2);

        if(leftresult == null) {
            return rightresult;
        }

        if(rightresult == null) {
            return leftresult;
        }

        if(!leftresult.equals(node1) && !leftresult.equals(node2))
            return leftresult;

        if(!rightresult.equals(node1) && !rightresult.equals(node2))
            return rightresult;

        if(leftresult.equals(node1) && rightresult.equals(node2))
            return ancestor;

        if(leftresult.equals(node2) && rightresult.equals(node1))
            return ancestor;

        return null;

    }

    public Node<T> getCommonAncestor3(Node<T> node1, Node<T> node2) {
        if(node1 == null)
            return null;
        if(node2 == null)
            return null;
        return _getCommonAncestor(root, node1, node2);
    }


    // get full path to a node.
    public LinkedList<T> getPath(T data) {
        if(root ==  null)
            return null;
        LinkedList<T> path = new LinkedList<>();
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()) {
            Node<T> cur = stack.pop();
            if(cur.visited == false) {
                stack.push(cur);
                cur.visited = true;
                if(cur.right != null)
                    stack.push(cur.right);
                if(cur.left != null)
                    stack.push(cur.left);
            } else {
                if(cur.val.compareTo(data) == 0) {
                    stack.push(cur);
                    cur.visited =  true;
                    break;
                }
            }
        }
        for(Node<T> item: stack) {
            if(item.visited == true)
                path.add(item.val);
        }
        return path;
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

//     there are two basic operation for every node: exploration vs. actual traveral iteration.
//     exploration is the act of finding children and putting childen in the execution stack / queue, following certain order
//     iteration is the action you want to do in this traversal, such as print, search etc.
//     for preorder, the exploration and iteration is together
//     for post order and in order, the exploration is done first and then we backtrack to iterate, therefore we can utilize these two for loop detection
//          if we reach the same node that has been explored before but haven't been executed, then hit a loop
//          in the parallel case, when we run into a node that has been explored and executed, it means that it has been done by another path and therefore there is no loop.

    // print pre order, implemented with iteration
    void printPreOrder() {
        System.out.println("................");
        if (root == null)
            return;
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node<T> cur = stack.pop();
            // iteration action
            System.out.println(cur.val);
            // exploration action
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
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

    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.root = new Node<>(1);
        tree.root.left = new Node<>(2);
        tree.root.right = new Node<>(3);
        tree.root.left.left = new Node<>(4);
        tree.root.left.right = new Node<>(5);
        tree.root.right.left = new Node<>(6);
        tree.root.right.right = new Node<>(7);
        tree.root.left.left.left = new Node<>(8);
        tree.root.left.left.right = new Node<>(9);
        tree.root.left.right.left = new Node<>(10);
        tree.root.left.right.right = new Node<>(11);
        tree.root.right.left.left = new Node<>(12);
        tree.root.right.left.right = new Node<>(13);
        tree.root.right.right.left = new Node<>(14);
        tree.root.right.right.right = new Node<>(15);

        Node<Integer> node1 = tree.root.left.left.right; // 9
        Node<Integer> node2 = tree.root.left.right.right; // 11


        Node<Integer> node3 = tree.root.right.left.right; //13
        Node<Integer> node4 = tree.root.right.right.right; // 15

        tree.fillParents();
        tree.printTree();
        System.out.println("The common ancestor is");
        System.out.println(tree.getCommonAncestor3(node1, node2).val);
    }
}




//// basic recursion vs. converting it to a loop
//public class Main {
//
//    //recursion - the spirit is to break the big problem into smaller ones. this is top down
//    public static int factorial (int n) {
//        if (n == 1 || n ==  0)
//            return 1;
//        return factorial(n-1 ) * n;
//    }
//
//    //iteration - the spirit is to build the solution from small to big. this is bottom up.
//    public static int factorial2 (int n) {
//        int result = 1;
//        for (int i = 1; i <= n; i++) {
//            result = result * i;
//        }
//        return result;
//    }
//
//    //iteration - to keep the problem solving order of top down but still use iteration, you use stack (which is first in last out order)
//    public static int factorial3 (int n) {
//        Stack<Integer> stack = new Stack<>();
//        int i = n;
//        while (i >= 1) {
//            stack.push(i);
//            i--;
//        }
//        int result = 1;
//        while(!stack.empty()) {
//            result = result * stack.pop();
//        }
//        return result;
//    }
//
//
//
//    public static void main(String[] args) {
//        System.out.println(factorial(6));
//        System.out.println(factorial2(6));
//        System.out.println(factorial3(6));
//
//
//    }
//}


//// stack overflow exercise
//public class Main {
//
//    static int a = 0;
//
//    public static void func1 () {
//        a++;
//        int b = 0;
//        int c = 0;
//        int[] ar = new int[19];
//        func1();
//    }
//
//    public static void main(String[] args) {
//        try {
//            func1();
//        }
//        catch(StackOverflowError t) {
//            System.out.println(t);
//            System.out.println(a);
//        }
//    }
//}
