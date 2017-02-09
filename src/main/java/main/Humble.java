package main;

import template.collection.sequence.ArrayUtils;
import template.numbers.IntUtils;

import java.util.*;
import java.io.PrintWriter;
/*
 ID: hackint1
 LANG: JAVA
 TASK: humble
*/
public class Humble {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int k = in.nextInt();
        int n = in.nextInt();
        int[] primes = new int[k];
        for (int i = 0; i < k; ++i) primes[i] = in.nextInt();
        ArrayUtils.mergeSort(primes);
//        Set<Long> humbleSet = new HashSet<>();
//        IntBinaryHeap<Long> humbleHeap = new IntBinaryHeap<>(Collections.reverseOrder());
//        for (int i = 0; i < primes.length; ++i) {
//            int cnt = 0;
//            int mul = 0;
//            while (true) {
//                if (cnt >= n) break;
//
//                long humble = primes[i] * mul++;
//                if (humbleHeap.size() == n && humble >= humbleHeap.min()) break;
//
//                long tmp = humble;
//                for (int factor : primes) {
//                    if (factor > tmp) break;
//                    while (tmp % factor == 0) tmp /= factor;
//                }
//                if (tmp != 1) continue;
//                cnt++;
//
//                //System.err.println(humble);
//                if (humbleSet.contains(humble)) continue;
//                if (humbleHeap.size() < n) {
//                    humbleSet.add(humble);
//                    humbleHeap.add(humble);
//                    continue;
//                }
//                if (humbleHeap.size() == n && humbleHeap.min() > humble) {
//                    humbleSet.remove(humbleHeap.pop());
//                    humbleHeap.add(humble);
//                    humbleSet.add(humble);
//                }
//            }
//        }
//        //ArrayUtils.printlnConcisely(humbleHeap, out, 100);
//        out.println(humbleHeap.min());

        List<Long> humbles = new ArrayList<>();
        humbles.add(1L);
//        while (humbles.size() <= n + 1) {
//            int next = Integer.MAX_VALUE;
//            for (int prime : primes) {
//                int whichToMul = (humbles.get(humbles.size() - 1) + prime) / prime;
//                int whichIndex = ArrayUtils.upperBound(humbles, whichToMul);
//                if (whichIndex > 0 && humbles.get(whichIndex - 1) == whichToMul) whichIndex--;
//                whichToMul = humbles.get(whichIndex);
//                int cand = whichToMul * prime;
//                next = Math.min(next, cand);
//            }
//            //System.err.println(next);
//            humbles.add(next);
//        }

        int[] humblesIndex = new int[primes.length];
        while (humbles.size() <= n + 1) {
            long next = Long.MAX_VALUE;
            for (int primeIndex = 0; primeIndex < primes.length; ++primeIndex) {
                long cand = -1;
                while (true) {
                    cand = humbles.get(humblesIndex[primeIndex]) * primes[primeIndex];
                    if (cand > humbles.get(humbles.size() - 1)) break;
                    humblesIndex[primeIndex]++;
                }
                next = Math.min(next, cand);
            }
            humbles.add(next);
        }

        out.println(humbles.get(n));
    }

    public static void main(String[] args) {
        ArrayUtils.printlnConcisely(IntUtils.primes(545), new PrintWriter(System.err), 100);
    }
}
