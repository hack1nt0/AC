#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 1e3 + 2;
int cell[maxn][maxn];
int from[maxn * maxn];
int to[maxn * maxn];
int vis[maxn * maxn];
vector<int> adj[maxn * maxn];
int n, nn;

bool check(int x) {
	return 0 <= x && x < n;
}
bool valid(int u) {
	int i = u / n, j = u % n;
	return 1 <= i && i < n - 1 && 1 <= j && j < n - 1 && cell[i][j];
}
bool dfs(int x) {
	if (vis[x] == -1) 
		return false;
	vis[x] = -1;
	bool ok = false;
	for (int y : adj[x]) {
		if (from[y] == -1 || dfs(from[y])) {
			ok = true;
			to[x] = y;
			from[y] = x;
			break;
		}
	}
	vis[x] = +1;
	return ok;
}
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int m;
	cin >> n >> m;
	nn = n * n;
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < n; ++j) cell[i][j] = 1;
	for (int i = 0; i < m; ++i) {
		int x, y;
		cin >> x >> y;
		--x, --y;
		cell[x][y] = 0;
	}
	int ans = 0;
	vector<bool> row(n), col(n);
	for (int i = 1; i < n - 1; ++i) {
		int sum = 0;
		for (int j = 0; j < n; ++j)
			sum += cell[i][j];
		row[i] = sum == n;
		if (row[i]) ++ans;
	}
	for (int i = 1; i < n - 1; ++i) {
		int sum = 0;
		for (int j = 0; j < n; ++j)
			sum += cell[j][i];
		col[i] = sum == n;
		if (col[i]) ++ans;
	}
	if (n % 2 == 1 && row[n / 2] && col[n / 2])
		--ans;
	cout << ans << endl;
}
