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
	int n, k; cin >> n >> k;
	vi a(n);
	for (int i = 0; i < n; ++i) cin >> a[i];
	vll psum(n);
	for (int i = 0; i < n; ++i) psum[i] = a[i] + (i > 0 ? psum[i - 1] : 0);
	llt x = 0;
	for (int i = k - 1; i < n; ++i)
		x += psum[i] - (i - k >= 0 ? psum[i - k] : 0);
	cout << x / (n - k + 1.0) << endl;
	return 0;
}
