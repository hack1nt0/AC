package main;

public class Cyberline {
    public String lastCyberword(String cyberline) {
        String[] words = cyberline.split("[^0-9a-zA-Z@-]+");
        for (int i = words.length - 1; i >= 0; --i) {
            String w = words[i];
            if (w.replaceAll("-", "").length() == 0)
                continue;
            return w.replaceAll("-", "");
        }
        throw new RuntimeException();
    }
}
