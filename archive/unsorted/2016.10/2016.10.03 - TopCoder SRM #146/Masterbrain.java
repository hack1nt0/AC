package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Masterbrain {
    public int possibleSecrets(String[] guesses, String[] results) {
        int N = guesses.length;
        int ans = 0;
        for (int a = 1; a <= 7; ++a)
            for (int b = 1; b <= 7; ++b)
                for (int c = 1; c <= 7; ++c)
                    for (int d = 1; d <= 7; ++d) {
                        String secret = a + "" + b + "" + c + "" + d;

                        int fn = 0;
                        for (int i = 0; i < guesses.length; ++i) {
                            int B = 0, W = 0;
                            boolean[] visSecret = new boolean[secret.length()];
                            boolean[] visGuess = new boolean[guesses[i].length()];
                            for (int k = 0; k < guesses[i].length(); ++k) {
                                if (guesses[i].charAt(k) == secret.charAt(k)) {
                                    visGuess[k] = true;
                                    visSecret[k] = true;
                                    B++;
                                    continue;
                                }
                            }
                            for (int k = 0; k < guesses[i].length(); ++k) {
                                if (visGuess[k]) continue;
                                for (int l = 0; l < secret.length(); ++l) {
                                    if (visSecret[l]) continue;
                                    if (secret.charAt(l) == guesses[i].charAt(k)) {
                                        visSecret[l] = true;
                                        W++;
                                        break;
                                    }
                                }
                            }
                            String jR = B + "b " + W + "w";
                            if (!jR.equals(results[i])) {
                                fn++;
                            }
                        }
                        if (fn == 1) {
                            ans++;
                        }
                    }

        return ans;
    }

}
