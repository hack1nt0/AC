#include <bits/stdc++.h>
using namespace std;

const int maxn = 5e3 + 2;
int c[maxn], a[maxn];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n; cin >> n;
	for (int i = 0; i < n; ++i) {
		char cc; cin >> cc;
		c[i] = cc - '0';
	}
	for (int i = 0; i < n; ++i) {
		char cc; cin >> cc;
		a[i] = cc - '0';
	}
	vector<int> p10, p11, p01, p00;
	for (int i = 0; i < n; ++i) {
		if (c[i] && !a[i]) p10.push_back(i + 1);
		if (c[i] && a[i]) p11.push_back(i + 1);
		if (!c[i] && a[i]) p01.push_back(i + 1);
		if (!c[i] && !a[i]) p00.push_back(i + 1);
	}
	int t10 = p10.size();
	int t11 = p11.size();
	int t01 = p01.size();
	int t00 = p00.size();
	/*
	   c10 + c11 + c01 + c00 = t10 - c10 + ...
	   c10 + c11 = t01 - c01 + t11 - c11
	*/
	for (int c10 = 0; c10 <= t10; ++c10) {
		for (int c11 = 0; c11 <= t11; ++c11) {
			int c01 = t01 + t11 - c11 - (c10 + c11);
			int c00 = n / 2 - c10 - c11 - c01;
			if (0 <= c01 && c01 <= t01 && 0 <= c00 && c00 <= t00) {
				for (int i = 0; i < c10; ++i) cout << p10[i] << " ";
				for (int i = 0; i < c11; ++i) cout << p11[i] << " ";
				for (int i = 0; i < c01; ++i) cout << p01[i] << " ";
				for (int i = 0; i < c00; ++i) cout << p00[i] << " ";
				cout << endl;
				return 0;
			}
		}
	}
	cout << -1 << endl;
	return 0;
}
				
		

