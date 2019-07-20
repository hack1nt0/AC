#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<llt> vll;
typedef vector<vi> vvi;
typedef pair<int, int> pii;
#define pb push_back
#define all(x) x.begin(),x.end()
#define fi first
#define se second
#define size(x) int(x.size())

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	llt n, k; cin >> n >> k;
	vll divs;
	for (llt d = 1; d * d <= n; ++d) if (n % d == 0) {
		divs.pb(d);
		if (d != n / d) divs.pb(n / d);
	}
	sort(all(divs));
	double sum = k * (1.0 + k) / 2;
	if (sum > n) {
		cout << -1 << endl;
		return 0;
	}
	//d * m = n;
	//m >= k * (1 + k) / 2
	llt d = 0;
	for (int i = size(divs) - 1; i >= 0; --i) {
		if (n / divs[i] >= sum) {
			d = divs[i];
			break;
		}
	}
	if (!d) {
		cout << -1 << endl;
		return 0;
	}
	vll r(k);
	for (llt i = 0, left = n / d; i < k; ++i) {
		if (i == k - 1)
			r[i] = left * d;
		else {
			r[i] = (i + 1) * d;
			left -= i + 1;
		}
	}
	for (auto x : r) cout << x << ' ';
	cout << endl;
	return 0;
}
