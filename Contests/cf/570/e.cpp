#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<vi> vvi;

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n, k;
	string s;
	cin >> n >> k >> s;
	int ret = 0;
	vvi comb(n + 1, vi(n + 1));
	for (int i = 0; i <= n; ++i)
		for (int j = 0; j <= n; ++j) {
			int& r = comb[i][j];
			if (j == 0) r = 1;
			else if (i < j) r = 0;
			else r = comb[i - 1][j - 1] + comb[i - 1][j];
		}
	for (int len = n; len >= 0; --len) {
		vi right('z' + 1, -1);
		vvi dp(n + 1, vi(len + 1, 0));
		dp[0][0] = 1;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j <= len; ++j) {
				dp[i + 1][j] = (j > 0 ? dp[i][j - 1] : 0) + dp[i][j];
				if (right[s[i]] != -1) {
					assert(right[s[i]] < i);
					dp[i + 1][j] -= dp[right[s[i]]][j - 1];
				}
			}
			right[s[i]] = i;
		}
		int tot = dp[n][len];
		assert(tot > 0);
		int del = min(k, tot);
		k -= del;
		ret += (n - len) * del;
		if (k == 0) break;
	}
	if (k > 0) ret = -1;
	cout << ret << endl;
	return 0;
}
