package main;

public class ClockWalk {
    public int finalPosition(String flips) {
        int pointer = 0;
        for (int i = 0; i < flips.length(); ++i) {
            char c = flips.charAt(i);
            int step = i + 1;
            if (c == 'h')
                pointer = (pointer + step) % 12;
            else
                pointer = (pointer - step + 12) % 12;
        }
        return pointer == 0 ? 12 : pointer;
    }
}
