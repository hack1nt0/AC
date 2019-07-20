#include <bits/stdc++.h>
using namespace std;

class TreeDiameters { 
public: 
	typedef vector<int> vi;
	typedef vector<vi> vvi;
	vvi adj;
	/*
	void dfs(int x, int fa, int d, vi& size) {
		if (d >= size.size()) size.push_back(0);
		size[d]++;
		for (int y : adj[x]) if (y != fa)
			dfs(y, x, d + 1, size);
	}
	*/
	int getMax(vector <int> p) { 
		int n = p.size() + 1;
		adj.clear();
		adj.resize(n);
		for (int i = 0; i < p.size(); ++i) {
			int x = i + 1, y = p[i];
			adj[x].push_back(y);
			adj[y].push_back(x);
		}
		int ret = 1;
		vvi size(n, vi(n, 0));
		auto sizeat = [&](int x, int l) { return l < size[x].size() ? size[x][l] : 0; };
		function<void(int,int,int,vi&)> dfs = [&](int x, int fa, int d, vi& size) {
			if (d >= size.size()) size.push_back(0);
			size[d]++;
			for (int y : adj[x]) if (y != fa)
				dfs(y, x, d + 1, size);
		};
		for (int x = 0; x < n; ++x) {
			for (int y : adj[x]) {
				//fill(size[y].begin(), size[y].end(), 0);
				size[y].clear();
				dfs(y, x, 0, size[y]);
			}
			for (int l = 0; l < n; ++l) {
				int tot = 0;
				for (int y : adj[x]) tot += sizeat(y, l);
				int maybe = 0;
				for (int y : adj[x]) {
					maybe += sizeat(y, l) * (tot - sizeat(y, l));
					tot -= sizeat(y, l);
				}
				ret = max(ret, maybe);
				if (!tot || !maybe) break;
			}
		}
		return ret;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const int &Expected, const int &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arr0[] = {0,1,2,2}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 3; verify_case(0, Arg1, getMax(Arg0)); }
	void test_case_1() { int Arr0[] = {0,0,0,0}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 6; verify_case(1, Arg1, getMax(Arg0)); }
	void test_case_2() { int Arr0[] = {0,1,2,3,4,5,6,7}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 1; verify_case(2, Arg1, getMax(Arg0)); }
	void test_case_3() { int Arr0[] = {0,0,0,2,3,4}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 3; verify_case(3, Arg1, getMax(Arg0)); }
	void test_case_4() { int Arr0[] = {0}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 1; verify_case(4, Arg1, getMax(Arg0)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	TreeDiameters ___test; 
	___test.run_test(-1); 
} 
// END CUT HERE 
