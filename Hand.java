//********************************************************************
//  Hand.java       Author: Thu Phuong Hoang & Nadine Mendis
//  This class represents a hand of cards.
//********************************************************************
import java.util.*;

public class Hand {
  private List<Card> cards;

  public Hand() {
    cards = new ArrayList<>();
  }

  public void addCard(Card card) {
    cards.add(card);
  }

  public int getBlackjackValue() {
    int value = 0;
    int numAces = 0;

    for (Card card : cards) {
      if (card.getFace().equals("A")) {
        numAces++;
      }
      else {
        value += card.getValue();
      }
    }

    for (int i = 0; i < numAces; i++) {
      if (value + 11 <= 21){
        value = value + 11;
      }
      else {
        value = value + 1;
      }
    }
    return value;
  }

  public Card getCard(int index) {
    if (index >= 0 && index < cards.size()) {
      return cards.get(index);
    } else {
      return null; // Return null if the index is out of bounds
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(); // Create a StringBuilder object to build the string representation of the hand

    for (Card card : cards) {
      sb.append(card).append(" "); // Append each card to the StringBuilder with a space separator
    }

    return sb.toString(); 
  }
}
