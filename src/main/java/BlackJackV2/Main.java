package BlackJackV2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
       List<CardString> deck = new ArrayList<>();

        String[] suits = {"DIAMONDS", "HEARTS", "CLUBS", "SPADES"};
        String[] ranks = {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX",
                "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"};

        for(String suit : suits){
            for(String rank : ranks){
                deck.add(new CardString(suit, rank));
            }
        }

        System.out.println(deck);


    }
}
