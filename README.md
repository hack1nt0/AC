# AC(Hope my codes t be ACcepted!)

算法的比赛记录和总结。

## 常用的优化技巧

$O(lgN)$      | $O(N)$       | $O(N\sqrt N)$             | $O(NlgN)$          | $O(2^{N/2})$
------------- | ------------ | ------------------------- | ------------------ | ------------------
Binary Search | Two Pointers | Square Root Decomposition | Divide and Conquer | Meet-in-the-middle
-             | TreeDp       | -                         | -                  | -

## 网络流的复杂度

$O(N^2M)$ | $O(FM)$
--------- | -------------
Max Flow  | Min Cost Flow

## 一些常用对象和求解类型的复杂度

Target \ Object | Subsets of Subsets | Spanning Trees                              | Cliques       | Graphs
--------------- | ------------------ | ------------------------------------------- | ------------- | ------
Enumeration     | $O(3^N)$           | $O(2^NM)$                                   | $O(N2^N)$     | ?
Counting        | $O(N2^N)$?         | Dp on $O(2^NM)$ or Matrix-tree on $O(N^3)$? | $O(N2^{N/2})$ | ?
Optimization    | $O(N2^N)$          | $O(NlgM)$                                   | $O(N2^{N/2})$ | ?
SAT             | $O(N2^N)$          | $O(2^NM)$, Dp on $O(2^N)$                   | $O(N2^{N/2})$ | ?
