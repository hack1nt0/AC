#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;
	map<int, int> id;
	id[4] = 0;
	id[8] = 1;
	id[15] = 2;
	id[16] = 3;
	id[23] = 4;
	id[42] = 5;
	vector<set<int>> ps(6);
	for (int i = 0; i < n; ++i) {
		int d;
		cin >> d;
		ps[id[d]].insert(i);
	}
	int goods = 0;
	int seq[10];
	while (true) {
		for (auto s : ps) if (s.empty()) break;
		bool stop = false;
		for (int i = 0; i < 6; ++i) {
			auto x = i == 0 ? ps[0].begin() : upper_bound(ps[i].begin(), ps[i].end(), seq[i - 1]);
			if (x == ps[i].end()) {
				stop = true;
				break;
			}
			seq[i] = *x;
			ps[i].erase(x);
		}
		if (stop) break;
		else goods++;
	}
	cout << n - goods * 6 << "\n";

	return 0;
}
