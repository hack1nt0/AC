#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 1e3 + 2;
int xs[maxn][maxn];
int row[maxn][maxn];
int col[maxn][maxn];
int rsz[maxn];
int csz[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n, m;
	cin >> n >> m;
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j)
			cin >> xs[i][j];
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m; ++j) row[i][j] = xs[i][j];
		sort(row[i], row[i] + m);
		rsz[i] = unique(row[i], row[i] + m) - row[i];
	}
	for (int i = 0; i < m; ++i) {
		for (int j = 0; j < n; ++j) col[i][j] = xs[j][i];
		sort(col[i], col[i] + n);
		csz[i] = unique(col[i], col[i] + n) - col[i];
	}
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m; ++j) {
			int x = xs[i][j];
			int l1 = lower_bound(row[i], row[i] + rsz[i], x) - row[i];
			int r1 = rsz[i] - l1 - 1;
			int l2 = lower_bound(col[j], col[j] + csz[j], x) - col[j];
			int r2 = csz[j] - l2 - 1;
			cout << max(l1, l2) + 1 + max(r1, r2) << " ";
		}
		cout << "\n";
	}
	return 0;
}
