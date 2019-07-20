#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
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
	int n, m;
	cin >> n >> m;
	vi ret(n, 0);
	vector<pii> seg0, seg1, seg3;
	while (m--) {
		int t, l, r;
		cin >> t >> l >> r;
		l--, r--;
		if (t == 0) seg0.pb({l, r});
		if (t == 1) seg1.pb({l, r});
	}
	sort(all(seg0));
	sort(all(seg1));
	for (int i = 0; i < size(seg1);) {
		int r = seg1[i].se;
		int j = i + 1;
		while (j < size(seg1) && seg1[j].fi <= r) r = max(r, seg1[j].se), j++;
		seg3.pb({seg1[i].fi, r});
		i = j;
	}
	assert(is_sorted(all(seg3)));
	for (int i = 0, j = 0; i < size(seg3);) {
		while (j < size(seg0) && seg0[j].se <= seg3[i].fi) j++;
		while (j < size(seg0) && seg0[j].fi <= seg3[i].se) {
			if (seg3[i].fi <= seg0[j].fi && seg0[j].se <= seg3[i].se) {
				cout << "NO" << endl;
				return 0;
			}
			j++;
		}
		i++;
	}
	for (int i = 0, d = n, j = 0; i < n;) {
		if (j < size(seg3)) {
			while (i < seg3[j].fi) ret[i++] = d--;
			while (i <= seg3[j].se) ret[i++] = d;
			d--;
			j++;
		} else {
			while (i < n) ret[i++] = d--;
		}
	}
	cout << "YES\n";
	for (int x : ret) cout << x << ' ';
	cout << endl;
	return 0;
}
