#include <bits/stdc++.h>
using namespace std;
class CentipedeSocks {
public:
	int sum(vector<int>& xs) {
		int r = 0;
		for (int x : xs) r += x;
		return r;
	}
	int fewestSocks(int C, int F, vector <int> sockCounts) {
		int n = sockCounts.size();
		vector<int> cnt = sockCounts;
		sort(cnt.begin(), cnt.end(), greater<int>());
		vector<int> used(n, 0);
		for (int i = 0; i < n; ++i) {
			int x = min(cnt[i], F - 1);
			cnt[i] -= x;
			used[i] += x;
		}
		int pets = 0;
		while (true) {
			if (pets >= C) break;
			bool az = true;
			for (int c : cnt) az &= c == 0;
			if (az) break;
			for (int i = 0; i < n; ++i) {
				if (cnt[i] > 0) {
					pets++;
					if (pets == C) {
						used[i]++;
						break;
					} else {
						int x = min(cnt[i], F);
						cnt[i] -= x;
						pets++;
						used[i] += x;
					}
				}
			}
		}
		return pets < C ? -1 : sum(used);
	}
};
