#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<llt> vl;
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
	int n; cin >> n;
	vi a(n);
	for (int i = 0; i < n; ++i) cin >> a[i];
	int nq; cin >> nq;
	vvi dp(n, vi(20));
	vvi val(n, vi(20));
	for (int i = 0; i < 20; ++i) {
		for (int j = 0; j + (1 << i) <= n; ++j) {
			int& r = dp[j][i];
			int& v = val[j][i];
			if (i == 0) r = 0, v = a[j];
			else {
				r = dp[j][i - 1] + dp[j + (1 << i - 1)][i - 1];
				v = val[j][i - 1] + val[j + (1 << i - 1)][i - 1];
				if (v >= 10) {
					v -= 10;
					r += 1;
				}
			}
		}
	}
	auto ispow2 = [](int x) { return __builtin_popcount(x) == 1; };
	while (nq--) {
		int l, r; cin >> l >> r;
		int len = log2(r - l + 1);
		//debug(l, r, len);
		cout << dp[l - 1][len] << '\n';
	}
	return 0;
}
