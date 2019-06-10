#include <bits/stdc++.h>
using namespace std;


typedef long long llt;
llt dp[(1 << 18) + 2][100 + 3];
llt fact[20];

int main() {
	llt n; int m;
	cin >> n >> m;
	vector<int> xs;
	{
		llt nn = n;
		while (nn > 0) {
			xs.push_back(nn % 10);
			nn /= 10;
		}
	}
	int nx = xs.size();
	///for (int i = 0; i < nx; ++i) cout << xs[i] << " " ;
	///cout << endl;
	for (int i = 0; i < 1 << nx; ++i)
		for (int j = 0; j < m; ++j) 
			dp[i][j] = 0;
	dp[0][0] = 1;
	for (int i = 0; i < 1 << nx; ++i)
		for (int j = 0; j < m; ++j) if (dp[i][j] != 0) {
			for (int k = 0; k < nx; ++k) if ((i & 1LL << k) == 0) {
				if (i == 0 && xs[k] == 0) 
					continue;
				dp[i | 1LL << k][(j * 10 + xs[k]) % m] += dp[i][j];
			}
		}
	llt ans = dp[(1LL << nx) - 1][0]; 
	map<int, int> xs2;
	for (int x : xs) xs2[x]++;
	fact[0] = 1;
	for (int i = 1; i < 20; ++i) fact[i] = fact[i - 1] * i;
	for (auto e : xs2) ans /= fact[e.second];
	cout << ans << endl;
	return 0;
}
