package Blackjack;

import Cards.*;

import java.util.Scanner;


//reshullfe fucnion in deck
//bonus add chips the game. Make it as close to bj as possible
public class BlackjackDriver {
    private Deck deck;
    private Hand playersHand;
    private Hand dealersHand;

    public BlackjackDriver() {
        deck = new Deck();
        playersHand = new Hand();
        dealersHand = new Hand();
    }


    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Blackjack!");
        System.out.println("The dealer is dealing");

        playersHand.addCard(deck.deal().orElseThrow());
        dealersHand.addCard(deck.deal().orElseThrow());
        playersHand.addCard(deck.deal().orElseThrow());
        dealersHand.addCard(deck.deal().orElseThrow());


        System.out.println("The dealer has dealt the cards.");

        boolean endOfTurn = false;

        while (!endOfTurn) {

            System.out.println("\ndealers hand: " + dealersHand.getCard(1));
            System.out.println("\nYour hand: " + playersHand.getCards());


            if (playersHand.isBlackjack() && dealersHand.isBlackjack()) {

                System.out.println("Both the player and the dealer have hit a natural Blackjack! Its a tie!");

                System.out.println("Dealers hand: " + dealersHand.getCards());
                System.out.println("\nYour hand: " + playersHand.getCards());
                break;

            } else if (playersHand.isBlackjack()) {
                System.out.println("You hit a natural Blackjack! You Win!");

            } else if (dealersHand.isBlackjack()) {
                System.out.println("The dealer has hit a Natural Blackjack! Dealer wins!");

            } else {
                playerTurn();
                delaerTurn();
                determineWinner();

                //method to check for over 21 points bust
                System.out.println("\n It is your move: Hit(1) or Stand(2):");
                String input = scanner.nextLine();

                if (input.equals("1") || input.equalsIgnoreCase("hit")) {

                    playersHand.addCard(deck.deal().get());

                    endOfTurn = checkForBust(playersHand);
                    if (endOfTurn) {
                        System.out.println("You lost the round");
                        System.out.println("\n Your hand: " + playersHand.getCards());
                        break;
                    }

                } else if (input.equals("2") || input.equalsIgnoreCase("stand")) {
                    System.out.println("The dealer reveals his hand");
                    System.out.println("\ndealers hand: " + dealersHand.getCards());

                    while (dealersHand.getHandSum() < 17) {

                        dealersHand.addCard(deck.deal().get());

                        if (checkForBust(dealersHand)) {
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

    }

    private void determineWinner() {

    }

    private void delaerTurn() {

    }

    private void playerTurn() {

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
