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

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n; cin >> n;
	string s; cin >> s;
	vi cnt(2, 0);
	for (char c : s) cnt[c - '0']++;
	if (cnt[0] == cnt[1]) {
		cout << 2 << "\n" << s.substr(0, n - 1) << ' ' << s[n - 1] << '\n';
	} else {
		cout << 1 << '\n' << s << '\n';
	}
	return 0;
}
