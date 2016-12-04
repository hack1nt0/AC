package main;

public class MergeSort {
    private int[] num;
    private int[] tmp;

    public int howManyComparisons(int[] numbers) {
        num = numbers;
        tmp = new int[numbers.length];
        int ans = mergeSort(numbers, 0, numbers.length);
        return ans;
    }

    private int mergeSort(int[] num, int L, int R) {
        if (L + 1 >= R) return 0;
        int mid = L + (R - L) / 2;
        int res = mergeSort(num, L, mid) + mergeSort(num, mid, R);
        int i = L, j = mid, k = L;
        while (i < mid && j < R) {
            res++;
            if (num[i] < num[j]) tmp[k++] = num[i++];
            else if (num[i] > num[j]) tmp[k++] = num[j++];
            else if (num[i] == num[j]) {
                tmp[k++] = num[i++];
                tmp[k++] = num[j++];
            }
        }
        if (i < mid) for (int ii = i; ii < mid; ++ii) tmp[k++] = num[ii];
        if (j < R) for (int ii = j; ii < R; ++ii) tmp[k++] = num[ii];
        for (int ii = L; ii < R; ++ii) num[ii] = tmp[ii];
        return res;
    }
}
