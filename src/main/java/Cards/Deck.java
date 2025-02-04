package Cards;

import java.util.Collections;
import java.util.Optional;
import java.util.Stack;

public class Deck {
    private final Stack<Card> deckOfCards;

    public Deck() {
        this.deckOfCards = initDeck();
    }

    private Stack<Card> initDeck() {
        final Stack<Card> deckOfCards = new Stack<>();
        for (final Suits suit : Suits.values()) {
            for (final Rank rank : Rank.values()) {
                deckOfCards.push(Card.getCard(suit, rank));
            }
        }
        Collections.shuffle(deckOfCards);
        return deckOfCards;
    }

    public Optional<Card> deal() {
        return this.deckOfCards.isEmpty() ? Optional.empty() : Optional.of(this.deckOfCards.pop());
    }

    public void reshuffle() {
        this.deckOfCards.clear();
        this.deckOfCards.addAll(initDeck());
    }

}
