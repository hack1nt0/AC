#include <bits/stdc++.h>
using namespace std;

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	string s, t;
	cin >> s >> t;
	int ns = s.size(), nt = t.size();
	vector<int> ss('1' + 1, 0), st('1' + 1, 0);
	for (int i = 0; i < ns; ++i) ++ss[s[i]];
	/*
	for (int i = 0; i < nt; ++i) ++st[t[i]];
	if (ns < nt || !(ss['0'] >= st['0'] && ss['1'] >= st['1'])) {
		cout << s << endl;
		return 0;
	}
	*/
	string ans(ns, 0);
	int no = 0;
	vector<int> back(nt + 1, 0);
	for (int i = 2; i <= nt; ++i) {
		int b = back[i - 1];
		while (b > 0 && t[i - 1] != t[b])
			b = back[b];
		if (t[i - 1] == t[b])
			++b;
		back[i] = b;
	}
	for (int i = 0, pt = 0; i < ns; ++i) {
		while (pt > 0 && ss[t[pt]] == 0)
			pt = back[pt];
		char c = t[pt];
		if (ss[c] > 0) {
			--ss[c];
			ans[i] = c;
			++pt;
		} else {
			for (c = '0'; c <= '1'; ++c) if (ss[c] > 0) {
				--ss[c];
				ans[i] = c;
				break;
			}
		}
		if (pt == nt) {
			pt = back[pt];
			++no;
		}
	}
	//cerr << no << endl;
	cout << ans << endl;
	return 0;
}

