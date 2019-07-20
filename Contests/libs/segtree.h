#include <bits/stdc++.h>
using namespace std;

struct segtree {
	typedef int T;
	typedef vector<T> vi;
	int n, _n;
	vi val;
	vi coved;
	vi covv;
	segtree(const vi& _xs) {
		n = 1;
		_n = _xs.size();
		while (n < _n) n <<= 1;
		val.resize(n * 2, maxn);
		coved.resize(n * 2, false);
		covv.resize(n * 2, 0);
		for (int i = 0; i < _n; ++i) {
			val[i + n - 1] = _xs[i];
		}
		for (int i = n - 2; i >= 0; --i) {
			val[i] = min(val[L(i)], val[R(i)]);
		}
	}
	inline int L(int i) { return (i << 1) + 1; }
	inline int R(int i) { return (i << 1) + 2; }
	void reset(int ql, int qr, T x) { reset(ql, qr, 0, n, 0, x); }
	void reset(int ql, int qr, int l, int r, int i, T x) {
		if (qr <= l || r <= ql) return;
		if (ql <= l && r <= qr) {
			coved[i] = true;
			covv[i] = x;
			val[i] = covv[i];
			return;
		}
		if (coved[i]) {
			coved[L(i)] = true;
			covv[L(i)] = covv[i];
			val[L(i)] = covv[i];
			coved[R(i)] = true;
			covv[R(i)] = covv[i];
			val[R(i)] = covv[i];
			coved[i] = false;
		}
		int m = l + (r - l) / 2;
		reset(ql, qr, l, m, L(i), x);
		reset(ql, qr, m, r, R(i), x);
		val[i] = min(val[L(i)], val[R(i)]);
	}
	T query(int ql, int qr) { return query(ql, qr, 0, n, 0); }
	T query(int ql, int qr, int l, int r, int i) {
		if (qr <= l || r <= ql) return maxn;
		if (ql <= l && r <= qr) {
			if (coved[i]) return covv[i];
			else return val[i];
		}
		if (coved[i]) {
			coved[L(i)] = true;
			covv[L(i)] = covv[i];
			val[L(i)] = covv[i];
			coved[R(i)] = true;
			covv[R(i)] = covv[i];
			val[R(i)] = covv[i];
			coved[i] = false;
		}
		int m = l + (r - l) / 2;
		T ret = maxn;
		ret = min(ret, query(ql, qr, l, m, L(i)));
		ret = min(ret, query(ql, qr, m, r, R(i)));
		return ret;
	}
};
