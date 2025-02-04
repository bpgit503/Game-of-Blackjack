package Blackjack;

import Cards.Deck;
import Cards.Player;

import java.util.Scanner;

public class BlackjackDriver {
    private Deck deck;
    private Player player;
    private Player dealer;
    private final Scanner scanner;

    public BlackjackDriver(String name, double initialBalance) {
        deck = new Deck();
        player = new Player(name, initialBalance, false);
        dealer = new Player("Dealer", 0, true);
        scanner = new Scanner(System.in);

    }

    public void startGame() {
        boolean gameLoop = true;

        System.out.println("Welcome to Blackjack!\n\n");
        while (gameLoop) {
            if (player.getBalance() == 0) {
                System.out.println("You have lost all your chips. Come back with more");
                break;
            }

            System.out.println("Place your bet!");
            double bet = scanner.nextDouble();
            boolean canBet = player.placeBet(bet);
            while (!canBet) {
                System.out.println("Please bet a valid amount.\nYou have $" + player.getBalance());
                canBet = player.placeBet(scanner.nextDouble());
            }
            scanner.nextLine();

            System.out.println("The dealer is dealing");

            player.getHand().addCard(deck.deal().orElseThrow());
            dealer.getHand().addCard(deck.deal().orElseThrow());
            player.getHand().addCard(deck.deal().orElseThrow());
            dealer.getHand().addCard(deck.deal().orElseThrow());


            System.out.println("The dealer has dealt the cards.");


            System.out.println("\ndealers hand: " + dealer.getHand().getCard(1));
            System.out.println("\nYour hand: " + player.getHand().getCards());


            if (player.getHand().isBlackjack() && dealer.getHand().isBlackjack()) {

                System.out.println("Both the player and the dealer have hit a natural Blackjack! Its a tie!");

                System.out.println("Dealers hand: " + dealer.getHand().getCards());
                System.out.println("\n" + player.getName() + "hand: " + player.getHand().getCards());
                player.pushBet();

            } else if (player.getHand().isBlackjack()) {
                System.out.println("You hit a natural Blackjack! You Win!");
                player.winBlackjack();

            } else if (dealer.getHand().isBlackjack()) {
                System.out.println("The dealer has hit a Natural Blackjack! Dealer wins!");
                player.loseBet();

            } else {
                playerTurn();
                dealerTurn();
                determineWinner();
                if (gameLoop = playAgain()) {
                    deck.reshuffle();
                    player.resetHand();
                    dealer.resetHand();
                }
            }
        }
    }

    private boolean playAgain() {
        System.out.println("Care for another round? yes(1) or no(2)");
        String answer = scanner.nextLine();
        if (answer.equals("1") || answer.equals("yes")) {
            return true;
        } else {
            return false;
        }
    }


    private void playerTurn() {
        System.out.println("\nIt is your move: Hit(1) or Stand(2):");
        String input = scanner.nextLine();
        while (true) {
            if (input.equals("1") || input.equalsIgnoreCase("hit")) {

                player.getHand().addCard(deck.deal().orElseThrow());

                System.out.println("You hit: " + player.getHand().getCards()
                        + "\nYour hand sum: " + player.getHand().getHandSum());

                if (player.getHand().isBust()) {

                    System.out.println("It's a bust! You lost the round\n");
                    System.out.println("Your hand" + player.getHand().getCards() + "\nDealers Hand" + dealer.getHand().getCards());
                    player.loseBet();
                    break;

                } else {

                    System.out.println("\nIt is your move: Hit(1) or Stand(2):");
                    input = scanner.nextLine();

                }
            } else {
                System.out.println("You stand with: " + player.getHand().getCards()
                        + "\nYour hand sum: " + player.getHand().getHandSum());
                break;
            }
        }
    }

    private void dealerTurn() {
        if (player.getHand().isBust()) return;

        System.out.println("The dealer reveals his hand");
        System.out.println("\ndealers hand: " + dealer.getHand().getCards());
        while (dealer.getHand().getHandSum() < 17) {

            dealer.getHand().addCard(deck.deal().orElseThrow());
            System.out.println("Dealer hits: " + dealer.getHand().getCards()
                    + "\nDealer's hand sum: " + dealer.getHand().getHandSum());


            if (dealer.getHand().isBust()) {
                System.out.println("It's a bust! The Dealer lost the round\n");
                player.winBet();
                return;
            }
        }

        System.out.println("Dealer stands with: " + dealer.getHand().getHandSum() + "\n");

    }

    private void determineWinner() {
        if (player.getHand().isBust()) return;
        if (dealer.getHand().isBust()) return;

        int playerSum = player.getHand().getHandSum();
        int dealerSum = dealer.getHand().getHandSum();

        if (playerSum > dealerSum) {
            System.out.println("Player wins with " + playerSum + " against dealer's " + dealerSum + "!");
            player.winBet();
        } else if (dealerSum > playerSum) {
            System.out.println("Dealer wins with " + dealerSum + " against player's " + playerSum + "!");
            player.loseBet();
        } else {
            System.out.println("It's a tie! Both have " + playerSum + ".");
            player.pushBet();
        }
    }
}
