#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<bool> vb;
typedef vector<llt> vll;
typedef vector<vi> vvi;
typedef vector<vll> vvll;
typedef pair<int, int> pii;
#define pb push_back
#define all(x) x.begin(),x.end()
#define fi first
#define se second
#define size(x) int(x.size())

const int mod = 1e9 + 7;
	
int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n;
	cin >> n;
	map<int, int> a;
	int maxn = 0;
	for (int i = 0; i < n; ++i) {
		int x; cin >> x;
		maxn = max(maxn, x);
		a[x]++;
	}
	maxn++;
	vi u(maxn, 1);
	vi c(maxn, 0);
	vi prime(maxn, true);
	for (int i = 2; i < maxn; ++i) if (prime[i]) {
		u[i] = -1;
		for (int j = i + i; j < maxn; j += i) {
			u[j] *= -1;
			if (j % (1LL * i * i) == 0) u[j] = 0;
			prime[j] = false;
		}
	}
	for (int i = 1; i < maxn; ++i) {
		for (int j = i; j < maxn; j += i) if (a.count(j)) {
			c[i] += a[j];
		}
	}
	assert(c[1] == n);
	debug(u, c);
	auto g = [&c](int d) {
		llt r = 1, p = c[d], acc = 2;
		while (p) {
			if (p & 1) r = r * acc % mod;
			p >>= 1;
			acc = acc * acc % mod;
		}
		return r - 1;
	};
	llt ret = 0;
	for (int i = 1; i < maxn; ++i) {
		debug(u[i], g(i));
		ret += u[i] * g(i) % mod;
		if (ret >= mod) ret -= mod;
		if (ret < 0) ret += mod;
	}
	cout << ret << endl;
	return 0;
}
