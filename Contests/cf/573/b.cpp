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
	const int n = 3;
	map<pair<char, char>, int> hs;
	for (int i = 0; i < n; ++i) {
		string h; cin >> h;
		hs[{h[0], h[1]}]++;
	}
	int ret = 3;
	for (auto e : hs) {
		auto h = e.fi;
		ret = min(ret, n - hs[h]);
		for (int i = -2; i <= 0; ++i) {
			int x = 0;
			for (int j = h.fi + i; j <= h.fi + i + 2; ++j)
				if (hs.count({j, h.se})) x++;
			ret = min(ret, n - x);
		}
	}
	cout << ret << endl;
	return 0;
}
