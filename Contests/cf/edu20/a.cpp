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
	int n, k; cin >> n >> k;
	if (n * n < k) {
		cout << -1 << endl;
		return 0;
	}
	vvi r(n, vi(n, 0));
	int kk = k;
	for (int i = 0; i < n; ++i)
		for (int j = i; j < n; ++j) {
			if (i == j && kk) {
				r[i][j] = 1;
				kk--;
			} else if (i < j && kk > 1) {
				r[i][j] = r[j][i] = 1;
				kk -= 2;
			}
		}
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j)
			cout << r[i][j] << ' ';
		cout << '\n';
	}
	return 0;
}
