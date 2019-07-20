#include <bits/stdc++.h>
using namespace std;

//WA
const int maxn = (int) 3e5 + 3;
int xs[maxn];
int where[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int n; cin >> n;
	for (int i = 1; i <= n; ++i) {
		cin >> xs[i];
		where[xs[i]] = i;
	}
	vector<pair<int, int>> ans;
	for (int i = 1; i <= n; ++i) if (where[i] != i) {
		if (where[i] - i >= n / 2) {
			ans.push_back({i, where[i]});
			where[xs[i]] = where[i];
		} else {
			if (n - where[i] >= n / 2) {
				ans.push_back({where[i], n});
				ans.push_back({i, n});
				where[xs[n]] = where[i];
				where[xs[i]] = n;
			} else if (i - 1 >= n / 2) {
				ans.push_back({1, where[i]});
				ans.push_back({1, i});
				ans.push_back({1, where[i]});
				where[xs[i]] = where[i];
			} else {
				cerr << n << " " << i << " " << where[i] << endl;
				assert(false);
			}
		}
		where[i] = i;
	}
	cout << ans.size() << "\n";
	for (auto e : ans) 
		cout << e.first << " " << e.second << "\n";
	return 0;
}
