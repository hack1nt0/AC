#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
const int maxn = (int) 2e5 + 10;
int xs[maxn];

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int nq; cin >> nq;
	while (nq--) {
		int n; cin >> n;
		fill(xs, xs + n, 0);
		for (int i = 0; i < n; ++i) {
			int t; cin >> t, t--;
			xs[t]++;
		}
		sort(xs, xs + n);
		int ret = xs[n - 1];
		int mx = xs[n - 1] - 1;
		for (int i = n - 2; i >= 0 && mx > 0; --i, mx--) {
			ret += min(xs[i], mx);
		}
		cout << ret << "\n";
	}
	return 0;
}
