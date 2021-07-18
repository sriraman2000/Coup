import java.util.ArrayList;

public class Player {
    private Card card1;
    private Card card2;
    private int numActiveCards;
    private int ID;
    private String name;
    public Player(Card x, Card y, String playerName){
        card1 = x;
        card2 = y;
        numActiveCards = 2;
        name = playerName;
    }

    public void setID(int id){
        ID = id;
    }
}
