package main;

public class CardCount {
    public String[] dealHands(int numPlayers, String deck) {
        String[] result = new String[numPlayers];
        for (int i = 0; i < numPlayers; ++i) result[i] = "";
        for (int i = 0; i < deck.length() / numPlayers * numPlayers; ++i) {
            result[i % numPlayers] += deck.charAt(i);
        }
        return result;
    }
}
