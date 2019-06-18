#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;
	cout << (n % 2 == 0 ? 1 << n / 2 : 0) << endl;
	return 0;
}
