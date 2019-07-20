#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	void solve(int n, int m) {
		vector<pair<int, int>> ret(n * m);
		vector<pair<int, int>> xs(n * m);
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) xs[i * m + j] = {i + 1, j + 1};
		int i = 0;
		int d = n * m - 1;
		int px = 0;
		while (i < n * m) {
			ret[i] = xs[px];
			//cout << px / m + 1 << ' ' << px % m + 1 << '\n';
			px += d;
			if (d > 0) d = -(d - 1);
			else d = -d - 1;
			++i;
		}
		assert(d <= 0);
		check(ret);
	}
	void check(const vector<pair<int, int>>& xs) {
		set<pair<int, int>> dirs;
		for (int i = 1; i < xs.size(); ++i) {
			pair<int, int> dir = {xs[i].first - xs[i - 1].first, xs[i].second - xs[i - 1].second};
			assert(dirs.find(dir) == dirs.end());
			dirs.insert(dir);
		}
	}
};

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	solver sol;
	/*
	int n, m;
	cin >> n >> m;
	sol.solve(n, m);
	*/
	const int maxn = (int) 1e6 + 3;
	for (int n = 1; n <= maxn; ++n)
		sol.solve(n, max(1, rand() % (max(maxn / n, 1))));
	return 0;
}
