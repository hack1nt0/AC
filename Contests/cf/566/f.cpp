#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

//UNK
struct solver {
	int t, a, b, p, q;
	void input(istream& is = cin) {
		is >> t;
	}
	void solve() {
		is >> a >> b >> p >> q;
		ratint period(q, p);
		ratint start(period / 2);
		//min_x(abs(a+x - s+q/p*k))
	}
	void print(ostream& os = cout) {
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
