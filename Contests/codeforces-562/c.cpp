#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 3e5;
int xs[maxn];
int n, m;
bool sat(int maxo) {
	bool ok = true;
	int pre = 0;
	for (int i = 0; i < n;) {
		vector<pair<int, int>> segs;
		if (xs[i] + maxo < m) {
			int l = xs[i], r = l + maxo + 1;
			segs.push_back({l, r});
		} else {
			{
				int l = 0, r = maxo - (m - 1 - xs[i]) -1 + 1;
				segs.push_back({l, r});
			}
			{
				int l = xs[i], r = m;
				segs.push_back({l, r});
			} 
		}
		for (auto seg : segs) {
			if (seg.first <= pre && pre < seg.second) {
				goto goon;
			}
			if (pre < seg.first) {
				pre = seg.first;
				goto goon;
			}
		}
		ok = false;
		break;
goon: 		++i;
	}
	return ok;
}
	
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; ++i) cin >> xs[i];
	int l = -1, r = m;
	while (l + 1 < r) {
		int mid = r - (r - l) / 2;
		if (sat(mid))
			r = mid;
		else 
			l = mid;
	}
	cout << r << endl;
	return 0;
}
