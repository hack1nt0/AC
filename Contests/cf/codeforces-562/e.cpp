#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 3e5 + 3;
const int maxb = 20;
int xs[maxn];
int f[maxn][maxb];

int main() {
	int n, q;
	cin >> n >> q;
	for (int i = 0; i < n; ++i) cin >> xs[i];
	for (int i = n; i >= 0; --i) {
		int x = xs[i];
		for (int b = 0; b < maxb; ++b) {
			f[i][b] = n;
			if (i == n) 
				continue;
			if ((x >> b & 1) != 0) {
				f[i][b] = i;
			//} else if ((x & xs[i + 1]) != 0) {
				//f[i][b] = f[i + 1][b];
			} else {
				for (int bb = 0; bb < maxb; ++bb) if ((x >> bb & 1) != 0) {
					f[i][b] = min(f[i][b], f[f[i + 1][bb]][b]);
				}
			}
		}
	}
	for (int i = 0; i < q; ++i) {
		int x, y;
		cin >> x >> y, --x, --y;
		bool ok = false;
		for (int b = 0; b < maxb; ++b) if ((xs[y] >> b & 1) != 0) {
			ok |= f[x][b] <= y;
		}
		cout << (ok ? "Shi" : "Fou") << "\n";
	}
	return 0;
}
