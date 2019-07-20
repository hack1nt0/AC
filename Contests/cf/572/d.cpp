#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<llt> vl;
typedef vector<vi> vvi;
typedef pair<int, int> pii;
#define pb push_back
#define all(x) x.begin(),x.end()
#define fi first
#define se second
//#define size(x) int(x.size())

const int maxn = 1e5 + 10;
vi adj[maxn];
pii es[maxn];
int capa[maxn];
int comp[maxn];

int find(int x) { return x == comp[x] ? x : (comp[x] = find(comp[x])); }
void unite(int x, int y) {
	int px = find(x), py = find(y);
	if (px != py) {
		comp[px] = py;
		capa[py] += capa[px];
		capa[px] = 0;
	}
}

int another(int e, int x) { return es[e].fi == x ? es[e].se : es[e].fi; }
void dfs(int x, int ie) {
	if (ie != -1 && adj[x].size() - 1 == 1) {
		unite(adj[x][0], ie);
		unite(adj[x][1], ie);
	}
	if (ie == -1 && adj[x].size() == 2)
		unite(adj[x][0], adj[x][1]);
	for (int oe : adj[x]) if (oe != ie) {
		dfs(another(oe, x), oe);
	}
}
int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n; cin >> n;
	for (int i = 0; i < n - 1; ++i) {
		int x, y; cin >> x >> y; x--, y--;
		es[i] = {x, y};
		adj[x].pb(i), adj[y].pb(i);
	}
	fill(capa, capa + n - 1, 1);
	for (int i = 0; i < n - 1; ++i) comp[i] = i;
	dfs(0, -1);
	bool ok = true;
	for (int i = 0; i < n - 1; ++i) ok &= capa[i] <= 1;
	//debug(capa[0], capa[1]);
	cout << (ok ? "YES" : "NO") << endl;
	return 0;
}
