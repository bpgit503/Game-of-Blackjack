package BlackJackV2;

public class CardString {
    private final String suit;
    private final String rank;

    public CardString(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "{" +
                "suit='" + suit + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
