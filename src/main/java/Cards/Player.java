package Cards;

import lombok.Data;

@Data
public class Player {
    private String name;
    private Hand hand;
    private double balance;
    private double currentBet;
    private boolean isDealer;

    public Player(String name, double balance, boolean isDealer) {
        this.name = name;
        this.balance = balance;
        this.isDealer = isDealer;
        this.hand = new Hand();
    }

    public boolean placeBet(double amount) {
        if (balance == 0) {
            System.out.println("You don't have enough money to bet with");
            return false;
        }
        if (amount > balance) {
            System.out.println(name + " does not have enough to bet $" + amount);
            return false;
        }

        this.currentBet += amount;
        this.balance -= amount;
        System.out.println(name + " placed a bet of $" + amount);
        return true;
    }

    public void winBet() {
        this.balance += currentBet * 2;
        System.out.println(name + "wins and now has $" + balance);
    }

    public void winBlackjack() {
        this.balance += currentBet * 2.5;
        System.out.println(name + "wins with Blackjack and now has $" + balance);
    }

    public void loseBet() {
        System.out.println(name + " loses the bet. Balance is now $" + balance);
    }

    //push (tie) : returns bet
    public void pushBet() {
        this.balance += currentBet;
        System.out.println(name = "pushes the bet, Balance restored to $" + balance);
    }

    public void resetHand() {
        this.hand = new Hand();
    }


}
