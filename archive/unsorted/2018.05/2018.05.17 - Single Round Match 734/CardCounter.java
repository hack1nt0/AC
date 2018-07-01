package main;

import org.omg.PortableInterceptor.INACTIVE;
import template.collection.CollectionUtils;
import template.collection.sequence.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CardCounter {
    public double winningChance(int[] _deck, int dealer, int[] _player) {
        List<Integer> pDeck = new ArrayList<>();
        for (int i = 0; i < _deck.length; ++i) {
            for (int j = 0; j < _deck[i]; ++j)
                pDeck.add(i + 1);
        }
        int player = _player[0] + _player[1];
        if (player > 21)
            return 0;
        double[] minProbWin = new double[pDeck.size() + 1];
        Arrays.fill(minProbWin, 0);
        double[][] comb = new double[pDeck.size() + 1][pDeck.size() + 1];
        for (int i = 0; i <= pDeck.size(); ++i) {
            for (int j = 0; j <= i; ++j) {
                double res = 0;
                if (i == j || j == 0)
                    res = 1;
                else if (j > 0 && i > 0) {
                    res = comb[i - 1][j] + comb[i - 1][j - 1];
                }
                comb[i][j] = res;
            }
        }
        for (int pSet = 0; pSet < 1 << pDeck.size(); ++pSet) {
            List<Integer> players = new ArrayList<>();
            players.add(_player[0]);
            players.add(_player[1]);
            for (int i = 0; i < pDeck.size(); ++i) {
                if ((pSet >> i & 1) != 0)
                    players.add(pDeck.get(i));
            }
            int pScore = score(players);
            double pWin = 0;
            if (pScore > 21 || Integer.bitCount(((1 << pDeck.size()) - 1) ^ pSet) < 1) {
                pWin = 0;
            } else {
                List<Integer> dDeck = new ArrayList<>();
                for (int i = 0; i < pDeck.size(); ++i)
                    if ((pSet >> i & 1) == 0)
                        dDeck.add(pDeck.get(i));
                boolean[] visited = new boolean[dDeck.size()];
                List<Integer> dealers = new ArrayList<>();
                List<Integer> acc = new ArrayList<>(); acc.add(dealer);
                dfs(dDeck, visited, dealers, acc);
                for (int dScore : dealers) if (dScore > 21 || dScore < pScore) pWin++;
                pWin /= dealers.size();
            }
            int pHits = Integer.bitCount(pSet);
            minProbWin[pHits] += pWin / comb[pDeck.size()][Integer.bitCount(pSet)];
        }

        double maxMin = Arrays.stream(minProbWin).max().getAsDouble();
        return maxMin;
    }

    private void dfs(List<Integer> dDeck, boolean[] visited, List<Integer> dealers, List<Integer> acc) {
        if (acc.size() >= 2) {
            if (sum(acc) >= 17 || ArrayUtils.count(visited, false) == 0) {
                dealers.add(score(acc));
                return;
            }
        }
        for (int i = 0; i < visited.length; ++i) if (!visited[i]){
            visited[i] = true;
            acc.add(dDeck.get(i));
            dfs(dDeck, visited, dealers, acc);
            acc.remove(acc.size() - 1);
            visited[i] = false;
        }
    }

    private int sum(List<Integer> list) {
        int sum = 0;
        for (int e : list)
            sum += (e == 1 ? 11 : e);
        return sum;
    }

    private int score(List<Integer> list) {
        int ans = 0;
        int aces = 0;
        for (int e : list) {
            ans += e;
            if (e == 1) aces++;
        }
        while (aces > 0 && ans + 10 <= 21)
            ans += 10;
        return ans;
    }

}
