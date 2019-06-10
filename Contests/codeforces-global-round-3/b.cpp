#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 2e5 + 3;
int a[maxn], b[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int n, m, ta, tb, k;
	cin >> n >> m >> ta >> tb >> k;
	for (int i = 0; i < n; ++i) cin >> a[i];
	for (int i = 0; i < m; ++i) cin >> b[i];
	/*
	sort(a, a + n);
	sort(b, b + m);
	*/

	int l = b[0] + tb, r = b[m - 1] + tb + 2;
	while (l + 1 < r) {
		int mid = l + (r - l) / 2;
		int bmax = mid - tb;
		int diff = 0;
		int bcnt = 0;
		int acnt = 0;
		for (int pb = 0, pa = 0; pb < m;) {
			if (b[pb] < bmax) {
				while (pa < n && a[pa] + ta <= b[pb]) {
					++acnt;
					++pa;
				}
				if (acnt > 0)
					++bcnt;
				diff = max(diff, bcnt - acnt);
				//if (bmax == 11) cout << bcnt << " " << acnt << " " << b[pb] << endl;
				++pb;
			} else {
				break;
			}
		}
		//cout << bmax << " " << bcnt << " " << diff << " " << acnt << endl;
		if (bcnt - diff <= k)
			l = mid;
		else 
			r = mid;
	}
	int ans = l;
	if (l == b[m - 1] + tb + 1) ans = -1;
	cout << ans << endl;
	return 0;
}
