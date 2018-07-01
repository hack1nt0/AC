package main;

public class CrossWord {
    public int countWords(String[] board, int size) {
        int ans = 0;
        for (String row : board) {
            String[] slots = row.split("[X]+");
            for (String slot : slots)
                if (slot.length() == size)
                    ++ans;
        }
        return ans;
    }
}
