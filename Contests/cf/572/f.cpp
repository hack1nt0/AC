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
#define loop(i,l,r) for (int i = l; i < r; ++i)

const int maxn = 1010;
int n, k;
int a[maxn];
llt dp[maxn][maxn];

llt f(int d) {
	for (int i = 1; i <= k; ++i) {
		int pj = 0;
		for (int j = 0; j < n; ++j) {
			llt& r = dp[i][j];
			r = 0;
			if (i == 1) r += 1;
			if (j > 0) r += dp[i][j - 1];
			while (pj < j && a[j] - a[pj] > d) pj++;
			if (a[j] - a[pj] < d) pj--;
			pj = max(pj, 0);
			if (a[j] - a[pj] >= d && i > 1) r += dp[i - 1][pj];
		}
	}
	llt y = dp[k][n - 1];
	for (int i = 1; i <= k; ++i) {
		int pj = 0;
		for (int j = 0; j < n; ++j) {
			llt& r = dp[i][j];
			r = 0;
			if (i == 1) r += 1;
			if (j > 0) r += dp[i][j - 1];
			while (pj < j && a[j] - a[pj] > d) pj++;
			if (a[j] - a[pj] <= d) pj--;
			pj = max(pj, 0);
			if (a[j] - a[pj] > d && i > 1) r += dp[i - 1][pj];
		}
	}
	llt x = dp[k][n - 1];
	if (y)
	debug(d, y, x);
	return y - x;
}
int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> k;
	loop(i, 0, n) cin >> a[i];
	sort(a, a + n);

	llt ret = 0;
	for (int i = 0; i <= k; ++i)
		for (int j = 0; j < n; ++j) dp[i][j] = 0;
	loop(i, 1, (maxn - 1) / (k - 1)) ret += f(i) * i;
	cout << ret << endl;
	return 0;
}
