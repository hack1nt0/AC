#include <bits/stdc++.h>

using namespace std;

typedef long long llt;
const int maxd = 70;
llt head[maxd];
llt len[maxd];
llt step[maxd];

llt smaller(llt x, int layer) {
	llt l = 0, r = len[layer];
	while (l + 1 < r) {
		llt mid = l + (r - l) / 2;
		if (head[layer] + step[layer] * mid <= x)
			l = mid;
		else
			r = mid;
	}
	return head[layer] + step[layer] * l;
}
llt larger(llt x, int layer) {
	llt l = -1, r = len[layer] - 1;
	while (l + 1 < r) {
		llt mid = r - (r - l) / 2;
		if (head[layer] + step[layer] * mid > x)
			r = mid;
		else
			l = mid;
	}
	return head[layer] + step[layer] * r;
}
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	llt n, q;
	cin >> n >> q;
	int dep = 1;
	while ((1LL << dep) < n) ++dep;
	for (int i = 0; i < dep; ++i) {
		head[i] = 1LL << i;
		len[i] = 1LL << dep - i - 1;
		step[i] = 1LL << i + 1;
	}
	for (int i = 0; i < q; ++i) {
		llt s;
		string path;
		cin >> s >> path;
		int layer = 0;
		while (smaller(s, layer) != s) ++layer;
		llt t = s;
		for (char c : path) {
			if (c == 'U') {
				if (layer == dep - 1) continue;
				++layer;
				t = smaller(t, layer);
			}
			if (c == 'L') {
				if (layer == 0) continue;
				--layer;
				t = smaller(t, layer);
			}
			if (c == 'R') {
				if (layer == 0) continue;
				--layer;
				t = larger(t, layer);
			}
		}
		cout << t << "\n";
	}
	cout << endl;
	return 0;
}
