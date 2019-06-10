#include <bits/stdc++.h>
using namespace std;
const int N = (int) 1e5;
int par[N];
int comps;

int find(int x) {
	return x == par[x] ? x : par[x] = find(par[x]);
}
void unite(int x, int y) {
	int fx = find(x);
	int fy = find(y);
	if (fx == fy) return;
	par[fx] = fy;
	--comps;
}
int main() {
	int n, m;
	cin >> n >> m;
	comps = n;
	for (int i = 0; i < n; ++i)
		par[i] = i;
	for (int i = 0; i < m; ++i) {
		int x, y, z;
		cin >> x >> y >> z, --x, --y;
		unite(x, y);
	}
	cout << comps << endl;
}
