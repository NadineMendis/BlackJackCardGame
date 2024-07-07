//********************************************************************
//  Blackjack.java       Author: Thu Phuong Hoang & Nadine Mendis
//  This is the driver class of the program
//********************************************************************
import java.util.*;

public class Blackjack {
  public static void main(String[] args) {
    Deck deck = new Deck();

    Scanner sc = new Scanner(System.in);

    //Instructions
    System.out.println("====================================BLACKJACK====================================\n" +
                       "- Compare your total hand value with the dealers hand.\n" +
                       "- The player or the dealer with 21 wins immediately.\n"+
                       "- The player or the dealer with more than 21 loses immediately.\n"+
                       "- If both have lower than 21, compare the totals and the one with high value wins\n" +
                       "=================================================================================");

    // Player name
    System.out.println();
    System.out.print("Enter players name: ");
    String playerName = sc.nextLine();
    System.out.println("Welcome " + playerName + "!");
    System.out.println();

    deck.shuffle();
    deck.showAllCards();
    System.out.println();

    String answer;

    do {
        // Place a bet
        double betAmount;
        do {
            try{
              System.out.print("Please place your bets: $");
              betAmount = sc.nextDouble();
              if (betAmount <= 0) {
                  System.out.println("Please enter a positive value greater than 0");
              }
            } catch (Exception e) {
              System.out.println("Invalid input. Please enter a valid numeric value.");
              sc.nextLine();
              betAmount = 0;
            }
        } while (betAmount <= 0);

        double winnings = betAmount * 2;

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        // Initial deal
        playerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        playerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());

        System.out.println(playerName + "'s Hand: " + playerHand);
        System.out.println("Dealer's Hand: " + dealerHand.getCard(0) + " xx\n"); // Display the dealer's hand (showing only one card)
        System.out.println(playerName + "'s Score: " + playerHand.getBlackjackValue());
        System.out.println("Dealer's Score (with card facing up): " + dealerHand.getCard(0).getValue());
        //Checks if the player has a total score of 21
        if (playerHand.getBlackjackValue() == 21) {
          System.out.println();
          System.out.println(playerName + " has 21!");
          System.out.println("Congratulations, " + playerName + "! You win $" + winnings);
        }
        else {
          // Player's turn
          while (true) {
            System.out.print("Hit or Stand? (H/S): ");
            String input = sc.next();

            if (input.equalsIgnoreCase("H")) {
              playerHand.addCard(deck.dealCard());
              System.out.println(playerName + "'s Hand: " + playerHand);
              System.out.println(playerName + "'s Score: " + playerHand.getBlackjackValue());
              System.out.println();

              if (playerHand.getBlackjackValue() > 21) {
                System.out.println(playerName + " busts! Dealer wins. You lose $" + betAmount);
                break;
              }
              else if (playerHand.getBlackjackValue() == 21) {
                System.out.println(playerName + " has 21!");
                System.out.println("Congratulations, " + playerName + "! You win $" + winnings);
                break;
              }
            }
            else if (input.equalsIgnoreCase("S")) {
              // Reveal the dealer's hidden card
              System.out.println("Dealer's Hand: " + dealerHand);
              System.out.println("Dealer's Score: " + dealerHand.getBlackjackValue());
              System.out.println();

              if (dealerHand.getBlackjackValue() == 21) {
                System.out.println("Dealer has 21! Dealer wins. You lose $" + betAmount);
                break;
              }
              else {
                // Dealer's turn
                while (dealerHand.getBlackjackValue() <= 16) { // Dealer hits as long as the score is 16 or less
                  dealerHand.addCard(deck.dealCard());
                }

                System.out.println("Dealer's Hand: " + dealerHand); // Display the dealer's final hand
                System.out.println("Dealer's Score: " + dealerHand.getBlackjackValue()); // Display the dealer's final score
                System.out.println();

                if (dealerHand.getBlackjackValue() > 21) {
                  System.out.println("Congratulations, " + playerName + "! You win $" + winnings);
                  break;
                }
                else if (dealerHand.getBlackjackValue() == 21) {
                  System.out.println("Dealer has 21! Dealer wins. You lose $" + betAmount);
                  break;
                }
                else {
                  // Compare hands
                  int playerValue = playerHand.getBlackjackValue();
                  int dealerValue = dealerHand.getBlackjackValue();

                  System.out.println(playerName + "'s Score: " + playerValue); // Display the player's final score
                  System.out.println("Dealer's Score: " + dealerValue); // Display the dealer's final score
                  System.out.println();

                  if (playerValue > dealerValue && playerValue < 21) {
                    System.out.println("Congratulations, " + playerName + "! You win $" + winnings);
                    break;
                  }
                  else if (playerValue < dealerValue && dealerValue < 21) {
                    System.out.println("Dealer wins! You lose $" + betAmount);
                    break;
                  }
                  else if (playerValue == dealerValue && dealerValue < 21) {
                    System.out.println("It's a tie!");
                    break;
                  }
                }
              }
            }
            else {
              // If the player enters the wrong command.
              System.out.println("Incorrect choice. Type H for hit or S for stand.");
            }
          }
        }
        System.out.println("Would you like to continue playing (Y/N)?");
        System.out.print("Enter your choice: ");
        answer = sc.next();
        System.out.println();
      }
    while (answer.equalsIgnoreCase("Y"));
    System.out.println("Good bye!");
  }
}
