#include <bits/stdc++.h>
using namespace std;

class VisitN { 
public: 
	string a;
	bool vis[30][30];
	int left;
	void dfs(int i, int j, int d) {
		//cout << left << " " << i << " " << j << " " << d << endl;
		if (!vis[i][j]) {
			vis[i][j] = true;
			--left;
		}
		if (left == 0) return;

		if (j + d >= 30) {
			a.push_back('S');
			dfs(i + 1, 29, -d);
			return;
		}
		if (j + d < 0) {
			a.push_back('S');
			dfs(i + 1, 0, -d);
			return;
		}
		if (d > 0) {
			a.push_back('E');
			dfs(i, j + 1, d);
			return;
		}
		if (d < 0) {
			a.push_back('W');
			dfs(i, j - 1, d);
			return;
		}
	}
	string visit(int n, int r, int c) { 
		this->left = n;
		a.resize(0);
		for (int i = 0; i < 30; ++i)
			for (int j = 0; j < 30; ++j)
				vis[i][j] = false;
		left--;
		vis[r][c] = true;
		while (left > 0 && !(r == 0 && c == 0)) {
			if (c > 0) {
				a.push_back('W');
				--left;
				--c;
				vis[r][c] = true;
			} else if (r > 0) {
				a.push_back('N');
				--left;
				--r;
				vis[r][c] = true;
			}
		}
		assert(left >= 0);
		if (left) {
			dfs(0, 0, +1);
		}
		return a;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const string &Expected, const string &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 1; int Arg1 = 4; int Arg2 = 7; string Arg3 = ""; verify_case(0, Arg3, visit(Arg0, Arg1, Arg2)); }
	void test_case_1() { int Arg0 = 2; int Arg1 = 4; int Arg2 = 7; string Arg3 = "N"; verify_case(1, Arg3, visit(Arg0, Arg1, Arg2)); }
	void test_case_2() { int Arg0 = 2; int Arg1 = 0; int Arg2 = 17; string Arg3 = "W"; verify_case(2, Arg3, visit(Arg0, Arg1, Arg2)); }
	void test_case_3() { int Arg0 = 4; int Arg1 = 1; int Arg2 = 1; string Arg3 = "NWEE"; verify_case(3, Arg3, visit(Arg0, Arg1, Arg2)); }
	void test_case_4() { int Arg0 = 10; int Arg1 = 5; int Arg2 = 13; string Arg3 = "NWSSEENNSSWS"; verify_case(4, Arg3, visit(Arg0, Arg1, Arg2)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	VisitN ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
