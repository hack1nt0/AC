#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	int n, m;
	vector<string> sheet;
	const int maxn = 2000;
	vector<vector<int>> ret;
	vector<vector<int>> adj;
	vector<int> vis;
	void input(istream& is = cin) {
		is >> n >> m;
		sheet.resize(n);
		for (int i = 0; i < n; ++i) cin >> sheet[i];
		adj.resize(30);
		vis.resize(30, 0);
	}
	void solve() {
		vector<int> xmn(30, maxn), xmx(30, -1), ymn(30, maxn), ymx(30, -1);
		auto at = [&](int x, int y) {
			if (!(0 <= x && x < n && 0 <= y && y < m)) return -2;
			char c = sheet[x][y]; 
			return c == '.' ? -1 : c - 'a';
		};
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				int c = at(i, j);
				if (c == -1) continue;
				xmn[c] = min(xmn[c], j);
				xmx[c] = max(xmx[c], j);
				ymn[c] = min(ymn[c], i);
				ymx[c] = max(ymx[c], i);
			}
		for (int i = 0; i < 26; ++i) if (xmn[i] <= xmx[i]) {
			int w = xmx[i] - xmn[i] + 1;
			int h = ymx[i] - ymn[i] + 1;
			if (w > 1 && h > 1) return;
		}
		auto addedge = [&](int x, int y) { adj[x].push_back(y); };
		auto hor = [&](int c) { return xmx[c] - xmn[c] >= ymx[c] - ymn[c]; };
		auto ver = [&](int c) { return xmx[c] - xmn[c] <= ymx[c] - ymn[c]; };
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				int c = at(i, j);
				if (c < 0) continue;
				{
					int pc = at(i, j - 1);
					if (hor(c) && xmn[c] < j && pc != -1 && pc != c)
						if (pc < c) return;
						//addedge(c, pc);
				}
				{
					int pc = at(i - 1, j);
					if (ver(c) && ymn[c] < i && pc != -1 && pc != c)
						if (pc < c) return; 
						//addedge(c, pc);
				}
			}
		cout << "H" << endl;
		auto isdag = [&]() {
			for (int i = 0; i < 26; ++i) if (!vis[i])
				if (!dfs(i)) return false;
			return true;
		};
		//if (!isdag()) return;
		for (int i = 0; i < 26; ++i) if (xmn[i] <= xmx[i]) {
			ret.push_back({ymn[i] + 1, xmn[i] + 1, ymx[i] + 1, xmx[i] + 1});
		}
	}
	bool dfs(int x) {
		if (vis[x] == 1) return true;
		if (vis[x] == -1) return false;
		vis[x] = -1;
		for (int y : adj[x]) if (!dfs(y))
			return false;
		return true;
	}
	void print(ostream& os = cout) {
		if (ret.size() == 0)
			os << "NO" << endl;
		else {
			os << "YES" << "\n";
			os << ret.size() << "\n";
			for (int i = 0; i < ret.size(); ++i) {
				for (int j = 0; j < ret[i].size(); ++j) os << ret[i][j] << " ";
				os << "\n";
			}
		}
	}
};

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int t; cin >> t;
	for (int i = 0; i < t; ++i) {
		solver sol;
		sol.input();
		sol.solve();
		sol.print();
	}
	return 0;
}
