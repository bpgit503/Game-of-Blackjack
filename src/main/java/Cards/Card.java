package Cards;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Card {
    private final Suits suit;
    private final Rank rank;

    public Card(Suits suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    private static Map<String, Card> cards = initCard();

    private static Map<String, Card> initCard() {
        final Map<String, Card> cache = new HashMap<>();
        for (final Suits suit : Suits.values()) {
            for (final Rank rank : Rank.values()) {
                cache.put(cardKey(suit, rank), new Card(suit, rank));
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static String cardKey(Suits suit, Rank rank) {
        return suit + " of " + rank;
    }

    public static Card getCard(Suits suit, Rank rank) {
        return cards.get(cardKey(suit, rank));
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }

    public Suits getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
}
