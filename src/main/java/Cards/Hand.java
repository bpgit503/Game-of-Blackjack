package Cards;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        return this.cards.get(index);
    }

    public Card revealDealerHiddenCard() {
        return cards.get(0);
    }

    public int getHandSum() {
        int sum = 0;
        int aceCount = 0;
        for (Card card : cards) {
            sum += card.getRank().getRankValue();
            if (card.getRank() == Rank.ACE) {
                aceCount++;
            }
        }

        while (sum > 21 && aceCount > 0) {
            sum -= 10;
            aceCount--;
        }


        return sum;
    }

}
