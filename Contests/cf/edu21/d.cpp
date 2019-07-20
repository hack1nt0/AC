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
	int n; cin >> n;
	vi a(n);
	for (int i = 0; i < n; ++i) cin >> a[i];
	llt sum = 0;
	for (int x : a) sum += x;
	if (n < 2 || sum % 2) {
		cout << "NO" << endl;
		return 0;
	}
	bool ok = false;
	llt lsum = 0;
	set<llt> lset;
	llt half = sum / 2;
	map<llt, int> rmap;
	for (int x : a) rmap[x]++;
	for (int i = 0; i <= n; ++i) {
		if (lsum == half
				|| lsum > half && lset.count(lsum - half)
				|| lsum < half && rmap[half - lsum]
				) {
			//debug(sum / 2, lsum, lset);
			ok = true;
			break;
		}
		if (i == n) break;
		lsum += a[i];
		lset.insert(a[i]);
		rmap[a[i]]--;
	}

	cout << (ok ? "YES" : "NO") << endl;
	return 0;
}
