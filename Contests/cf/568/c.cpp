#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	int n, m;
	vector<int> cost, cnt, ret;
	void input(istream& is = cin) {
		is >> n >> m;
		cost.resize(n);
		cnt.resize(101, 0);
		ret.resize(n);
		for (int i = 0; i < n; ++i)
			is >> cost[i];
	}
	void solve() {
		int x = 0;
		for (int i = 0; i < n; ++i) {
			int y = cost[i];
			int z = 0;
			int mn = 0;
			for (int j = 100; j >= 1 && z < x + y - m; --j) if (cnt[j] > 0) {
				int k = min((x + y - m - z + j - 1) / j, cnt[j]);
				z += k * j; 
				mn += k;
			}
			assert(z >= x + y - m);
			ret[i] = mn;
			x += cost[i];
			cnt[cost[i]]++;
		}
	}
	void print(ostream& os = cout) {
		for (int i = 0; i < n; ++i) os << ret[i] << " ";
		os << endl;
	}
	void print(vector<int> xs) {
		for (int i = 0; i < xs.size(); ++i) cout << xs[i] << " ";
		cout << endl;
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
