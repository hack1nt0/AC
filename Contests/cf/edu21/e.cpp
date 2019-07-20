#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include <sys/resource.h>
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<llt> vll;
typedef vector<vi> vvi;
typedef vector<vll> vvll;
typedef pair<int, int> pii;
#define pb push_back
#define all(x) x.begin(),x.end()
#define fi first
#define se second
#define size(x) int(x.size())

struct solver {
	void input(istream& is = cin) {
	}
	void solve() {
	}
	void print(ostream& os = cout) {
	}
};

int main(int argc, char* args[]) {
#ifdef LOCAL
	struct rlimit rlim;
	getrlimit(RLIMIT_STACK, &rlim);
	rlim.rlim_cur = 256 * 1024 * 1024;
	setrlimit(RLIMIT_STACK, &rlim);
	getrlimit(RLIMIT_DATA, &rlim);
	rlim.rlim_cur = 256 * 1024 * 1024;
	setrlimit(RLIMIT_DATA, &rlim);
#endif
	cin.sync_with_stdio(0);
	cin.tie(0);
	cout.precision(10);
	cout << fixed;
	int n, m; cin >> n >> m;
	vi w(n), c(n);
	for (int i = 0; i < n; ++i) cin >> w[i] >> c[i];
	vll psum[4];
	for (int i = 0; i < n; ++i) psum[w[i]].pb(c[i]);
	for (int i = 1; i < 4; ++i) {
		sort(all(psum[i]), greater<llt>());
		for (int j = 1; j < size(psum[i]); ++j) psum[i][j] += psum[i][j - 1];
	}
	auto g = [&psum](int x, int y, int z)->llt {
		llt gx = (0 < x && x <= size(psum[1])) ? psum[1][x - 1] : 0;
		llt gy = (0 < y && y <= size(psum[2])) ? psum[2][y - 1] : 0;
		llt gz = (0 < z && z <= size(psum[3])) ? psum[3][z - 1] : 0;
		//debug(x, y, z);
		return gx + gy + gz;
	};
	auto f = [&](int x) {
		llt r = 0;
		for (int y = 0; y <= min(size(psum[2]), n - x) && m - x - 2 * y >= 0; ++y) {
			int z = min(min(n - x - y, size(psum[3])), (m - x - 2 * y) / 3);
			z = max(z, 0);
			r = max(r, g(x, y, z));
		}
		return r;
	};
	int lx = 0, rx = min(m, min(n, size(psum[1]))) + 1;
	while (lx + 2 < rx) {
		int x1 = lx + (rx - lx) / 3;
		int x2 = rx - (rx - lx) / 3;
		llt y1 = f(x1), y2 = f(x2);
		//debug(x1, x2);
		//debug(y1, y2);
		if (y1 <= y2) 
			lx = x1 + 1;
		else
			rx = x2;
	}
	llt ret = 0;
	while (lx < rx) ret = max(ret, f(lx++));
	cout << ret << endl;
	auto e = {1, 2};
	*e.begin() = 0;
	cout << min(e) << endl;
	return 0;
}
