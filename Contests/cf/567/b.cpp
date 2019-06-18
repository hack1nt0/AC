#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

ostream& operator<<(ostream& os, const vector<int>& xs) {
	for (int i = 0; i < xs.size(); ++i) os << xs[i];
	return os;
}
struct solver {
	int n;
	vector<int> xs, ret;
	void input(istream& is = cin) {
		is >> n;
		xs.resize(n);
		for (int i = 0; i < n; ++i) {
			char c; is >> c;
			xs[i] = c - '0';
		}
		ret = {10};
	}
	void solve() {
		int mid = n / 2;
		int l = mid - 1, r = mid + 1;
		while (0 <= l && xs[l] == 0) l--;
		while (r <  n && xs[r] == 0) r++;
		for (int q : {l - 1, l, mid, r, r + 1}) {
			ret = min(ret, sum(q));
		}
	}
	void print(ostream& os = cout) {
		os << ret << endl;
	}
	vector<int> sum(int q) {
		if (!(0 < q && q <= n - 1)) return {10};
		if (xs[q] == 0) return {10};
		vector<int> c;
		int rem = 0;
		for (int i = 0; i < max(q, n - q); ++i) {
			int a = i < q ? xs[q - i - 1] : 0;
			int b = i < n - q ? xs[n - 1 - i] : 0;
			c.push_back((a + b + rem) % 10);
			rem = (a + b + rem) / 10;
		}
		if (rem) c.push_back(rem);
		reverse(c.begin(), c.end());
		return c;
	}
};

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	solver sol;
	sol.input();
	sol.solve();
	sol.print();
	return 0;
}
