#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

ostream& operator<<(ostream& os, deque<int> xs) {
	for (int i = 0; i < xs.size(); ++i) os << xs[i] << " ";
	return os;
}
//WA, rolling hash not desired 
struct solver {
	int nd, nq;
	deque<int> deq;
	vector<pair<int, int>> ret;
	vector<pair<long long, int>> qs;
	void input(istream& is = cin) {
		is >> nd >> nq;
		deq.resize(nd);
		ret.resize(nq);
		qs.resize(nq);
		for (int i = 0; i < nd; ++i) is >> deq[i];
		for (int i = 0; i < nq; ++i) { int q; is >> q; q--; qs[i] = {q, i}; }
		sort(qs.begin(), qs.end());
	}
	void solve() {
		//unordered_map<long long, pair<int, int>> hashmap;
		long long mod = (int) 1e6 * 2 + 33;
		vector<int> one(mod);
		vector<int> two(mod);
		vector<int> step(mod, -1);
		vector<long long> hvs(mod);
		long long hv = 0;
		vector<long long> pows(nd + 1, 1);
		for (int i = 1; i <= nd; ++i) pows[i] = 10 * pows[i - 1] % mod;
		for (int i = 0; i < nd; ++i) hv = (hv + deq[i] * pows[nd - i - 1] % mod) % mod;
		int iq = 0;
		int i = 0;
		while (step[hv] < 0 && iq < nq) {
			//cout << deq << endl;
			int x = deq.front(); deq.pop_front();
			int y = deq.front(); deq.pop_front();
			if (i == qs[iq].first) {
				ret[qs[iq].second] = {x, y};
				iq++;
			}
			one[hv] = x;
			two[hv] = y;
			step[hv] = i;
			hvs[i] = hv;
			//cout << hv << " " << step[hv] << endl;
			if (x > y) {
				hv = (hv - x * pows[nd - 1]) * 10 % mod + x * pows[nd - 1] % mod + y;
				hv %= mod;
				if (hv < 0) hv = (hv + mod) % mod;
				deq.push_front(x);
				deq.push_back(y);
			} else {
				hv = (hv - x * pows[nd - 1]) * 10 % mod + x;
				hv %= mod;
				if (hv < 0) hv = (hv + mod) % mod;
				deq.push_front(y);
				deq.push_back(x);
			}
			assert(0 <= hv && hv < mod);
			//cout << " " << hv << " " << step[hv] << endl;
			i++;
		}
		if (iq < nq) {
			int period = i - step[hv];
			int start = step[hv];
			//cout << start << " " << period << endl;
			while (iq < nq) {
				int which = start + (qs[iq].first - i) % period;
				int x = one[hvs[which]];
				int y = two[hvs[which]];
				ret[qs[iq].second] = {x, y};
				iq++;
			}
		}
	}
	void print(ostream& os = cout) {
		for (int i = 0; i < nq; ++i) os << ret[i].first << " " << ret[i].second << "\n";
		os << endl;
	}
};

mt19937 mt(std::chrono::steady_clock::now().time_since_epoch().count());
int period(int n) {
	vector<int> xs(n, 0);
	for (int i = 0; i < n; ++i) xs[i] = i;
	shuffle(xs.begin(), xs.end(), mt);
	deque<int> deq(xs.begin(), xs.end());
	set<deque<int>> st;
	int r = 0;
	while (true) {
		if (st.find(deq) != st.end()) break;
		r++;
		st.insert(deq);
		int x = deq.front(); deq.pop_front();
		int y = deq.front(); deq.pop_front();
		deq.push_front(max(x, y));
		deq.push_back(min(x, y));
		if (r % 1000 == 0) cout << r << endl;
	}
	return r;
}
int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	solver sol;
	sol.input();
	sol.solve();
	sol.print();
	/*
	int n = atoi(args[1]);
	cout << period(n) << endl;
	*/
	return 0;
}
