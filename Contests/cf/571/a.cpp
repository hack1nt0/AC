#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;

struct solver {
	int n, m, k;
	bool ret;
	void input(istream& is = cin) {
		is >> n >> m >> k;
	}
	void solve() {
		ret = n <= min(m, k);
	}
	void print(ostream& os = cout) {
		os << (ret ? "Yes" : "No") << endl;
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
