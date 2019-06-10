#include <bits/stdc++.h>
using namespace std;

int main() {
	cout.precision(10);
	cout << fixed;
	int n, k;
	cin >> n >> k;
	double prob = max(0.0, (n - k + 1.0) / n);
	for (int x = 1; x <= min(n, k - 1); ++x) {
		int pow2 = 1;
		while (x * pow2 < k) pow2 <<= 1;
		prob += 1.0 / (n * pow2);
	}
	cout << prob << endl;
	return 0;
}
