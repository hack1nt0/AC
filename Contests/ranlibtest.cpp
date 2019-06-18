#include "ranlib.h"

bool isdag(int x, vector<vector<int>>& adj, vector<int> vis) {
	vis[x] = -1;
	for (int y : adj[x]) {
		if (vis[y] == -1) return false;
		if (vis[y] == +1) continue;
		if (vis[y] == 0 && !isdag(y, adj, vis)) return false;
	}
	vis[x] = +1;
	return true;
}
bool isdag(vector<vector<int>>& adj) {
	int n = adj.size();
	vector<int> vis(n, 0);
	for (int i = 0; i < n; ++i) if (!vis[i])
		if (!isdag(i, adj, vis))
			return false;
	return true;
}
int main(int argc, char* args[]) {
	int l = argc <= 1 ? 1 : atoi(args[1]);
	int r = argc <= 2 ? 3 : atoi(args[2]);
	int k = argc <= 3 ? 2 : atoi(args[3]);
	bool back = argc <= 4 ? false : true;
	int tot = argc <= 5 ? (int) 1e5 : atoi(args[5]);
	map<vector<int>, double> mp;
	/*
	for (int i = 0; i < tot; ++i) {
		auto v = rchoose(l, r, k, back);
		sort(v.begin(), v.end());
		mp[v]++;
	}
	for (auto e : mp) {
		cout << e.first << " = " << e.second / tot << endl;
	}
	*/
	//dp[n, k] = dp[n - 1, k] + dp[n, k - 1];
	int n = r - l + 1;
	vector<long long> dp(n + 1);
	for (int i = 1; i <= k; ++i) {
		if (i == 1) {
			for (int j = 0; j <= n; ++j) dp[j] = j;
		} else {
			for (int j = 1; j <= n; ++j)
				dp[j] += dp[j - 1];
		}
	}
	cout << mp.size() << " <= " << dp[n] << endl;
	return 0;
}
