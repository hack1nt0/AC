package main;

public class RGBColor {
    public int[] getComplement(int[] rgb) {
        int N = rgb.length;
        int[] comp = new int[N];
        int gray = 0;
        for (int i = 0; i < N; ++i) {
            int c = 255 - rgb[i];
            if (Math.abs(c - rgb[i]) <= 32)
                ++gray;
            comp[i] = c;
        }
        if (gray == 3) {
            for (int i = 0; i < N; ++i) {
                comp[i] = rgb[i] + 128 <= 255 ? rgb[i] + 128 : rgb[i] - 128;
            }
        }
        return comp;
    }
}
