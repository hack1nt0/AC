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
	for (int i = 0; i < n; ++i) {
		cin >> a[i];
	}
	vi r(n, n);
	for (int i = 0, pz = -1; i < n; ++i) {
		if (!a[i]) pz = i;
		if (pz >= 0) r[i] = min(r[i], i - pz);
	}
	for (int i = n - 1, pz = -1; i >= 0; --i) {
		if (!a[i]) pz = i;
		if (pz >= 0) r[i] = min(r[i], pz - i);
	}
	for (int x : r) cout << x << ' ';
	cout << endl;
	return 0;
}
