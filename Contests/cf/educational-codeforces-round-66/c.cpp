#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 2e5 + 4;
int xs[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int t; cin >> t;
	while (t-- > 0) {
		int n, k;
		cin >> n >> k;
		for (int i = 0; i < n; ++i) cin >> xs[i];

		int l = -1, r = xs[k] - xs[0];
		int x = xs[0];
		while (l + 1 < r) {
			int mid = l + (r - l) / 2;
			bool ok = false;

			int len = mid * 2 + 1;
			deque<int> que;
			int nc = 0;
			for (int i = 0; i < n; ++i) {
				que.push_back(i);
				++nc;
				while (!que.empty() && xs[i] - xs[que.front()] + 1 > len) {
					que.pop_front();
					--nc;
				}
				if (nc >= k + 1) {
					ok = true;
					x = xs[i] - mid;
					break;
				}
				assert(!que.empty());
			}
			
			if (ok)
				r = mid;
			else
				l = mid;
		}
		cout << x << "\n";
	}
	return 0;
}
