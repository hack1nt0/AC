#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int q; cin >> q;
	while (q--) {
		long long n;
		cin >> n;
		map<int, int> fs = {{2, 0}, {3, 0}, {5, 0}};
		for (auto& e : fs) {
			int f = e.first;
			int& p = e.second;
			while (n % f == 0) {
				p++;
				n /= f;
			}
		}
		int ans;
		if (n > 1)
			ans = -1;
		else
			ans = fs[5] * 3 + fs[3] * 2 + fs[2];
		cout << ans << "\n";
	}
	return 0;
}
