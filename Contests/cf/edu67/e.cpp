#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

//TLE
typedef long long llt;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int, int> pii;
#define pb push_back
#define fi first
#define se second
const int maxn = 2e5 + 10;
vi adj[maxn];
struct fhash {
	int operator()(const pii& key) const { return (key.fi << 31) + key.se; }
};
unordered_map<pii, llt, fhash> f, g;

llt dfsg(int x, int fa) {
	pii st = {x, fa};
	auto it = g.find(st);
	if (it != g.end()) return it->se;
	llt z = 1;
	for (int y : adj[x]) if (y != fa) {
		z += dfsg(y, x);
	}
	g[st] = z;
	return z;
}

llt dfsf(int x, int fa) {
	pii st = {x, fa};
	auto it = f.find(st);
	if (it != f.end()) return it->se;
	llt z = g[st];
	assert(z);
	for (int y : adj[x]) if (y != fa) {
		z += dfsf(y, x);
	}
	f[st] = z;
	return z;
}

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n; cin >> n;
	for (int i = 0; i < n - 1; ++i) {
		int x, y; cin >> x >> y; x--, y--;
		adj[x].pb(y), adj[y].pb(x);
	}
	for (int i = 0; i < n; ++i) dfsg(i, -1);
	llt ret = 0;
	for (int i = 0; i < n; ++i) ret = max(ret, dfsf(i, -1));
	cout << ret << endl;
	return 0;
}
