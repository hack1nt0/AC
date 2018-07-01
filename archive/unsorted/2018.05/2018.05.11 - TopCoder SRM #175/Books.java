package main;

import template.collection.Sorter;
import template.collection.sequence.ArrayUtils;
import template.misc.IntComparator;

//WA
public class Books {
    class Book {
        String title;
        int rank;

        public Book(String title, int rank) {
            this.title = title;
            this.rank = rank;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Book book = (Book) o;

            return rank == book.rank;
        }

        @Override
        public int hashCode() {
            return rank;
        }
    }

    public int sortMoves(String[] titles) {
        int N = titles.length;
        int[] order = ArrayUtils.index(N);
        Sorter.sort(order, new IntComparator() {
            @Override
            public int compare(int a, int b) {
                int cmp = titles[a].compareTo(titles[b]);
                return cmp != 0 ? cmp : a - b;
            }
        });
        int[] rank = new int[N];
        for (int i = 0; i < N; ++i) rank[order[i]] = i;
        Book[] books = new Book[N];
        for (int i = 0; i < N; ++i) books[i] = new Book(titles[i], rank[i]);
        int ans = 0;
        while (true) {
            boolean found = false;
            int max = -1, which = -1;
            for (int i = 0; i < N; ++i) {
                if (books[i].rank == i)
                    continue;
                found = true;
                if (books[i].rank > max) {
                    max = books[i].rank;
                    which = i;
                }
                if (i == max) {
                    Book swap = books[which];
                    for (int j = which; j < i; ++j)
                        books[j] = books[j + 1];
                    books[i] = swap;
                    ++ans;
                    break;
                }
            }
            if (!found)
                break;
        }
        return ans;
    }
}
