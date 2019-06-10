#include <bits/stdc++.h>
using namespace std;

vector<int> dfs_order(vector<vector<int>> adj, int root = 0) {
	int n = adj.size();
	vector<int> ans;
	vector<int> vis(n, 0);
	stack<int> stk;
	stk.push(root);
	vis[root] = -1;
	while (stk.size() > 0) {
		int x = stk.top();
		if (vis[x] == 1) {
			ans.push_back(x);
			stk.pop();
		} else if (vis[x] == -1) {
			vis[x] = 1;
			for (int y : adj[x]) if (!vis[y]) {
				stk.push(y);
				vis[y] = -1;
			}
		}
	}
	return ans;
}


int ssc(int x) {
	static int nv = 0;
	if (vis[x])
		return x;
	stk.push(x);
	vis[x] = -1;
	int top = nv++;
	for (int y : adj[x])
		top = min(top, ssc(y));
	if (top == x) {
		while (true) {
			int y = stk.top(), stk.pop();
			comp[y] = ncomp;
			vis[y] = 1;
			if (y == x) break;
		}
		++ncomp;
	}
}

void scc(vector<vector<int>> adj) {
	int n = adj.size();
	vector<int> top(n);
	stack<int> stk;
	for (int x : dfs_order(adj)) {
		int top[x] = x;
		for (int y : adj[x]) {
			top[x] = min(top[x], top[y]);
		}
		stk.push(x);
		if (top[x] == x) {
			while (true) {
				int y = stk.top(), stk.pop();
				comp[y] = ncomp;
				vis[y] = 1;
				if (y == x) break;
			}
			++ncomp;
		}
	}
}
		
int main() {
	vector<vector<int>> adj = {
		{1},
		{0},
	};
	for (int x : dfs_order(adj)) cout << x << " ";
	cout << endl;
	return 0;
}
