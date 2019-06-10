#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
const int maxn = (int) 2e5 + 3;
const int mod = 998244353;
llt fact[maxn];
llt dp[maxn];
vector<int> adj[maxn];
int n;

void dfs(int x, int fa = -1) {
	int ny = adj[x].size();
	if (x != 0) --ny;
	if (ny == 0) {
		dp[x] = 1;
		return;
	}
	dp[x] = x == 0 ? n : ny + 1;
	dp[x] = dp[x] * fact[ny] % mod;
	for (int y : adj[x]) if (y != fa) {
		dfs(y, x);
		dp[x] = dp[x] * dp[y] % mod;
	}
}
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	cin >> n;
	for (int i = 0; i < n - 1; ++i) {
		int u, v;
		cin >> u >> v;
		--u, --v;
		adj[u].push_back(v);
		adj[v].push_back(u);
	}
	fact[0] = 1;
	for (int i = 1; i <= n; ++i) fact[i] = fact[i - 1] * i % mod;
	dfs(0);
	cout << dp[0] << endl;
	///for (int i = 0; i < n; ++i) cout << (i + 1) << " = " << dp[i] << endl;
	return 0;
}
