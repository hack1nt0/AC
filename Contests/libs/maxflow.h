struct graph {
	struct edge {
		int t, cap;
		edge* rev;
		edge(int _t, int _cap, edge* _r) : t(_t), cap(_cap), rev(_r) {}
		friend ostream& operator<<(ostream& os, edge e) { 
			os << "edge{to=" << e.t;
			os << ", cap=" << e.cap << "}";
			return os;
		}
	};
	vector<vector<edge*>> g;
	vi d, se;
	int oo;
	graph(int n) : g(n), d(n), se(n), oo(0) {}
	~graph() { for (auto& es : g) for (auto e : es) delete e;}
	void adde(int u, int v, int flow) {
		edge* e1 = new edge(v, flow, NULL);
		edge* e2 = new edge(u, 0, NULL);
		e1->rev = e2;
		e2->rev = e1;
		g[u].pb(e1), g[v].pb(e2);
		oo = max(oo, flow + 1);
	}
	int maxflow(int s, int t) {
		assert(s != t);
		int r = 0;
		while (bfs(s, t)) {
			fill(all(se), 0);
			while (true) {
				int x = dfs(s, t, oo);
				if (!x) break;
				r += x;
			}
		}
		return r;
	}
	int bfs(int s, int t) {
		fill(all(d), 0);
		d[s] = 1;
		queue<int> que; que.push(s);
		while (que.size()) {
			int x = que.front(); que.pop();
			if (x == t) break;
			for (auto e : g[x]) {
				int y = e->t;
				if (!d[y] && e->cap) {
					d[y] = d[x] + 1;
					que.push(y);
				}
			}
		}
		return d[t];
	}
	int dfs(int x, int t, int f) {
		if (x == t) {
			return f;
		}
		for (int& i = se[x]; i < sz(g[x]); ++i) {
			auto e = g[x][i];
			int y = e->t;
			if (d[y] == d[x] + 1 && e->cap) {
				int minf = dfs(y, t, min(e->cap, f));
				if (minf) {
					e->cap -= minf;
					e->rev->cap += minf;
					return minf;
				}
			}
		}
		return 0;
	}
};
