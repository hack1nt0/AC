package main;

public class TestTaking {
    public int findMax(int questions, int guessed, int actual) {
        int ans = questions - Math.abs(guessed - actual);
        return ans;
    }
}
