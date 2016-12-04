package main;

import java.util.Arrays;

public class TriangleMaking {
    public int maxPerimeter(int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        Arrays.sort(arr);
        arr[2] = Math.min(arr[2], arr[0] + arr[1] - 1);
        int ans = arr[0] + arr[1] + arr[2];
        return ans;
    }
}
