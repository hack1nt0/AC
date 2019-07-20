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
	int n, w; cin >> n >> w;
	vi a(n);
	for (int i = 0; i < n; ++i) cin >> a[i];
	int lb = 0;
	for (int i = 0; i < n; ++i) lb += (a[i] + 1) / 2;
	if (w < lb) {
		cout << -1 << endl;
		return 0;
	}
	vi ptr(n);
	for (int i = 0; i < n; ++i) ptr[i] = i;
	sort(all(ptr), [&a](int i, int j) { return a[i] > a[j]; });
	vi r(n);
	for (int i = 0, ww = w - lb; i < n; ++i) {
		int pi = ptr[i];
		int half = (a[pi] + 1) / 2;
		r[pi] = half;
		int used = min(ww, a[pi] - half);
		r[pi] += used;
		ww -= used;
	}
	
	for (int x : r) cout << x << ' ';
	cout << endl;
	return 0;
}
