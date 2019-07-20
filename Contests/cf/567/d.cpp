#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

ostream& operator<<(ostream& os, vector<int> xs) {
	for (int i = 0; i < xs.size(); ++i) os << xs[i] << " ";
	return os;
}

template <typename T>
struct bst {
	vector<T> xs;
	vector<int> l, r, sons;
	void insert(int x) {
	}
	void erase(int x) {
	}
	T kth(int k) {
		assert(k <= sons[0]);
		int cur = 0;
		while (true) {
			if (k == 1313
			if (sons[cur] == 1) {
				assert(k == 1);
				return xs[cur];
			}
			if (k <= sons[l[cur]]) cur = l[cur];
			else k -= sons[l[cur]], cur = r[cur];
		}
	}
};
	
struct solver {
	int n, m, q;
	vector<int> which;
	vector<pair<llt, int>> queries;
	vector<int> ret;
	void input(istream& is = cin) {
		is >> n >> m >> q;
		which.resize(n);
		for (int i = 0; i < n; ++i) cin >> which[i], which[i]--;
		queries.resize(q);
		for (int i = 0; i < q; ++i) {
			llt y; cin >> y;
			queries[i] = {y, i};
		}
		ret.resize(q);
	}
	void solve() {
		map<int, vector<int>> cycles;
		vector<int> cnt(m, 0);
		for (int city : which) cnt[city]++;
		for (int city = 0; city < m; ++city) {
			cycles[cnt[city]].push_back(city);
		}
		sort(queries.begin(), queries.end());
		auto ic1 = cycles.begin();
		auto ic2 = next(ic1);
		vector<int> cities = ic1->second;
		llt acc = n + 1;
		for (auto query : queries) {
			llt year = query.first;
			int qid = query.second;
			bool upd = false;
			while (true) {
				llt nacc = ic2 != cycles.end() ? acc + (ic2->first - ic1->first) * 1LL * cities.size() :
					numeric_limits<llt>::max();
				if (year >= nacc) {
					acc = nacc;
					for (int x : ic2->second) cities.push_back(x);
					//cities.insert(cities.end(), ic2->second.begin(), ic2->second.end());
					ic1 = ic2;
					ic2++;
					upd = true;
					continue;
				}
				break;
			}
			if (upd) sort(cities.begin(), cities.end());
			//cout << year << ": " << cities << endl;
			ret[qid] = cities[(year - acc) % cities.size()];
		}
	}
	void print(ostream& os = cout) {
		for (int i = 0; i < q; ++i) cout << ret[i] + 1 << "\n";
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
