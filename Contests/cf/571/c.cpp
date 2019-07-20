#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;

struct solver {
	string a, b;
	int ret;
	void input(istream& is = cin) {
		is >> a >> b;
	}
	void solve() {
		ret = 0;
		int x = 0;
		for (char c : b) x = (x + c - '0') % 2;
		int na = a.size(), nb = b.size();
		int y = 0;
		for (int i = 0; i < nb; ++i) y = (y + a[i] - '0') % 2;
		for (int i = nb; i <= na; ++i) {
			if ((x + y) % 2 == 0) ret++;
			if (i == na) break;
			y = y + a[i] - '0' - (a[i - nb] - '0');
			y = (y % 2 + 2) % 2;
		}
	}
	void print(ostream& os = cout) {
		os << ret << endl;
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
