#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 3e5 + 4;
typedef long long llt;
int xs[maxn];
llt sum[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	
	int n, k;
	cin >> n >> k;
	for (int i = 0; i < n; ++i) cin >> xs[i];
	sum[n] = 0;
	for (int i = n - 1; i >= 0; --i)
		sum[i] = sum[i + 1] + xs[i];
	llt ans = sum[0];
	sort(sum + 1, sum + n);
	for (int i = 0; i < k - 1; ++i)
		ans += sum[n - 1 - i];
	cout << ans << endl;
	return 0;
}
