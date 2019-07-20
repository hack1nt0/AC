#include <bits/stdc++.h>
using namespace std;

//WA
const int maxn = (int) 1e5 + 3;
const int maxd = 50 + 2;
vector<int> adj[maxn];
int open[maxn][maxd];
bool dp[maxn][maxd];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n, m, d;
	cin >> n >> m >> d;
	for (int i = 0; i < m; ++i) {
		int u, v; cin >> u >> v;
		--u, --v;
		adj[u].push_back(v);
	}
	for (int i = 0; i < n; ++i) {
		char c;
		for (int j = 0; j < d; ++j) {
			cin >> c;
			open[i][j] = c - '0';
		}
	}
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < d; ++j)
			dp[i][j] = false;
	dp[0][0] = true;
	queue<pair<int, int>> que;
	que.push({0, 0});
	while (que.size() > 0) {
		auto p = que.front();
		que.pop();
		int x = p.first;
		int xd = p.second;
		int yd = (xd + 1) % d;
		for (int y : adj[x]) {
			if (!dp[y][yd]) {
				dp[y][yd] = true;
				que.push({y, yd});
			}
		}
	}
	int ans = 0;
	for (int i = 0; i < n; ++i) {
		bool ok = false;
		for (int j = 0; j < d; ++j) ok |= dp[i][j] && open[i][j];
		if (ok) ++ans;
	}
	cout << ans << endl;
	return 0;
}
