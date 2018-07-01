package main;

//Near to time limit...
public class MajoritySubarray {
    public long getCount(int n, int seed, int m) {
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = (int) (((long) seed / (1 << 16)) % m);
            seed = (int) (((long) seed * 1103515245 + 12345) % (1L << 31));
        }
        long ans = 0;
        int[] psum = new int[n];
        int[] some = new int[n];
        BST.BST1 bst = new BST.BST1(n);
        for (int d = 0; d < m; ++d) {
            bst.clear();
            bst.insert(0);
            for (int i = 0; i < n; ++i) {
                psum[i] = 0;
                if (i > 0) psum[i] += psum[i - 1];
                if (array[i] == d)
                    psum[i]++;
                some[i] = 2 * psum[i] - i - 1;
                int delta = bst.nLessThan(some[i]);
                ans += delta;
                bst.insert(some[i]);
//                System.out.println(bst.height[bst.ROOT] + " " + (i + 1) + " " + n);
            }
//            System.out.println(d + " " + Arrays.toString(some) + " " + ans);
        }
        return ans;
    }

}
