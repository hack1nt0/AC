
{
	struct graph {
		struct edge {
			int t, flow;
			edge* rev;
		};
		vector<vector<edge*>> g;
		vector<int> d, se;
		int oo;
		graph(int n) : g(n), d(n), se(n), oo(0) {}
		~graph() { for (auto& es : g) for (auto e : es) delete e;}
		void adde(int u, int v, int flow) {
			edge* e1 = new edge(v, flow, 0);
			edge* e2 = new edge(u, 0, 0);
			e1.rev = e2;
			e2.rev = e1;
			g[u].pb(e1), g[v].pb(e2);
			oo = max(oo, flow + 1);
		}
		int maxflow(int s, int t) {
			int n = g.size();
			int r = 0;
			while (bfs(s, t)) {
				fill(all(se), 0);
				while (true) {
					int x = dfs(s, t, oo);
					if (x == oo) break;
					r += x;
				}
			}
			return r;
		}
		int bfs(int s, int t) {
			fill(all(d), 0);
			d[s] = 0;
			queue<int> que; que.push(s);
			while (que.size()) {
				int x = que.front(); que.pop();
				for (auto e : g[x]) {
					int y = e->t;
					if (!d[y] && e->f) {
						d[y] = d[x] + 1;
						if (y == t) break;
						que.push(y);
					}
				}
			}
			return d[t];
		}
		int dfs(int x, int t, int f) {
			if (x != t) {
				for (int& i = se[x]; i < g[x].size(); ++i) {
					auto e = g[x][i];
					int y = e->t;
					if (d[y] == d[x] + 1 && e->f > 0) {
						int minf = dfs(y, t, min(e->f, f));
						if (minf) {
							e->f -= minf;
							e->rev->f += minf;
							return minf;
						}
					}
				}
			}
			return f;
		}
	}
}

vector<int> cutnodes(vector<vector<int>> adj) {
}
vector<int> bridges(vector<vector<int>> adj) {
}
vector<int> scc(vector<vector<int>> adj) {
}
vector<int> mincuts(vector<vector<int>> adj) {
}
int mincostflow(vector<vector<int>> adj) {
}

int maxmatch(vector<vector<int>> adj) {
}
