#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	string x, y;
	int nx, ny;
	bool ret;
	void input(istream& is = cin) {
		is >> x >> y;
		nx = x.size(), ny = y.size();
	}
	void solve() {
		int px = 0, py = 0;
		ret = true;
		while (px < nx && py < ny) {
			if (x[px] != y[py]) {
				ret = false;
				break;
			}
			int npx = px + 1;
			while (npx < nx && x[npx] == x[npx - 1]) npx++;
			int npy = py + 1;
			while (npy < ny && y[npy] == y[npy - 1]) npy++;
			if (npx - px > npy - py) {
				ret = false;
				break;
			}
			px = npx;
			py = npy;
		}
		ret &= px == nx && py == ny;
	}
	void print(ostream& os = cout) {
		os << (ret ? "YES" : "NO") << endl;
	}
};

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int t; cin >> t;
	solver sol;
	for (int i = 0; i < t; ++i) {
		sol.input();
		sol.solve();
		sol.print();
	}
	return 0;
}
