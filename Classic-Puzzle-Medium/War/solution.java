import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Queue;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    
    private static Deque<String> player1;
    private static Deque<String> player2;
    private static int depth;
    private static Boolean DEBUG = true;

    public static void main(String args[]) {
        // Initialization
        player1 = new ArrayDeque<>();
        player2 = new ArrayDeque<>();
        int rounds = 0;
        int depth = 0;
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            String cardp1 = in.next(); // the n cards of player 1
            player1.addLast(cardp1);
            //printDebug("Pushing "+cardp1+" to player 1");
        }
        int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            String cardp2 = in.next(); // the m cards of player 2
            player2.addLast(cardp2);
            //printDebug("Pushing "+cardp2+" to player 2");
        }

        while (!player1.isEmpty() && !player2.isEmpty()){
            rounds++;
            printDebug("== Round "+rounds+" ==");
            printDebug("Player 1 has "+player1.size()+" cards, Player 2 has "+player2.size()+" cards");
            printDecks();
            
            String card1 = player1.removeFirst();
            String card2 = player2.removeFirst();
                
            // Fight
            int res = fight(card1, card2);
                
            switch(res){
                //Player 1 wins
                case 1:
                    printDebug("Player 1 won the battle, Giving cards to player 1");
                    player1.addLast(card1);
                    player1.addLast(card2);
                    break;
                        
                //Player 2 wins
                case 0:
                    printDebug("Player 2 won the battle, Giving cards to player 2");
                    player2.addLast(card1);
                    player2.addLast(card2);
                    break;
                
                // War
                case -1:
                    printDebug("Starting war");
                    
                    Deque<String> war1 = new ArrayDeque<>();
                    war1.addFirst(card1);
                    Deque<String> war2 = new ArrayDeque<>();
                    war2.addFirst(card2);
                    
                    depth++;
                    war(war1, war2);
                    depth--;
                    break;
                
                default:
                    printDebug("Invalid result value "+res+", exiting");
                    System.out.println(-1);
                    break;
            }
        }
        
        
        printDebug("Player 1 has "+player1.size()+" cards remaining");
        printDebug("Player 2 has "+player2.size()+" cards remaining");
        
        n = player1.size();
        m = player2.size();
        
        if (n > m){
            System.out.println("1 "+rounds);
        }
        else if (m > n){
            System.out.println("2 "+rounds);
        }
        else {
            System.out.println("PAT");
        }
        System.exit(0);
    }
    
    // Handles a war case
    // Takes two Deques which are the cards from each player up for grabs in the war
    private static void war(Deque<String> war1, Deque<String> war2){
        
        // Four cards are pulled from each deck
        // If a player runs out, both players are placed equally first
        if (player1.size() < 4 || player2.size() < 4){
            System.out.println("PAT");
            System.exit(0);
        }
        
        // Draw 3 war cards from each player
        for (int i = 0; i < 3; i++){
            String c1 = player1.removeFirst();
            war1.addLast(c1);
                
            String c2 = player2.removeFirst();
            war2.addLast(c2); 
        }  
        
        // Battle
        String card1 = player1.removeFirst();
        String card2 = player2.removeFirst();
        
        war1.addLast(card1);
        war2.addLast(card2);
        
        int res = fight(card1, card2);
        
        int n = war1.size();
        int m = war2.size();
        
        switch(res){
            
            //Player 1 wins
            case 1:
                printDebug("Player 1 won the war, Giving cards to Player 1");
                
                for (int i = 0; i < n; i++){
                    String c = war1.removeFirst();
                    player1.addLast(c);
                }
                
                for (int i = 0; i < m; i++){
                    String c = war2.removeFirst();
                    player1.addLast(c);
                }
                break;
            
            //Player 2 wins
            case 0:
                printDebug("Player 2 won the war, Giving cards to Player 2");

                for (int i = 0; i < n; i++){
                    String c = war1.removeFirst();
                    player2.addLast(c);
                }
                
                for (int i = 0; i < m; i++){
                    String c = war2.removeFirst();
                    player2.addLast(c);
                }
                break;
            
            //Tie
            case -1:
                printDebug("No winner, the war continues");
                
                depth++;
                war(war1, war2);
                depth--;
                break;
            
            default:
                printDebug("Invalid result value "+res+", exiting");
                System.out.println(-1);
                break;
        }
    }
    
    // Compares two cards.
    // Returns 1 if s1 is greater
    // Returns 0 if s2 is greater
    // Returns -1 if they are equal
    private static int fight(String s1, String s2){
        int i1 = cardVal(s1);
        int i2 = cardVal(s2);
        
        printDebug("Comparing cards "+s1+" and "+s2);
        
        if (i1 > i2){
            printDebug("Card 1 wins");
            return 1;
        }
        else if (i1 < i2){
            printDebug("Card 2 wins");
            return 0;
        }
        else {
            printDebug("Tie");
            return -1;
        }
    }
    
    // Returns the integer value of a card
    private static int cardVal(String s){
        if (s.length() == 3){
            s = s.substring(0,2);
        }
        else {
            s = s.substring(0,1);   
        }
        
        switch(s){
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(s);
        }
    }
    
    private static void printDecks(){
        Deque<String> p1 = new ArrayDeque<>(player1);
        Deque<String> p2 = new ArrayDeque<>(player2);
        
        String s1 = "";
        for (int i = 0; i < player1.size(); i++){
            s1 = s1 + " " + p1.removeFirst();
        }
        printDebug(s1);
        
        String s2 = "";
        for (int i = 0; i < player2.size(); i++){
            s2 = s2 + " " + p2.removeFirst();
        }
        printDebug(s2);
    }
    
    private static void printDebug(String s){
        String out = "";
        for(int i = 0; i < depth; i++){
            out += "    ";
        }
        out += s;
        System.err.println(out);
    }
}