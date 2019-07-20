#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	
	llt a, b, c; 
	cin >> a >> b >> c;
	if (a < b)
		swap(a, b);
	llt ans = b * 2 + (a > b ? 1 : 0);
	ans += c * 2;
	cout << ans << endl;
	return 0;
}
