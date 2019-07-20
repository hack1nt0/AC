#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<vi> vvi;

template <typename T>
struct segtree {
	typedef vector<T> vi;
	int n, _n;
	vi xs, lazy, most, maxv;
	segtree(const vi& _xs) {
		n = 1;
		_n = _xs.size();
		while (n < _n) n <<= 1;
		xs.resize(n * 2, 0);
		lazy.resize(n * 2, 0);
		maxv.resize(n * 2, 0);
		for (int i = 0; i < _n; ++i) {
			xs[i + n - 1] = _xs[i];
			maxv[i + n - 1] = _xs[i];
		}
		for (int i = n - 1; i >= 0; --i) {
			xs[i] = xs[L(i)] + xs[R(i)];
			maxv[i] = max(maxv[L(i)], maxv[R(i)]);
		}
	}
	inline int L(int i) { return (i << 1) + 1; }
	inline int R(int i) { return (i << 1) + 2; }
	void add(int ql, int qr, T x) { add(ql, qr, 0, n, 0, x); }
	void add(int ql, int qr, int l, int r, int i, T acc) {
		if (qr <= l || r <= ql) return;
		if (ql <= l && r <= qr) {
			maxv[i] += acc;
			lazy[i] += acc;
			return;
		}
		int m = l + (r - l) / 2;
		add(ql, qr, l, m, L(i), acc);
		add(ql, qr, m, r, R(i), acc);
		maxv[i] = max(maxv[L(i)], maxv[R(i)]);
	}
	int find() { return find(0, n, 0, 0); }
	int find(int l, int r, int i, T acc) {
		if (l + 1 == r) return maxv[i] + acc > 0 ? l : -1;
		int m = l + (r - l) / 2;
		if (maxv[R(i)] + acc > 0) return find(m, r, R(i), acc + lazy[R(i)]);
		if (maxv[L(i)] + acc > 0) return find(l, m, L(i), acc + lazy[L(i)]);
		return -1;
	}
	void reset(int ql, int qr, T x) { reset(ql, qr, 0, n, 0, x); }
	void reset(int ql, int qr, int l, int r, int i, T x) {
		if (qr <= l || r <= ql) return;
		if (ql <= l && r <= qr) {
			maxv[i] = x;
			covv[i] = x;
			coved[i] = true;
			return;
		}
		if (coved[i]) {
			coved[i] = false;
			covv[L(i)] = covv[i];
			covv[R(i)] = covv[i];
		}
		int m = l + (r - l) / 2;
		add(ql, qr, l, m, L(i), x);
		add(ql, qr, m, r, R(i), x);
		maxv[i] = max(maxv[L(i)], maxv[R(i)]);
	}
	void query(int ql, int qr) { reset(ql, qr, 0, n, 0, acc); }
	void reset(int ql, int qr, int l, int r, int i, T x) {
		if (qr <= l || r <= ql) return;
		if (ql <= l && r <= qr) {
			maxv[i] = x;
			covv[i] = x;
			coved[i] = true;
			return;
		}
		if (coved[i]) {
			coved[i] = false;
			covv[L(i)] = covv[i];
			covv[R(i)] = covv[i];
		}
		int m = l + (r - l) / 2;
		add(ql, qr, l, m, L(i), x);
		add(ql, qr, m, r, R(i), x);
		maxv[i] = max(maxv[L(i)], maxv[R(i)]);
	}
};

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int nd, np, nq;
	cin >> nd >> np;
	vi cost(nd);
	vi money(np);
	for (int i = 0; i < nd; ++i) cin >> cost[i];
	for (int i = 0; i < np; ++i) cin >> money[i];
	const int maxn = (int) 1e6 + 10;
	vi psum(maxn, 0);
	for (int x : cost) psum[x]++;
	for (int x : money) psum[x]--;
	for (int i = maxn - 1; i >= 1; --i) psum[i] += psum[i + 1];
	//debug(psum);
	segtree st(psum);
	cin >> nq;
	while (nq--) {
		int t, i, x;
		cin >> t >> i >> x, i--;
		if (t == 1) {
			st.add(0, cost[i] + 1, -1);
			st.add(0, x + 1, +1);
			//cout << st.at(x) << endl;
		} else {
			st.add(0, money[i] + 1, +1);
			st.add(0, x + 1, -1);
		}
		cout << st.find() << "\n";
	}
	return 0;
}
