#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	int n;
	vector<int> xs;
	vector<int> ret;
	void input(istream& is = cin) {
		is >> n;
		xs.resize(n);
		for (int i = 0; i < n; ++i) is >> xs[i];
	}
	void solve() {
		ret = xs;
		for (int& x : ret) if (x >= 0) x = -x - 1;
		if (n % 2 == 0) return;
		/*
		long long prod = 1, mx;
		for (int x : ret) prod *= x;
		int m = -1;
		for (int i = 0; i < n; ++i) {
			int x = ret[i];
			long long cur = prod / x * (-x - 1);
			if (cur > mx || m == -1) {
				m = i;
				mx = cur;
			}
		}
		*/
		int m = min_element(ret.begin(), ret.end()) - ret.begin();
		ret[m] = -ret[m] - 1;
	}
	void print(ostream& os = cout) {
		for (int i = 0; i < n; ++i) os << ret[i] << " ";
		os << endl;
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
