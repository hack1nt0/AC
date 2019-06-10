#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
const int maxn = (int) 2e5 + 4;
int a[maxn];
int b[maxn];
int n;
bool seta[maxn];

bool sat(int mid) {
	int pb = 0;
	for (int i = 1; i <= n; ++i) seta[i] = false;
	for (int i = 0; i < n; ++i) seta[a[i]] = true;
	while (pb < mid) seta[b[pb++]] = true;
	int top = 1;
	if (mid == 0) {
		int p1 = -1;
		for (int i = 0; i < n; ++i) if (b[i] == 1) p1 = i;
		if (p1 != -1) {
			bool inc = true;
			for (int i = p1 + 1; i < n; ++i) inc &= b[i] == b[i - 1] + 1;
			if (!inc) {
				return false;
			} else {
				top = b[n - 1] + 1;
			}
		}
	}
	for (; top <= n; ++top) {
		if (!seta[top]) {
			return false;
		}
		seta[top] = false;
		if (pb < n)
			seta[b[pb++]] = true;
	}
	return true;
}
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	cin >> n;
	int nz = 0;
	for (int i = 0; i < n; ++i) {
		cin >> a[i];
		if (a[i] == 0) ++nz;
	}
	for (int i = 0; i < n; ++i) cin >> b[i];

	if (nz == 0) {
		cout << n << endl;
		return 0;
	}
	if (nz == n) {
		bool ok = true;
		for (int i = 0; i < n; ++i) ok &= b[i] == i + 1;
		if (ok) {
			cout << 0 << endl;
			return 0;
		}
	} 
	if (sat(0)) {
		cout << n - b[n - 1] << endl;
		return 0;
	}
	int l = 0, r = n;
	while (l + 1 < r) {
		int mid = r - (r - l) / 2;
		if (sat(mid))
			r = mid;
		else
			l = mid;
	}
	cout << r + n << endl;
	return 0;
}
