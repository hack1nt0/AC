package main;

import template.collection.sequence.ArrayUtils;
import template.collection.tuple.Tuple2;

import javax.management.relation.RoleUnresolved;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

/*
 ID: hackint1
 LANG: JAVA
 TASK: shopping
*/

public class Shopping {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int sn = in.nextInt();
        int[] priceOffer = new int[sn];
        List<Tuple2<Integer, Integer>>[] offers = new ArrayList[sn];
        for (int i = 0; i < sn; ++i) {
            offers[i] = new ArrayList<>();
            int cnt = in.nextInt();
            for (int j = 0; j < cnt; ++j) offers[i].add(new Tuple2<>(in.nextInt(), in.nextInt()));
            priceOffer[i] = in.nextInt();
        }
        int MAX_CODE = 1000;
        int ndiff = in.nextInt();
        int[] idxProduct = new int[ndiff];
        int[] rank = new int[MAX_CODE];
        int[] howmany = new int[MAX_CODE];
        int[] priceSingle = new int[MAX_CODE];
        int S = 0;
        for (int i = 0; i < ndiff; ++i) {
            int code = in.nextInt();
            idxProduct[i] = code;
            rank[code] = i;
            howmany[code] = in.nextInt();
            S |= howmany[code] << (i * 3);
            priceSingle[code] = in.nextInt();
        }

        int[][] minPrice = new int[S + 1][sn + 1];
        int INF = Integer.MAX_VALUE;

        for (int s = 1; s <= S; ++s) {
            int[] cnt = new int[ndiff];
            for (int i = 0; i < ndiff; ++i) {
                cnt[i] = (s >> (i * 3)) & ((1 << 3) - 1);

                minPrice[s][0] += cnt[i] * priceSingle[idxProduct[i]];
            }

            for (int offer = 0; offer < sn; ++offer) {
                minPrice[s][offer + 1] = minPrice[s][offer];
                boolean ok = true;
                for (Tuple2<Integer, Integer> o : offers[offer]) {
                    int c = o.getFirst();
                    int k = o.getSecond();
                    if (cnt[rank[c]] < k) {ok = false; break;}
                }
                if (ok) {
                    int price = priceOffer[offer];
                    int ncnt = s;
                    for (Tuple2<Integer, Integer> o : offers[offer]) {
                        int c = o.getFirst();
                        int k = o.getSecond();
                        ncnt &= ~(((1 << 3) - 1) << (rank[c] * 3));
                        ncnt |= (cnt[rank[c]] - k) << (rank[c] * 3);
                    }
                    price += minPrice[ncnt][offer + 1];
                    minPrice[s][offer + 1] = Math.min(minPrice[s][offer + 1], price);
                }
            }
        }

        out.println(minPrice[S][sn]);
    }
}
