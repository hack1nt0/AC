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

//WA
int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n; cin >> n;
	vi a(n);
	for (int i = 0; i < n; ++i) cin >> a[i];
	int nq; cin >> nq;
	while (nq--) {
		int p, k;
		cin >> p >> k;
		int l = -1, r = 20;
		while (l + 1 < r) {
			int m = r - (r - l) / 2;
			llt x = m == 0 ? p : p * (1LL << (m - 1)) + (a[p] + k) * m;
			//debug(nq, m, x);
			if (n < x) r = m;
			else l = m;
		}
		cout << r << "\n";
	}
	return 0;
}
