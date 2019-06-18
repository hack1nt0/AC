#include <bits/stdc++.h>
using namespace std;

template <typename T>
struct segtree {
	int n;
	vector<T> xs;
	vector<T> lazy;
	segtree(const vector<int>& _xs) {
		n = 1;
		int _n = _xs.size();
		while (n < _n) n <<= 1;
		xs.resize(n * 2, 0);
		for (int i = 0; i < _n; ++i) xs[i + n - 1] = _xs[i];
		for (int i = n - 1; i >= 0; --i) xs[i] = xs[L(i)] + xs[R(i)];
		lazy.resize(n * 2, 0);
	}
	inline int L(int i) { return (i << 1) + 1; }
	inline int R(int i) { return (i << 1) + 2; }
	T min(int ql, int qr) { return min(ql, qr, 0, n, 0); }
	T min(int ql, int qr, int l, int r, int i) {
	}
	T upd(int ql, int qr, T x) { upd(ql, qr, x, 0, n, 0); }
	T upd(int ql, int qr, T x, int l, int r, int i) {
	}
	T add(int ql, int qr, T x) { add(ql, qr, x, 0, n, 0); }
	T add(int ql, int qr, T x, int l, int r, int i) {
	}
};
