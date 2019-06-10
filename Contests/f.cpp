#include <bits/stdc++.h>
using namespace std;

//TLE
const int maxn = (int) 1e5 + 3;
int parent[maxn];
int vis[maxn];
vector<pair<int, int>> adj[maxn];
map<int, int> adj2[maxn];
set<int> nbs[maxn];

int find(int x) {
	return x == parent[x] ? x : parent[x] = find(parent[x]);
}
int unite(int x, int y) {
	int fx = find(x);
	int fy = find(y);
	if (fx == fy) return fx;
	parent[fx] = fy;
	nbs[fy].insert(nbs[fx].begin(), nbs[fx].end());
	nbs[fx].clear();
	return fy;
}
void dfs(int x, int root, int c, int parity, int fa) {
	if (parity == 0) {
		if (vis[x] > 0) {
			if (parity == 0)
				unite(x, root);
			return;
		}
		if (vis[x] < 0) return;
		vis[x] = -1;
		if (x != root) {
			unite(x, root);
		}
	} else {
		nbs[find(root)].insert(x);
	}
	for (auto& e : adj[x]) if (e.first != fa) {
		if (parity == 0)
			dfs(e.first, root, e.second, 1, x);
		else if (c == e.second)
			dfs(e.first, root, c, 0, x);
	}
	if (parity == 0) {
		vis[x] = +1;
	}
}
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n, m, c, q;
	cin >> n >> m >> c >> q;
	for (int i = 0; i < m; ++i) {
		int u, v, z; 
		cin >> u >> v >> z;
		--u, --v;
		adj[u].push_back({v, z});
		adj[v].push_back({u, z});
	}
	for (int i = 0; i < n; ++i) {
		vis[i] = 0;
		parent[i] = i;
	}
	for (int i = 0; i < n; ++i) if (!vis[i]) {
		dfs(i, i, -1, 0, -1);
		vis[i] = 1;
		for (auto& e : adj[i]) adj2[i][e.second] = find(e.first);
	}
	for (int i = 0; i < n; ++i)
		for (auto& e : adj[i]) 
			adj2[i][e.second] = find(e.first);
	for (int i = 0; i < q; ++i) {
		char c; cin >> c;
		///cout << c << endl;
		if (c == '?') {
			int u, v;
			cin >> u >> v, --u, --v;
			int fu = find(u);
			int fv = find(v);
			///cout << c << " " << u << " " << v << endl;
			bool ok = fu == fv || nbs[fu].count(v);
			cout << (ok ? "Yes": "No") << "\n";
		} else {
			int u, v, z; 
			cin >> u >> v >> z;
			--u, --v;
			for (int j : {u, v}) {
				if (adj2[j].count(z)) {
					unite(u + v - j, adj2[j][z]);
					nbs[find(u + v - j)].insert(j);
				} else {
					adj2[j][z] = find(u + v - j);
				}
			}
		}
	}
	return 0;
}
