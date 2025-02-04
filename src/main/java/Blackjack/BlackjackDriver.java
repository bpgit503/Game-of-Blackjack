package Blackjack;

import Cards.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackDriver {
    private Deck deckOfCards;
    private Hand playersHand;
    private Hand dealersHand;


    public void run() {
        Scanner scanner = new Scanner(System.in);

        deckOfCards = new Deck();
        playersHand = new Hand();
        dealersHand = new Hand();

        System.out.println("Welcome to Blackjack!");
        System.out.println("The dealer is dealing");

        playersHand.addCard(deckOfCards.deal().get());
        dealersHand.addCard(deckOfCards.deal().get());
        playersHand.addCard(deckOfCards.deal().get());
        dealersHand.addCard(deckOfCards.deal().get());

        System.out.println("The dealer has dealt the cards.");

        boolean endOfTurn = false;

        while (!endOfTurn) {

            System.out.println("\ndealers hand: " + dealersHand.getCard(1));
            System.out.println("\nYour hand: " + playersHand.getCards());


            if (checkForBlackjack(playersHand)) {

                System.out.println("You've hit a natural! \nLets see what the Dealers got 00\n");
                System.out.println("Dealers hand: " + dealersHand.getCards());

                if (checkForBlackjack(dealersHand)) {

                    System.out.println("Its a tie. - push");

                } else {

                    System.out.println("Congratulations! You've hit a won!");
                }
            } else {

                //method to check for over 21 points bust
                System.out.println("\n It is your move: Hit(1) or Stand(2):");
                String input = scanner.nextLine();

                if (input.equals("1") || input.equalsIgnoreCase("hit")) {

                    playersHand.addCard(deckOfCards.deal().get());

                    endOfTurn = checkForBust(playersHand);
                        if (endOfTurn) {
                            System.out.println("You lost the round");
                            System.out.println("\n Your hand: " + playersHand.getCards());
                            break;
                        }

                } else if (input.equals("2") || input.equalsIgnoreCase("stand")) {
                    System.out.println("The dealer reveals his hand");
                    System.out.println("\ndealers hand: " + dealersHand.getCards());

                    while(dealersHand.getHandSum() < 17){

                        dealersHand.addCard(deckOfCards.deal().get());

                        if(checkForBust(dealersHand)){
                            break;
                        }
                    }
                    System.out.println("\n\nLets see the results!");
                    System.out.println("\ndealers hand: " + dealersHand.getCards());
                    System.out.println("\nYour hand: " + playersHand.getCards());
                    compareHands(playersHand, dealersHand);

                    break;
                }
            }


        }
        //bonus add chips the game. Make it as close to bj as possible
    }

    private boolean checkForBust(Hand hand) {

        int aceCount = (int) hand.getCards().stream().filter(card -> card.getRank() == Rank.ACE).count();

        int sumOfCards = hand.getHandSum();

        if (aceCount > 0) {
            for (int i = 0; i < aceCount; i++) {
                if (sumOfCards + 11 <= 21) {
                    sumOfCards += 11;
                } else {
                    sumOfCards += 1;
                }
            }

            return false;

        } else if (sumOfCards > 21) {
            System.out.println("Its a bust!");
            return true;
        }
        return false;
    }

    private void compareHands(Hand playersHand, Hand dealersHand) {
        int playersHandSum = playersHand.getHandSum();
        int dealersHandSum = dealersHand.getHandSum();

        if (playersHandSum == dealersHandSum) {
            System.out.println("Its a tie. - push");
        } else if (playersHandSum > dealersHandSum) {
            System.out.println("Congratulations! You won the round!");
        } else {
            System.out.println("The dealers won the round! Care to try again?");
        }
    }

    private boolean checkForBlackjack(Hand hand) {
        if (hand.getCards().size() == 2) {
            if (hand.getHandSum() == 21) {
                System.out.println("Blackjack spotted!");
                return true;
            }
        }


        return false;
    }

}
