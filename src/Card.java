public class Card {
    public boolean enabled;
    private String cardName;
    public String shortenedName;
    public Card(String cardType){
        enabled = true;
        cardName = cardType;
    }
    public void createShortName(){
        switch (cardName) {
            case "Captain" -> shortenedName = "ca";
            case "Contessa" -> shortenedName = "k";
            case "Assassin" -> shortenedName = "a";
            case "Ambassador" -> shortenedName = "q";
            case "Duke" -> shortenedName = "d";
        }
    }
}
