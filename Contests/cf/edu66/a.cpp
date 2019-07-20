#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int t; cin >> t;
	while (t-- > 0) {
		llt n, k;
		cin >> n >> k;
		llt ans = 0;
		while (n != 0) {
			if (n % k == 0) {
				++ans;
				n /= k;
			} else {
				llt m = n / k;
				llt step = n - k * m;
				n -= step;
				ans += step;
			}
		}
		cout << ans << "\n";
	}
	return 0;
}
