#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

//WA

typedef long long llt;
typedef vector<int> vi;
typedef vector<llt> vl;
typedef vector<vi> vvi;
typedef pair<int, int> pii;
#define pb push_back
#define all(x) x.begin(),x.end()
#define fi first
#define se second
#define size(x) int(x.size())

vvi adj;
vi val;
int ret;
void dfs(int x, int l, int r) {
	//debug(x + 1, l, r);
	if (x < 0 || r - l <= 0) return;
	if (l <= val[x] && val[x] < r) ret++;// debug(val[x]);
	if (val[x] < l) {
		dfs(adj[x][1], l, r);
	} else if (l <= val[x] && val[x] < r) {
		dfs(adj[x][0], l, val[x]);
		dfs(adj[x][1], val[x] + 1, r);
	} else {
		dfs(adj[x][0], l, r);
	}
}
int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n; cin >> n;
	adj.resize(n);
	val.resize(n);
	vi deg(n, 0);
	for (int i = 0; i < n; ++i) {
		int v, l, r;
		cin >> v >> l >> r; l--, r--;
		val[i] = v;
		adj[i].pb(l), adj[i].pb(r);
		if (l >= 0) deg[l]++;
		if (r >= 0) deg[r]++;
	}
	int root = -1;
	for (int i = 0; i < n; ++i) if (!deg[i]) {
		assert(root == -1);
		root = i;
	}
	ret = 0;
	dfs(root, 0, *max_element(all(val)) + 1);
	cout << n - ret << endl;
	return 0; 
}
