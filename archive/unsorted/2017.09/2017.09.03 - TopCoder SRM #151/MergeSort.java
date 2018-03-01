package main;

public class MergeSort {
    int comparisons = 0;
    int[] tmp;
    public int howManyComparisons(int[] numbers) {
        tmp = new int[numbers.length];
        mergeSort(numbers, 0, numbers.length);
        return comparisons;
    }

    private void mergeSort(int[] d, int from, int to) {
        if (to - from <= 1) return;
        int mid = from + (to - from) / 2;
        mergeSort(d, from, mid);
        mergeSort(d, mid, to);
        int i = from;
        int j = mid;
        int k = from;
        while (i < mid && j < to) {
            comparisons++;
            if (d[i] < d[j])
                tmp[k++] = d[i++];
            else if (d[i] > d[j])
                tmp[k++] = d[j++];
            else if (d[i] == d[j]) {
                tmp[k++] = d[i++];
                tmp[k++] = d[j++];
            }
        }
        while (i < mid) tmp[k++] = d[i++];
        while (j < to)  tmp[k++] = d[j++];
        System.arraycopy(tmp, from, d, from, to - from);
    }
}
