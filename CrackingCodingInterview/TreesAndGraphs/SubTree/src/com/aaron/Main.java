package com.aaron;

//----------------------------------------------------------------------------------------------------------------
// Question: check one tree is the subtree of the other
//----------------------------------------------------------------------------------------------------------------


import java.lang.reflect.Array;
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
// This whole pos thing is really not necessary, as the whole merger algorithm can be done with just one function.
// the concept of realizing that there is duplciation in position shifting is good and spot on, but the solution is inefficient
class ListOrderMerger<T> {
    class Pos<T> {
        T val = null;
    }
    private int leftLength = 0;
    private int rightLength = 0;
    LinkedList<Pos<T>> left;
    LinkedList<Pos<T>> right;
    LinkedList<LinkedList<Pos<T>>> merged;


    public ListOrderMerger(int leftLength, int rightLength) {
        this.leftLength = leftLength;
        this.rightLength = rightLength;
        this.left = new LinkedList<Pos<T>>();
        this.right = new LinkedList<Pos<T>>();
    }

    private LinkedList<LinkedList<Pos<T>>> addOne(Pos<T> one, LinkedList<Pos<T>> list) {
        LinkedList<LinkedList<Pos<T>>> result = new LinkedList<LinkedList<Pos<T>>>();
        for(int i = 0; i <= list.size(); i++) {
            LinkedList<Pos<T>> temp = new LinkedList<Pos<T>>();
            for(int j = 0; j < list.size(); j++) {
                if(i == j)
                    temp.add(one);
                temp.add(list.get(j));
            }
            if(i == list.size()) {
                temp.add(one);
            }
            result.add(temp);
        }
        return result;
    }

    private LinkedList<LinkedList<Pos<T>>> _merge(LinkedList<Pos<T>> left, LinkedList<Pos<T>> right) {
        //initial state
        if(left.size() == 0)
            return null;
        if(right.size() == 0)
            return null;
        if(left.size() == 1) {
            return addOne(left.get(0), right);
        }
        if(right.size() == 1) {
            return addOne(right.get(0), left);
        }

        //left first
        LinkedList<Pos<T>> leftCopy = (LinkedList<Pos<T>>) left.clone();
        LinkedList<Pos<T>> rightCopy = (LinkedList<Pos<T>>) right.clone();

        Pos<T> leftFirst = leftCopy.remove();
        LinkedList<LinkedList<Pos<T>>> leftFirstResults = _merge(leftCopy, rightCopy);
        for(LinkedList<Pos<T>> item: leftFirstResults) {
            item.addFirst(leftFirst);
        }

        //right first
        leftCopy = (LinkedList<Pos<T>>) left.clone();
        rightCopy = (LinkedList<Pos<T>>) right.clone();

        Pos<T> rightFirst = rightCopy.remove();
        LinkedList<LinkedList<Pos<T>>> rightFirstResults = _merge(leftCopy, rightCopy);
        for(LinkedList<Pos<T>> item: rightFirstResults) {
            item.addFirst(rightFirst);
        }
        leftFirstResults.addAll(rightFirstResults);
        return leftFirstResults;
    }

    public void initializeMerger() {
        for(int i = 0; i < leftLength; i++)  {
            left.add(new Pos<T>());
        }
        for(int i = 0; i < rightLength; i++)  {
            right.add(new Pos<T>());
        }
        merged = _merge(left, right);
    }

    //Link list is a new copy, the actual T object is just reference. Left and right should not change.
    public LinkedList<LinkedList<T>> merge(LinkedList<T> leftT, LinkedList<T> rightT) {
        if(leftT.size() != leftLength)
            return null;
        if(rightT.size() != rightLength)
            return null;
        int i = 0;
        for(T item: leftT) {
            left.get(i).val = item;
            i++;
        }
        i = 0;
        for(T item: rightT) {
            right.get(i).val = item;
            i++;
        }

        LinkedList<LinkedList<T>> results = new LinkedList<LinkedList<T>>();
        for(LinkedList<Pos<T>> item: merged) {
            LinkedList<T> temp = new LinkedList<T>();
            for(Pos<T> pos: item) {
                temp.add(pos.val);
            }
            results.add(temp);
        }
        return results;
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

    // build a BST from a sorted array.
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

    // create a BST from a array following the insert order
    // this is a tree factory

    private void insertBST(Node<T> root, T val) {
        if(root == null)
            return;
        if(root.val.compareTo(val) < 0) {
            if(root.right == null) {
                Node<T> node = new Node<>(val);
                root.right = node;
                return;
            } else {
                insertBST(root.right, val);
            }
        } else {
            if(root.left == null) {
                Node<T> node = new Node<>(val);
                root.left = node;
                return;
            } else {
                insertBST(root.left, val);
            }
        }
    }

    static public <T extends Comparable<T>> Tree<T> createBST(T[] arr) {
        if(arr.length == 0)
            return null;
        Tree<T> tree = new Tree<>();
        tree.root = new Node<>(arr[0]);
        for(int i = 1; i < arr.length; i++) {
            tree.insertBST(tree.root, arr[i]);
        }
        return tree;
    }

    // Create all possible insert array from a BST
    static private <T extends Comparable<T>> LinkedList<LinkedList<T>> deepClone (LinkedList<LinkedList<T>> src) {
        if (src == null)
            return null;
        LinkedList<LinkedList<T>> dest = new  LinkedList<LinkedList<T>>();
        for (LinkedList<T> item: src) {
            dest.add((LinkedList<T>) item.clone());
        }
        return dest;
    }

    public static <T extends Comparable<T>>  void printLL(LinkedList<LinkedList<T>> ll) {
        if(ll == null)
            return;
        for(LinkedList<T> item: ll) {
            System.out.println(item);
        }
    }

    private LinkedList<LinkedList<T>> _merge(LinkedList<LinkedList<T>> left, LinkedList<LinkedList<T>> right) {

        LinkedList<LinkedList<T>> leftNew = deepClone(left);
        LinkedList<LinkedList<T>> rightNew = deepClone(right);

        if(left == null)
            return rightNew;
        if(right == null)
            return leftNew;

        if(left.size() == 0 || right.size() == 0) {
            return null;
        }

        ListOrderMerger<T> merger = new ListOrderMerger<T>(left.peek().size(), right.peek().size());
        merger.initializeMerger();

        LinkedList<LinkedList<T>> results = new LinkedList<LinkedList<T>>();

        for(LinkedList<T> leftItem : left) {
            for(LinkedList<T> rightItem : right) {
                results.addAll(merger.merge(leftItem, rightItem));
            }
        }
        return results;
    }

    private LinkedList<LinkedList<T>> _getBSTArray(Node<T> node) {


        if(node == null)
            return null;

        LinkedList<LinkedList<T>> results = _merge(_getBSTArray(node.left), _getBSTArray(node.right));
        if(results == null) {
            results = new LinkedList<LinkedList<T>>();
            LinkedList<T> onlyOne = new LinkedList<>();
            onlyOne.add(node.val);
            results.add(onlyOne);
        } else {
            for(LinkedList<T> result: results) {
                result.addFirst(node.val);
            }
        }

        System.out.println("------------");
        System.out.println(node.val);
        for(LinkedList<T> item: results) {
            System.out.println(item);
        }

        return results;
    }

    public LinkedList<LinkedList<T>> getBSTArray() {
        return _getBSTArray(root);
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

    //check whether the tree is a subtree
    private boolean isEqual(Node<T> left, Node<T> right) {
        if(left == null && right == null)
            return true;
        if(right == null || right == null)
            return false;
        Stack<Node<T>> leftStack = new Stack<>();
        Stack<Node<T>> rightStack = new Stack<>();
        leftStack.push(left);
        rightStack.push(right);

        while(!leftStack.empty() && !rightStack.empty()) {
            Node<T> leftCur = leftStack.pop();
            Node<T> rightCur = rightStack.pop();
            if(leftCur.val.compareTo(rightCur.val) != 0) {
                return false;
            }
            if(leftCur.left != null) {
                leftStack.push(leftCur.left);
            }
            if(leftCur.right != null) {
                leftStack.push(leftCur.right);
            }
            if(rightCur.left != null) {
                rightStack.push(rightCur.left);
            }
            if(leftCur.right != null) {
                rightStack.push(rightCur.right);
            }

        }

        if(!leftStack.empty() || !rightStack.empty())
            return false;

        return true;


    }

    public boolean isSubtree(Tree<T> subtree) {
        if(subtree == null)
            return false;

        if(subtree.root == null)
            return false;


        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        while(!stack.empty()) {
            Node<T> cur = stack.pop();
            if(isEqual(cur, subtree.root)) {
                return true;
            }
            if(cur.left != null) {
                stack.push(cur.left);
            }
            if(cur.right != null) {
                stack.push(cur.right);
            }
        }
        return false;
    }
}

public class Main {

    public static void main(String[] args) {

        Integer[] arr0 = {3, 1, 5, 2, 4, 6, 0};
        Tree tree = Tree.createBST(arr0);
        tree.printTree();


        Integer[] arr1 = {1, 2, 0};
        Tree tree2 = Tree.createBST(arr1);
        tree2.printTree();


        Integer[] arr2 = {1, 2};
        Tree tree3 = Tree.createBST(arr2);
        tree3.printTree();

        System.out.println(tree.isSubtree(tree2));
        System.out.println(tree.isSubtree(tree3));




    }
}
