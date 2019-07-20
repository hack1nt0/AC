#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 2e5 + 3;
int xs[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n; cin >> n;
	for (int i = 0; i < n; ++i) cin >> xs[i];
	sort(xs, xs + n);
	int d = -1;
	int c = 0;
	for (int i = 1; i < n; ++i) {
		int di = xs[i] - xs[i - 1];
		if (d == -1 || di < d) {
			d = di;
			c = 1;
		} else if (di == d) {
			++c;
		}
	}
	cout << d << " " << c << endl;
	return 0;
}
