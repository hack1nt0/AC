#include <bits/stdc++.h>
using namespace std;

//WA
const int M = 17;
int xs[1 << (M + 1) + 3];
int n;

void print() {
	for (int i = 0; i < n; ++i) cout << xs[i] << " ";
	cout << endl;
}
int main() {
	int m, k;
	cin >> m >> k;
	n = 1 << m + 1;
	if ((1 << m) - 1 < k) {
		cout << -1 << endl;
		return 0;
	}
	if (m == 1) {
		if (k == 1) {
			cout << -1 << endl;
			return 0;
		}
	}
	if (k == 0) {
		for (int i = 0; i < n / 2; ++i) {
			xs[i] = xs[n - 1 - i] = i;
		}
	} else {
		int l = (n - 6) / 2;
		int y = 0;
		while (y < n / 2 && (y == k || (y ^ k) == k)) ++y;
		if (y == n / 2) throw new runtime_error("");
		xs[l + 0] = xs[l + 2] = y;
		xs[l + 1] = xs[l + 4] = k;
		xs[l + 3] = xs[l + 5] = y ^ k;
		int x = 0;
		for (int i = 0; i < l; ++i) {
			xs[i] = xs[n - 1 - i] = x;
			++x;
			while (x == y || x == (y ^ k) || x == k)
				++x;
		}
	}
	print();
	return 0;
}
