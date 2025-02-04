package Blackjack;

import Cards.*;

import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;


//reshullfe fucnion in deck
//bonus add chips the game. Make it as close to bj as possible
public class BlackjackDriver {
    private Deck deck;
    private Hand playersHand;
    private Hand dealersHand;
    private final Scanner scanner;

    public BlackjackDriver() {
        deck = new Deck();
        playersHand = new Hand();
        dealersHand = new Hand();
        scanner = new Scanner(System.in);
    }

    public void testRun() {
        Hand dealerHand = new Hand();

        dealerHand.addCard(new Card(Suits.HEARTS, Rank.SEVEN));
        dealerHand.addCard(new Card(Suits.SPADES, Rank.FOUR));
        dealerHand.addCard(new Card(Suits.CLUBS, Rank.ACE));

        System.out.println("Dealer's hand: " + dealerHand.getCards());
        System.out.println("Dealer's hand sum: " + dealerHand.getHandSum());

        if (dealerHand.isBust()) {
            System.out.println("Dealer busts!");
        } else {
            System.out.println("Dealer is safe with sum: " + dealerHand.getHandSum());
        }

    }


    public void startGame() {

        System.out.println("Welcome to Blackjack!");
        System.out.println("The dealer is dealing");

        playersHand.addCard(deck.deal().orElseThrow());
        dealersHand.addCard(deck.deal().orElseThrow());
        playersHand.addCard(deck.deal().orElseThrow());
        dealersHand.addCard(deck.deal().orElseThrow());


        System.out.println("The dealer has dealt the cards.");





            System.out.println("\ndealers hand: " + dealersHand.getCard(1));
            System.out.println("\nYour hand: " + playersHand.getCards());


            if (playersHand.isBlackjack() && dealersHand.isBlackjack()) {

                System.out.println("Both the player and the dealer have hit a natural Blackjack! Its a tie!");

                System.out.println("Dealers hand: " + dealersHand.getCards());
                System.out.println("\nYour hand: " + playersHand.getCards());

            } else if (playersHand.isBlackjack()) {
                System.out.println("You hit a natural Blackjack! You Win!");

            } else if (dealersHand.isBlackjack()) {
                System.out.println("The dealer has hit a Natural Blackjack! Dealer wins!");

            } else {
                playerTurn();
                dealerTurn();
                determineWinner();
            }
        }


    private void playerTurn() {
        System.out.println("\nIt is your move: Hit(1) or Stand(2):");
        String input = scanner.nextLine();
        while (true) {
            if (input.equals("1") || input.equalsIgnoreCase("hit")) {

                playersHand.addCard(deck.deal().orElseThrow());

                System.out.println("You hit: " + playersHand.getCards()
                        + "\nYour hand sum: " + playersHand.getHandSum());

                if (playersHand.isBust()) {

                    System.out.println("It's a bust! You lost the round\n");
                    System.out.println("Your hand" + playersHand.getCards() + "\nDealers Hand" + dealersHand.getCards());
                    break;

                } else{

                    System.out.println("\nIt is your move: Hit(1) or Stand(2):");
                    input = scanner.nextLine();

                }
            }else {
                System.out.println("You stand with: " + playersHand.getCards()
                        + "\nYour hand sum: " + playersHand.getHandSum());
                break;
            }
        }
    }

    private void dealerTurn() {
        if (playersHand.isBust()) return;

        System.out.println("The dealer reveals his hand");
        System.out.println("\ndealers hand: " + dealersHand.getCards());
        while (dealersHand.getHandSum() < 17) {

            dealersHand.addCard(deck.deal().orElseThrow());
            System.out.println("Dealer hits: " + dealersHand.getCards()
                    + "\nDealer's hand sum: " + dealersHand.getHandSum());


            if (dealersHand.isBust()) {
                System.out.println("It's a bust! The Dealer lost the round");
                return;
            }
        }

        System.out.println("Dealer stands with: " + dealersHand.getHandSum());

    }

    private void determineWinner() {
        if (playersHand.isBust()) return;
        if (dealersHand.isBust()) return;

        int playerSum = playersHand.getHandSum();
        int dealerSum = dealersHand.getHandSum();

        if (playerSum > dealerSum) {
            System.out.println("Player wins with " + playerSum + " against dealer's " + dealerSum + "!");
        } else if (dealerSum > playerSum) {
            System.out.println("Dealer wins with " + dealerSum + " against player's " + playerSum + "!");
        } else {
            System.out.println("It's a tie! Both have " + playerSum + ".");
        }
    }
}
