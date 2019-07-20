#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	int n, ret;
	void input(istream& is = cin) {
		is >> n;
	}
	void solve() {
		ret = 0;
		ret += (1 + n * 2 - 1) * n / 2;
		ret += (1 + (n - 1) * 2 - 1) * (n - 1) / 2;
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
