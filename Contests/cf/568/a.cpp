#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	int a, b, c, d;
	int ret;
	void input(istream& is = cin) {
		is >> a >> b >> c >> d;
	}
	void solve() {
		vector<int> xs = {a, b, c};
		sort(xs.begin(), xs.end());
		int x = xs[1] - xs[0], y = xs[2] - xs[1];
		ret = max(0, d - x) + max(0, d - y);
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
