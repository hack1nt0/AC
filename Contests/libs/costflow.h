
struct graph {
	struct edge {
		int t, cap, cost;
		edge* rev;
		edge(int _t, int _cap, int _cost, edge* _r) : t(_t), cap(_cap), cost(_cost), rev(_r) {}
	};
	int n;
	vector<vector<edge*>> g;
	vi d, inq, upd;
	vector<edge*> ie;
	const int oo = 1e9;
	graph(int _n) : n(_n), g(_n), d(_n), inq(_n), upd(_n), ie(_n) {}
	~graph() { for (auto& es : g) for (auto e : es) delete e;}
	void adde(int u, int v, int flow, int cost) {
		edge* e1 = new edge(v, flow, cost, NULL);
		edge* e2 = new edge(u, 0, -cost, NULL);
		e1->rev = e2;
		e2->rev = e1;
		g[u].pb(e1), g[v].pb(e2);
	}
	int mincost(int s, int t, int flow) {
		int r = 0;
		int c;
		while (flow && (c = mincost(s, t)) < oo) {
			assert(c != -oo); //neg cycle
			int f = oo;
			for (int i = t; i != s; i = ie[i]->rev->t) f = min(f, ie[i]->cap);
			for (int i = t; i != s; i = ie[i]->rev->t) {
				ie[i]->cap -= f;
				ie[i]->rev->cap += f;
			}
			f = min(f, flow);
			flow -= f;
			r += c * f;
		}
		return r;
	}
	int mincost(int s, int t) {
		fill(all(d), oo);
		fill(all(inq), 0);
		fill(all(upd), 0);
		d[s] = 0;
		queue<int> que; que.push(s);
		while (que.size()) {
			int x = que.front(); que.pop();
			inq[x] = false;
			for (auto e : g[x]) if (e->cap) {
				int y = e->t;
				if (d[x] + e->cost < d[y]) {
					d[y] = d[x] + e->cost;
					ie[y] = e;
					upd[y]++;
					if (upd[y] >= n) return -oo;
					if (!inq[y]) que.push(y), inq[y] = true;
				}
			}
		}
		return d[t];
	}
};

