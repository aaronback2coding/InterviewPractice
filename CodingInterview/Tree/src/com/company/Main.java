package com.company;

import java.lang.ref.Cleaner;

public class Main {

    public static void main(String[] args) {
	// write your code here
//                    3
//                  / \
//                  2   5
//                  / \
//                  -1   4

//        Node<Integer> root = new Node<>(3);
//        Node<Integer> node2 = root.addChild(2, true);
//        Node<Integer> N5 = root.addChild(5, false);
//        node2.addChild(-1, true);
//        Node<Integer> N4 = node2.addChild(4, false);
//        root.getCommonAncestor(N5, N4);


        Node<Integer> root = new Node<>(1);
        Node<Integer> n2 = root.addChild(2, true);
        Node<Integer> n3 = root.addChild(3, false);
        Node<Integer> n4 = n2.addChild(4, true);
        Node<Integer> n5 = n2.addChild(5, false);
        Node<Integer> n6 = n3.addChild(6, true);
        Node<Integer> n7 = n3.addChild(7, false);
        Node<Integer> n8 = n6.addChild(8, false);
        Node<Integer> n9 = n8.addChild(9, true);

//        root.printPath(n5);

        root.testPrintPath(root);





//        Node.LengthResults<Integer> results = Node.getLongestPath(root);
//        root.print();
//        Node.MyIterator iterator = root.iterator(root);
//
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        System.out.println(root.isBST());
    }
}
