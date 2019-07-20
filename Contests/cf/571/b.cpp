#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;

struct solver {
	llt n, m;
	llt ret = 0;
	void input(istream& is = cin) {
		is >> n >> m;
	}
	void solve() {
		{
			llt x = 0;
			llt h = (n + 1) / 3;
			llt w = (m + 1) / 2;
			x = h * w;
			if (n % 3 == 1) x += (m + 1) / 3;
			ret = max(ret, x);
		}
		{
			llt x = 0;
			llt h = (n + 1) / 2;
			llt w = (m + 1) / 3;
			x = h * w;
			if (m % 3 == 1) x += (n + 1) / 3;
			ret = max(ret, x);
		}
	}
	void print(ostream& os = cout) {
		os << ret << "\n";
	}
};

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	solver sol;
	sol.input();
	sol.solve();
	sol.print();
	return 0;
}
