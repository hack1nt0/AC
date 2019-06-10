#include <bits/stdc++.h>
using namespace std;

//TLE
const int maxn = (int) 2e5 + 4;
const int maxx = (int) 5e5 + 4;
pair<int, int> segs[maxn];
int dp[maxx];
vector<pair<int, int>> ys[maxx];
int ans[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	
	int n, m;
	cin >> n >> m;
	for (int i = 0; i < n; ++i) {
		int l, r;
		cin >> l >> r;
		segs[i] = {l, r};
	}
	sort(segs, segs + n);
	for (int i = 0, p = 0; i < maxx; ++i) {
		dp[i] = i;
		if (i > 0) 
			dp[i] = max(dp[i], dp[i - 1]);
		while (p < n && segs[p].first == i) {
			dp[i] = max(dp[i], segs[p].second);
			++p;
		}
	}
	
	for (int i = 0; i < m; ++i) {
		int x, y;
		cin >> x >> y;
		ys[x].push_back({y, i});
	}
	for (int x = 0; x < maxx; ++x) if (ys[x].size()) {
		sort(ys[x].begin(), ys[x].end());
		int ny = ys[x].size();
		int y = x;
		int p = 0;
		int nc = 0;
		while (p < ny) {
			int y2 = dp[y];
			if (y2 <= y) {
				break;
			}
			y = y2;
			++nc;
			while (p < ny && y >= ys[x][p].first)
				ans[ys[x][p++].second] = nc;
		}
		while (p < ny)
			ans[ys[x][p++].second] = -1;
	}
	for (int i = 0; i < m; ++i)
		cout << ans[i] << "\n";
	return 0;
}
