#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
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

const int maxn = 1000 + 10;
bool cache[maxn][maxn * 2];
int n, k;
string s, r;
char c(int i) {
	if (i == -1) return 'L';
	if (i == 0) return 'D';
	if (i == +1) return 'W';
}
bool dfs(int i, int d) {
	if (i == n) {
		//debug(r, d);
		return abs(d) == k;
	}
	if (abs(d) == k)
		return false;
	if (cache[i][d + k])
		 return false;
	switch (s[i]) {
		case 'L' :
			r[i] = 'L';
			return dfs(i + 1, d - 1);
		case 'W' :
			r[i] = 'W';
			return dfs(i + 1, d + 1);
		case 'D' :
			r[i] = 'D';
			return dfs(i + 1, d);
		case '?' :
			for (int j = -1; j <= +1; ++j)
				if ((r[i] = c(j)) && dfs(i + 1, d + j))
					return true;
	}
	cache[i][d + k] = true;
	return false;
}
	
int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	cin >> n >> k;
	cin >> s;
	r.resize(n);
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < k + k; ++j)
			cache[i][j] = false;
	if (dfs(0, 0))
		cout << r << endl;
	else 
		cout << "NO" << endl;
	return 0;
}
