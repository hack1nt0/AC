#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
const int maxn = (int) 3e6;
bool isp[maxn];
int minf[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n;
	cin >> n;
	vector<int> primes;
	for (int i = 0; i < maxn; ++i) {
		minf[i] = i;
		isp[i] = true;
	}
	for (int i = 2; i < maxn; ++i) if (isp[i]) {
		primes.push_back(i);
		for (int j = i + i; j < maxn; j += i) {
			minf[j] = min(minf[j], i);
			isp[j] = false;
		}
	}
	map<int, int> pxs, oxs;
	for (int i = 0; i < 2 * n; ++i) {
		int d;
		cin >> d;
		if (isp[d]) {
			pxs[d]++;
		} else {
			oxs[d]++;
		}
	}
	auto del = [](map<int, int>& mp, int key)->bool {
		auto i = mp.find(key);
		if (i == mp.end()) return false;
		if (--i->second == 0) mp.erase(i);
		return true;
	};
	vector<int> ret;
	while (!oxs.empty()) {
		int x = (*--oxs.end()).first;
		int y = x / minf[x];
		assert(del(oxs, x));
		assert(del(oxs, y) || del(pxs, y));
		ret.push_back(x);
	}
	while (!pxs.empty()) {
		int x = (*pxs.begin()).first;
		int y = primes[x - 1];
		assert(del(pxs, x));
		assert(del(pxs, y));
		ret.push_back(x);
	}
	assert((int)ret.size() == n);

	for (int i = 0; i < n; ++i) cout << ret[i] << " ";
	cout << endl;
	return 0;
}
