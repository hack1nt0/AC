#include <bits/stdc++.h>
using namespace std;

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n, k;
	cin >> n >> k;
	vector<int> a(k);
	for (int i = 0; i < k; ++i) cin >> a[i];
	vector<int> xs(n);
	for (int i = 0; i < n; ++i) xs[i] = i + 1;
	for (int i = 0, h = 0; i < k; ++i) {
		int j = h;
		int cnt = 0;
		while (cnt < a[i] % (n - i)) {
			j = (j + 1) % n;
			while (!xs[j]) j = (j + 1) % n;
			++cnt;
		}
		cout << xs[j] << " ";
		xs[j] = 0;
		j = (j + 1) % n;
		while (!xs[j]) j = (j + 1) % n;
		h = j;
	}
	cout << endl;
	return 0;
}
