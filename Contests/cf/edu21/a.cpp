#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include <sys/resource.h>
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<llt> vl;
typedef vector<vi> vvi;
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
	int n; cin >> n;
	vi xs;
	int x = n;
	while (x) {
		xs.pb(x % 10);
		x /= 10;
	}
	xs.back() += 1;
	fill(xs.begin(), xs.end() - 1, 0);
	if (xs.back() == 10) {
		xs.back() = 0;
		xs.pb(1);
	}
	auto d = [](vi xs) {
		int r = 0;
		for (int i = size(xs) - 1; i >= 0; --i)
			r = r * 10 + xs[i];
		return r;
	};
	cout << d(xs) - n << endl;
	return 0;
}
