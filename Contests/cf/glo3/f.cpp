#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 3e5 + 3;
typedef long long llt;
llt val[maxn];
llt mask[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int n; cin >> n;
	dp[0] = 0;
	for (int i = 0; i < n; ++i) {
		cin >> val[i] >> mask[i];
		dp[0] += val[i];
	}
	for (int i 
	return 0;
}
