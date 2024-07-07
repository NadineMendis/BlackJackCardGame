//********************************************************************
//  Deck.java       Author: Thu Phuong Hoang & Nadine Mendis
//  This class represents a deck of cards.
//********************************************************************
import java.util.*;

public class Deck {
    private List<Card> cards;
    private int nextCard;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"S:", "H:", "D:", "C:"};
        String[] faces = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Generate all possible card combinations by iterating over suits and faces
        for (String suit : suits) {
            for (String face : faces) {
                cards.add(new Card(suit, face));
            }
        }

        nextCard = 0;
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int j = (int) (Math.random() * (cards.size() - i));
            Card tmp = cards.get(i);
            cards.set(i, cards.get(i + j));
            cards.set(i + j, tmp);
        }
    }

    public void showAllCards() {
        System.out.println("A deck of 52 cards in random order:");
        System.out.println();
        for (int i = 0; i < cards.size(); i++) {
            System.out.print(cards.get(i) + " ");
            if ((i + 1) % 13 == 0) {
                System.out.println();
            }
        }
    }

    public Card dealCard() {
        if (nextCard < cards.size()) {
            return cards.get(nextCard++);
        } else {
            return null; // Return null if all cards have been dealt
        }
    }
}
