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

//WA
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
	int n; cin >> n;
	vi a(n);
	llt s = 0;
	for (int i = 0; i < n; ++i) cin >> a[i], s += a[i];
	bool win;
	if (s <= 1) {
		win = false;
	} else {
		win = s % 2 == 0;
	}
	cout << (win ? "sjfnb" : "cslnb") << endl;
	return 0;
}
