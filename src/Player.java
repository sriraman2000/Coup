import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private Card card1;
    private Card card2;
    public int numActiveCards;
    private int ID;
    private String name;
    private int coins;
    public Player(Card x, Card y, String playerName){
        card1 = x;
        card2 = y;
        numActiveCards = 2;
        name = playerName;
        coins = 0;
    }

    public void setID(int id) {
        ID = id;
    }

    public void collectIncome(){
        if(coins < 10) coins++;
    }

    public void collectForeignAid(){
        if (coins < 10) coins += 2;
    }

    public void makeMove(){
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        scanner.close();
    }

    public void loseCard(){
        numActiveCards--;
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();
        String short1 = card1.shortenedName;
        String short2 = card2.shortenedName;
        while(!(choice.equals(short1) || choice.equals(short2))){
            choice = scanner.nextLine().toLowerCase();
        }
        if(choice.equals(short1)){
            card1.enabled = false;
        }
        else{
            card2.enabled = false;
        }
    }
}
