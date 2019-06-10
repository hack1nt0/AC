#include <bits/stdc++.h>
using namespace std;

const int N = (int) 1e5 + 2;
vector<pair<int, int>> adj[N];
int col[N];
int n;
void dfs(int x, int fa) {
	for (auto& e : adj[x]) if (e.first != fa) {
		if (e.second % 2 == 1) {
			col[e.first] = col[x] ^ 1;
		} else {
			col[e.first] = col[x];
		}
		dfs(e.first, x);
	}
}
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	cin >> n;
	for (int i = 0; i < n - 1; ++i) {
		int u, v, w;
		cin >> u >> v >> w, --u, --v;
		adj[u].push_back({v, w});
		adj[v].push_back({u, w});
	}
	col[0] = 0;
	dfs(0, -1);
	for (int i = 0; i < n; ++i) cout << col[i] << "\n";
	return 0;
}
