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
	sort(all(a));
	vi r(n);
	r[0] = a[n - 1];
	r[1] = a[n - 2];
	r[2] = a[0];
	for (int i = 1; i < n - 2; ++i) r[2 + i] = a[i];
	bool ok = true;
	for (int i = 0; i < n; ++i) {
		int x = r[(i - 1 + n) % n];
		int y = r[(i + 1 + n) % n];
		ok &= x + y > r[i];
	}
	if (ok) {
		cout << "YES\n";
		for (auto x : r) cout << x << ' ';
		cout << endl;
	} else {
		cout << "NO\n";
	}
	return 0;
}
