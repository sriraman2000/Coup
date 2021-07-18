import java.util.ArrayList;
import java.util.*;

public class Game {
    private int numPlayers;
    private int turn;
    private ArrayList<Card> deck;
    private ArrayList<Player> players;

    public Game(int numberOfPlayers){
        numPlayers = numberOfPlayers;
        turn = 0;
        initializeDeck();
        initializePlayers();
    }

    private void initializeDeck(){
        deck = new ArrayList<Card>();
        for(int i = 0; i < numPlayers; i++){
            deck.add(new Card("Ambassador"));
            deck.add(new Card("Duke"));
            deck.add(new Card("Cantessa"));
            deck.add(new Card("Assassin"));
            deck.add(new Card("Captain"));
        }
        shuffleDeck(deck);
    }
    private void shuffleDeck(ArrayList<Card> items) {
        Collections.shuffle(items);
    }
    private void initializePlayers(){
        players = new ArrayList<Player>();
        for(int i = 0; i < numPlayers; i++){
            Card first = deck.remove(deck.size() - 1);
            Card second = deck.remove(deck.size() - 1);
            players.add(new Player(first, second, Integer.toString(i)));
        }
        shufflePlayers(players);
        for(int i = 0; i < players.size(); i++){
            players.get(i).setID(i);
        }
    }
    private void shufflePlayers(ArrayList<Player> items){
        Collections.shuffle(items);
    }
}
