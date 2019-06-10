#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int n; cin >> n;
	//2 * (m - 1) >= n - 1
	int m = (n - 1 + 2 + 1) / 2;
	cout << m << "\n";
	int nn = n;
	for (int i = 1; i <= m && nn > 0; ++i, --nn)
		cout << i << " " << 1 << "\n";
	for (int i = 2; i <= m && nn > 0; ++i, --nn)
		cout << m << " " << i << "\n";
	return 0;
}
