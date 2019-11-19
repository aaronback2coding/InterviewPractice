package com.aaron;
// Learning
//  the core concept of OO design:
//      model the design as objects and their associations - game, hand, deck, card
//      pursue abstraction, inheritance, and polymorphism for data reuse - game specific logic vs. game generics
//      decouple and encapsulate data as needed
//      the actual implementation using Java
//      leanring - when you write code, try to write generic code if possible. it will make people feel good about your design
//      the basics of business logic vs. code logic separation



import com.aaron.blackjack.BlackJackGame;
import com.aaron.blackjack.BlackJackHandFactory;

public class Main {

    public static void main(String[] args) {
        BlackJackHandFactory handfactory = new BlackJackHandFactory();
        BlackJackGame game = new BlackJackGame(5, handfactory);
        game.play();


    }
}
