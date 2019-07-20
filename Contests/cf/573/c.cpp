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
	llt n, m, k; cin >> n >> m >> k;
	vll p(m);
	for (int i = 0; i < m; ++i) cin >> p[i], p[i]--;
	int ret = 0;
	int ptr = 0;
	while (ptr < m) {
		llt x1 = p[ptr] - ptr;
		int ptr2;
		{
			int l = ptr, r = m;
			while (l + 1 < r) {
				int mid = l + (r - l) / 2;
				if (p[mid] - p[ptr] <= k - 1 - x1 % k)
					l = mid;
				else
					r = mid;
			}
			ptr2 = l;
		}
		ret++;
		ptr = ptr2 + 1;
	}
	cout << ret << endl;
	return 0;
}
