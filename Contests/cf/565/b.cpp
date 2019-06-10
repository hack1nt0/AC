#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int t; cin >> t;
	while (t--) {
		int n;
		cin >> n;
		vector<int> c(3, 0);
		for (int i = 0; i < n; ++i) {
			int d;
			cin >> d;
			c[d % 3]++;
		}
		cout << c[0] + min(c[1], c[2]) + abs(c[1] - c[2]) / 3 << "\n";
	}
	return 0;
}
