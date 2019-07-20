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

void check(int maxn) {
	for (int i = 1; i < maxn; ++i) if (maxn % i) {
		set<int> st;
		int j = i;
		while (!st.count(j)) {
			st.insert(j);
			j += i;
			if (j >= maxn) j -= maxn;
		}
		//debug(maxn, i, st);
		//assert(size(st) == maxn);
	}
}
//TLE
int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n; cin >> n;
	string y; cin >> y;
	/*
	vi divs;
	for (int d = 1; d * d <= n; ++d) if (n % d == 0) {
		divs.pb(d);
		if (d != n / d) divs.pb(n / d);
	}
	*/
	//debug(size(divs));
	int ret = 0;
	if (count(all(y), '1') % 2) {

	} else {
		for (int k = 1; k <= n; ++k) {
			int ng = __gcd(k, n); 
			if (ng == 1)
				ret++;
			else {
				bool ok = true;
				for (int i = 0; i < ng; ++i) {
					int j = i, n1 = 0;
					while (true) {
						if (y[j] == '1') n1++;
						j += k;
						if (j >= n) j -= n;
						if (j == i) break;
					}
					ok &= n1 % 2 == 0;
				}
				if (ok) ret++;
			}
		}
	}
	cout << ret << endl;
	return 0;
}
