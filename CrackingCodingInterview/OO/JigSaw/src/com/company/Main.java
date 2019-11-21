package com.company;
// Learning
//  the core concept of OO design:
//      model the design as objects and their associations - game, hand, deck, card
//      pursue abstraction, inheritance, and polymorphism for data reuse - game specific logic vs. game generics
//      decouple and encapsulate data as needed
//      the actual implementation using Java
//      leanring - when you write code, try to write generic code if possible. it will make people feel good about your design
//      the basics of business logic vs. code logic separation
//      learning (from the parking lot question) - it is easy to model business logic when there are concrete objects already
//          e.g. vehicle, car, bus, bike etc. The harder part is to observe, discover, and abstract objects that are not that
//          concrete but critical to simulate the real world situation - e.g. parking record,which is s a critical structure to
//          park and unpark cars.
//          the key part of OO design is to find the right object, container, and action to represent the real world situation





public class Main {

    public static void main(String[] args) {
        Puzzle puzzle = new Puzzle(10);
        puzzle.print();
        puzzle.shuffle();
        puzzle.print();
        puzzle.solve();
        puzzle.print();
    }
}
