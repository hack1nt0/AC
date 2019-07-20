#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;

struct solver {
	int n, s, t;
	int r;
	void input(istream& is = cin) {
		is >> n >> s >> t;
	}
	void solve() {
		if (s + t >= 2 * n) 
			r = 1;
		else {
			int c = s + t - n;
			assert(c <= s && c <= t);
			r = max(s - c, t - c) + 1;
		}
	}
	void print(ostream& os = cout) {
		os << r << endl;
	}
};

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	solver sol;
	int nq; cin >> nq;
	while (nq--) {
		sol.input();
		sol.solve();
		sol.print();
	}
	return 0;
}
