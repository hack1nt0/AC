#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 1e5 + 3;
int xs[maxn];
int dp[maxn][3];
int nx[maxn][3];

void umax(int& x, int y) {
	x = max(x, y);
}
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	string d; cin >> d;
	int n = d.size();
	int n0 = 0;
	for (int i = 0; i < n; ++i) {
		xs[i] = d[i] - '0';
		if (xs[i] == 0) ++n0;
	}
	for (int i = 0; i <= n; ++i)
		for (int j = 0; j < 3; ++j)
			dp[i][j] = nx[i][j] = -1;
	for (int i = 0; i < n; ++i) {
		if (xs[i] != 0) {
			int ni = i + 1;
			int nj = xs[i] % 3;
			if (1 > dp[ni][nj]) {
				dp[ni][nj] = 1;
				nx[ni][nj] = i * 3 + 0;
			}
		}
		for (int j = 0; j < 3; ++j) if (dp[i][j] != -1) {
			{
				int ni = i + 1;
				int nj = (j + xs[i]) % 3;
				if (dp[i][j] + 1 > dp[ni][nj]) {
					dp[ni][nj] = dp[i][j] + 1;
					nx[ni][nj] = i * 3 + j;
				}
			}
			{
				int ni = i + 1;
				int nj = j;
				if (dp[i][j] > dp[ni][nj]) {
					dp[ni][nj] = dp[i][j];
					nx[ni][nj] = nx[i][j];
				}
			}
		}
	}
	int ma = dp[n][0];
	if (ma == -1) {
		if (n0 > 0) ma = 0;
		cout << ma << endl;
	} else {
		vector<int> ans;
		for (int i = n, j = 0; nx[i][j] != -1;) {
			int pi = nx[i][j] / 3;
			int pj = nx[i][j] % 3;
			ans.push_back(xs[pi]);
			i = pi, j = pj;
		}
		reverse(ans.begin(), ans.end());
		//cerr << ans.size() << " " << ma << endl;
		assert(ans.size() == ma);
		for (int i = 0; i < ma; ++i) cout << ans[i];
		cout << endl;
	}
	return 0;
}
