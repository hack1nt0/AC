#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	int n;
	vector<pair<int, int>> xs;
	int ret;
	void input(istream& is = cin) {
		is >> n;
		xs.resize(n);
		for (int i = 0; i < n; ++i) {
			int x; is >> x;
			xs[i] = {x, i};
		}
	}
	void solve() {
		sort(xs.begin(), xs.end());
		ret = -1;
		if (n == 2) 
			ret = 0;
		else {
			if (check(2, xs[2].first - xs[1].first)) 
				ret = xs[0].second;
			else if (check(2, xs[2].first - xs[0].first)) 
				ret = xs[1].second;
			else {
				int step = xs[1].first - xs[0].first;
				int pre = xs[1].first;
				bool ok = true;
				for (int i = 2; i < n; ++i) {
					if (xs[i].first - pre != step) {
						if (ret == -1) 
							ret = xs[i].second;
						else {
							ok = false; break;
						}
					} else {
						pre = xs[i].first;
					}
				}
				if (!ok) ret = -1;
				else if (ret == -1) ret = xs[0].second;
			}
		}
	}
	bool check(int from, int step) {
		for (int i = from + 1; i < n; ++i) if (xs[i].first - xs[i - 1].first != step)
			return false;
		return true;
	}
	void print(ostream& os = cout) {
		os << (ret >= 0 ? ret + 1 : -1) << endl;
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
