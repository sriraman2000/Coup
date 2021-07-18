import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.*;

public class Game {
    private int numPlayers;
    private int turn;
    private ArrayList<Card> deck;
    private ArrayList<Player> players;
    private ArrayList<Player> remainingPlayers;

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
            deck.add(new Card("Contessa"));
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
            remainingPlayers.add(players.get(i));
        }
    }
    private void shufflePlayers(ArrayList<Player> items){
        Collections.shuffle(items);
    }

    private int allowBlocks(int currentTurn){
        for(int i = 0; i < remainingPlayers.size() && i != currentTurn; i++){
            Player challengingPlayer = remainingPlayers.get(i);
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().toLowerCase();
            while(!(command.equals("y")) && !(command.equals("n"))){ // make sure command is valid
                command = scanner.nextLine().toLowerCase();
            }
            if(command.equals("y")){ // challenging
                return i;
            }
        }
        return -1;
    }
    private int allowChallenges(int playerBeingChallenged, String cardType){
        Player challengedPlayer = remainingPlayers.get(playerBeingChallenged);
        for(int i = 0; i < remainingPlayers.size() && i != playerBeingChallenged; i++) {
            Player challengingPlayer = remainingPlayers.get(i);
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().toLowerCase();
            while(!(command.equals("y")) && !(command.equals("n"))){ // make sure command is valid
                command = scanner.nextLine().toLowerCase();
            }
            if(command.equals("y")){ // challenging
                StringBuilder str = new StringBuilder();
                str.append("has");
                str.append(cardType);
                try {
                    Method method = Player.class.getDeclaredMethod(str.toString());
                    Object hasCard = method.invoke(challengedPlayer);
                    if((Boolean)(hasCard)){
                        challengingPlayer.loseCard();
                        if(challengingPlayer.numActiveCards <= 0){
                            remainingPlayers.remove(i);
                        }
                        return -1;
                    }
                    else{
                        challengedPlayer.loseCard();
                        if(challengedPlayer.numActiveCards <= 0){
                            remainingPlayers.remove(playerBeingChallenged);
                        }
                        return i;
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public void start(){
        while(remainingPlayers.size() > 1){
            Player currentPlayer = remainingPlayers.get(turn);
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            command = command.toLowerCase();
            if(command.equals("i")){
                currentPlayer.collectIncome();
            }
            else if(command.equals("f")){
                int blocker = allowBlocks(turn);
                if(blocker == -1){ // no one wants to block with duke
                    currentPlayer.collectForeignAid();
                }
                else{ // block with duke
                    int challenger = allowChallenges(blocker, "Duke");
                    if(challenger != -1){ // no one successfully challenged the duck block
                        currentPlayer.collectForeignAid();
                    }
                }
            }
            currentPlayer.makeMove();
             // turn = (turn + 1) % remainingPlayers.size();
            if (turn >= remainingPlayers.size()){
                turn = 0;
            }
            else{
                turn ++;
            }
        }

    }
}
