package main;

import java.io.StringReader;

public class WindowManager {
    public String[] screen(int height, int width, String[] windows) {
        char[][] pic = new char[height][width];

        Window[] wins = new Window[windows.length];
        for (int i = 0; i < windows.length; ++i) {
            String[] tmp = windows[i].split("[ ]+");
            int tlv = Integer.parseInt(tmp[0]);
            int tlh = Integer.parseInt(tmp[1]);
            int vs = Integer.parseInt(tmp[2]);
            int hs = Integer.parseInt(tmp[3]);
            char fill = tmp[4].charAt(0);
            wins[i] = new Window(tlv, tlh, vs, hs, fill);
        }
        for (int i = 0; i < height; ++i)
            for (int j = 0; j < width; ++j) {
                pic[i][j] = ' ';
                for (int w = wins.length - 1; w >= 0; --w) {
                    char c = wins[w].charAt(j, i);
                    if (c != 0) {
                        pic[i][j] = c;
                        break;
                    }
                }
            }
        String[] ans = new String[height];
        for (int i = 0; i < height; ++i)
            ans[i] = new String(pic[i]);
        return ans;
    }


    class Window {
        int tlx, tly, brx, bry;
        char c;

        public Window(int tlv, int tlh, int vs, int hs, char fill) {
            this.c = fill;
            this.tlx = tlh;
            this.tly = tlv;
            this.brx = tlh + hs - 1;
            this.bry = tlv + vs - 1;
        }

        public char charAt(int x, int y) {
            if (tlx <= x && x <= brx && tly <= y && y <= bry) {
                if (tlx < x && x < brx && tly < y && y < bry)
                    return c;
                if (x == tlx && y == tly || x == tlx && y == bry || x == brx && y == tly || x == brx && y == bry)
                    return '+';
                return (x == tlx || x == brx) ? '|' : '-';
            } else
                return 0;
        }
    }
}
