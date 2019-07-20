#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<float> vf;

struct solver {
	int n;
	vf xs;
	vi ret;
	void input(istream& is = cin) {
		is >> n;
		xs.resize(n);
		ret.resize(n);
		for (int i = 0; i < n; ++i) is >> xs[i];
	}
	void solve() {
		llt sum = 0;
		for (int i = 0; i < n; ++i) {
			ret[i] = (int) xs[i];
			sum += ret[i];
		}
		if (sum != 0) {
			for (int i = 0; i < n && sum != 0; ++i) {
				if ((int) xs[i] == xs[i]) continue;
				int del = xs[i] < 0 ? -1 : +1;
				if (abs(sum + del) < abs(sum)) {
					ret[i] += del;
					sum += del;
				}
			}
		}
		assert(sum == 0);
	}
	void print(ostream& os = cout) {
		for (int x : ret) os << x << "\n";
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
