#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include <sys/resource.h>
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<llt> vl;
typedef vector<vi> vvi;
typedef vector<vl> vvl;
typedef pair<int, int> pii;
typedef pair<llt, llt> pll;
#define pb push_back
#define all(x) x.begin(),x.end()
#define fi first
#define se second
#define sz(x) int(x.size())

#include "debug.h"
#include "costflow.h"

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
	graph g(4);
	g.adde(0, 1, 1, 1);
	g.adde(0, 2, 2, 1);
	g.adde(1, 3, 2, 1);
	g.adde(2, 3, 2, 1);
	debug(g.mincost(0, 3, 10));
	return 0;
}
