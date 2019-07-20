#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
#define pb push_back
#define se second

const int maxn = 3e5 + 10;

struct segtree {
	typedef int T;
	typedef vector<T> vi;
	int n, _n;
	vi xs;
	vi maxv;
	vi coved;
	vi covv;
	segtree(const vi& _xs) {
		n = 1;
		_n = _xs.size();
		while (n < _n) n <<= 1;
		xs.resize(n * 2, 0);
		maxv.resize(n * 2, maxn);
		coved.resize(n * 2, false);
		covv.resize(n * 2, 0);
		for (int i = 0; i < _n; ++i) {
			maxv[i + n - 1] = _xs[i];
		}
		for (int i = n - 2; i >= 0; --i) {
			maxv[i] = min(maxv[L(i)], maxv[R(i)]);
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
			maxv[i] = covv[i];
			return;
		}
		if (coved[i]) {
			coved[L(i)] = true;
			covv[L(i)] = covv[i];
			maxv[L(i)] = covv[i];
			coved[R(i)] = true;
			covv[R(i)] = covv[i];
			maxv[R(i)] = covv[i];
			coved[i] = false;
		}
		int m = l + (r - l) / 2;
		reset(ql, qr, l, m, L(i), x);
		reset(ql, qr, m, r, R(i), x);
		maxv[i] = min(maxv[L(i)], maxv[R(i)]);
	}
	T query(int ql, int qr) { return query(ql, qr, 0, n, 0); }
	T query(int ql, int qr, int l, int r, int i) {
		if (qr <= l || r <= ql) return maxn;
		if (ql <= l && r <= qr) {
			if (coved[i]) return covv[i];
			else return maxv[i];
		}
		if (coved[i]) {
			coved[L(i)] = true;
			covv[L(i)] = covv[i];
			maxv[L(i)] = covv[i];
			coved[R(i)] = true;
			covv[R(i)] = covv[i];
			maxv[R(i)] = covv[i];
			coved[i] = false;
		}
		int m = l + (r - l) / 2;
		T ret = maxn;
		ret = min(ret, query(ql, qr, l, m, L(i)));
		ret = min(ret, query(ql, qr, m, r, R(i)));
		return ret;
	}
};

struct solver {
	int n;
	vi a, b;
	bool ret;
	void input(istream& is = cin) {
		is >> n;
		a.resize(n);
		b.resize(n);
		for (int i = 0; i < n; ++i) is >> a[i];
		for (int i = 0; i < n; ++i) is >> b[i];
	}
	void solve() {
		vi ptr(n);
		{
			map<int, deque<int>> mp;
			for (int i = 0; i < n; ++i) mp[a[i]].pb(i);
			for (int i = 0; i < n; ++i) {
				auto it = mp.find(b[i]);
				if (it == mp.end() || it->se.size() == 0) { ret = false; return; }
				ptr[i] = it->se[0];
				it->se.pop_front();
			}
		}
		segtree seg(a);
		ret = true;
		for (int i = 0; i < n; ++i) {
			if (seg.query(0, ptr[i] + 1) == b[i]) {
				seg.reset(ptr[i], ptr[i] + 1, maxn);
			} else {
				ret = false;
				return;
			}
		}
	}
	void print(ostream& os = cout) {
		os << (ret ? "YES" : "NO") << endl;
	}
};

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int nt; cin >> nt;
	while (nt--) {
		solver sol;
		sol.input();
		sol.solve();
		sol.print();
	}
	return 0;
}
