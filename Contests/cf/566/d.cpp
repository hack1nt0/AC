#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

struct solver {
	int n;
	vector<int> dep, deg, layer, fa, depest;
	vector<vector<int>> adj;
	int ret;
	
	void input(istream& in = cin) {
		in >> n;
		dep.resize(n, 0);
		depest.resize(n, -1);
		deg.resize(n, 0);
		layer.resize(n, -1);
		fa.resize(n, -1);
		adj.resize(n);
		for (int i = 0; i < n - 1; ++i) {
			int x, y; in >> x >> y; x--, y--;
			adj[x].push_back(y), adj[y].push_back(x);
			deg[x]++, deg[y]++;
		}
	}
	void solve() {
		if (n == 1) {
			ret = 1;
			return;
		}
		int s = dfs(0);
		int t = dfs(s);
		//cout << s << " " << deg[s] << endl;
		//cout << t << " " << deg[t] << endl;
		assert(deg[s] == 1 && deg[t] == 1);
		vector<int> path;
		for (int x = t; x != s; x = fa[x]) path.push_back(x);
		path.push_back(s);
		vector<int> roots{path[path.size() / 2], s, t};
		{
			int x = path[path.size() / 2];
			int root = dfs(x);
			for (int y : adj[x]) if (root == -1 || dep[depest[y]] < dep[depest[root]])
				root = depest[y];
			roots.push_back(root);
		}
		ret = -1;
		for (int root : roots) {
			if (check(root)) {
				ret = (root + 1);
				return;
			}
		}
	}
	void print(ostream& out = cout) {
		out << ret << endl;
	}
	int dfs(int x, int f = -1, int d = 0) {
		fa[x] = f;
		dep[x] = d;
		int ny = adj[x].size();
		if (f != -1) ny--;
		int dx = x;
		if (ny == 0);
		else 
			for (int y : adj[x]) if (y != f) {
				int dy = dfs(y, x, d + 1);
				if (dep[dy] > dep[dx]) dx = dy;
			}
		depest[x] = dx;
		return dx;
	}

	bool check(int x, int f, int d) {
		if (layer[d] != -1 && layer[d] != deg[x])
			return false;
		layer[d] = deg[x];
		for (int y : adj[x]) if (y != f) {
			if (!check(y, x, d + 1)) return false;
		}
		return true;
	}

	bool check(int x) {
		for (int i = 0; i < n; ++i) layer[i] = -1;
		return check(x, -1, 0);
	}
	void solve2() {
		ret = -1;
		if (n == 1)
			ret = 1;
		else 
			for (int root = 0; root < n; ++root) {
				if (check(root)) {
					ret = root + 1; 
					return;
				}
			}
	}
};

int solve(istream& in) {
}

#include "ranlib.h"
int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	solver sol;
	sol.input();
	sol.solve();
	sol.print();
	/*
	int n = argc == 1 ? 4 : atoi(args[1]);
	while (true) {
		string input(rtree(n));
		//cout << input << endl;
		int x, y;
		{
			auto in = stringstream(input);
			solver sol;
			sol.input(in);
			sol.solve();
			x = sol.ret;
		}
		{
			auto in = stringstream(input);
			solver sol;
			sol.input(in);
			sol.solve2();
			y = sol.ret;
		}

		if (x == -1 && y >= 0 || x >= 0 && y == -1)
			error(input, x, y);
	}
	*/
	return 0;
}
