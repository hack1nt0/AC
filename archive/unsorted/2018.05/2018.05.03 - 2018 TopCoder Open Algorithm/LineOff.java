package main;

public class LineOff {
    public int movesToDo(String points) {
        int ans = 0;
        while (true) {
            boolean found = false;
            for (int i = 0; i < points.length() - 1; ++i) {
                if (points.charAt(i) == points.charAt(i + 1)) {
                    ++ans;
                    found = true;
                    points = points.substring(0, i) + points.substring(i + 2);
                    break;
                }
            }
            if (!found)
                break;
        }
        return ans;
    }
}
