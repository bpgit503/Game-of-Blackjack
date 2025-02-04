import Blackjack.BlackjackDriver;

public class BlackjackApp {
    public static void main(String[] args) {
        BlackjackDriver driver = new BlackjackDriver("Player", 1000);
        driver.startGame();
    }
}
