#include <bits/stdc++.h>
using namespace std;

const int maxn = (int) 3e5 + 3;

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int n; cin >> n;
	vector<pair<pair<int, int>, int>> xs, ys;
	for (int i = 0; i < n; ++i) {
		int a, b;
		cin >> a >> b;
		if (a < b) {
			xs.push_back({{a, b}, i + 1});
		} else {
			ys.push_back({{a, b}, i + 1});
		}
	}
	if (xs.size() > ys.size()) {
		cout << xs.size() << "\n";
		sort(xs.begin(), xs.end(), [&](const auto& i, const auto& j) { return i.first.second > j.first.second; });
		for (auto& e : xs) cout << e.second << " ";
		cout << endl;
	} else {
		cout << ys.size() << "\n";
		sort(ys.begin(), ys.end(), [&](const auto& i, const auto& j) { return i.first.second < j.first.second; });
		for (auto& e : ys) cout << e.second << " ";
		cout << endl;
	}
	return 0;
}
