#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	llt x, y, z;
	llt ret, debt;
	void input(istream& is = cin) {
		is >> x >> y >> z;
	}
	void solve() {
		ret = x / z + y / z;
		llt remx = x % z, remy = y % z;
		debt = 0;
		if (remx + remy >= z) {
			debt = z - max(remx, remy);
			ret++;
		}
	}
	void print(ostream& os = cout) {
		os << ret << " " << debt << endl;
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
