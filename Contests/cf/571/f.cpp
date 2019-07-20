#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<pair<int, int>> vpi;


int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n, m;
	cin >> n >> m;
	vi deg(n, 0);
	vpi es(m);
	for (int i = 0; i < m; ++i) {
		int x, y; cin >> x >> y; x--, y--;
		deg[x]++, deg[y]++;
		es[i] = {x, y};
	}
	for (int i = 0; i < n; ++i) deg[i] = (deg[i] + 1) / 2;
	vpi ret;
	for (int i = 0; i < m; ++i) if (deg[es[i].first] && deg[es[i].second]) {
		ret.push_back(es[i]);
		deg[es[i].first]--;
		deg[es[i].second]--;
	}
	auto dec = [](int& x) { if (x > 0) --x; };
	for (int i = 0; i < m; ++i) if (deg[es[i].first] || deg[es[i].second]) {
		ret.push_back(es[i]);
		dec(deg[es[i].first]);
		dec(deg[es[i].second]);
	}
	bool ok = ret.size() <= (n + m + 1) / 2;
	for (int d : deg) ok &= !d;
	assert(ok);
	cout << ret.size() << "\n";
	for (auto e : ret) {
		cout << e.first + 1 << " " << e.second + 1 << "\n";
	}
	return 0;
}
