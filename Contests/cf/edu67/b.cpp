#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../../libs/debug.h"
#endif

typedef long long llt;
typedef vector<int> vi;
typedef vector<vi> vvi;
#define pb push_back
#define all(x) x.begin(),x.end()


int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n, m;
	string s;
	cin >> n >> s >> m;
	vvi pos(27);
	for (int i = 0; i < n; ++i) pos[s[i] - 'a'].pb(i);
	vi cnt(27);
	while (m-- > 0) {
		string t; cin >> t;
		fill(all(cnt), 0);
		for (char c : t) cnt[c - 'a']++;
		int ret = 0;
		for (int i = 0; i < 27; ++i) if (cnt[i])
			ret = max(ret, pos[i][cnt[i] - 1]);
		cout << ret + 1 << "\n";
	}
	return 0;
}
