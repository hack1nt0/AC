#include <bits/stdc++.h>
using namespace std;

const int maxn = 23;
int del = 0;
int par[maxn * maxn];

class FriendlyRooks { 
public:

	int find(int x) {
		return x == par[x] ? x : par[x] = find(par[x]);
	}
	void unite(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if (fx != fy) {
			par[fx] = fy;
			--del;
		}
	}
	int getMinFriendlyColoring(vector <string> board) { 
		int n = board.size();
		int m = board[0].size();
		for (int i = 0; i < n * m; ++i) par[i] = i;
		int nr = 0;
		del = 0;
		for (int i = 0; i < n; ++i) {
			int pre = -1;
			for (int j = 0; j < m; ++j) if (board[i][j] == 'R') {
				++nr;
				if (pre != -1)
					unite(pre, i * m + j);
				pre = i * m + j;
			}
		}
		for (int i = 0; i < m; ++i) {
			int pre = -1;
			for (int j = 0; j < n; ++j) if (board[j][i] == 'R') {
				if (pre != -1)
					unite(pre, j * m + i);
				pre = j * m + i;
			}
		}
		return nr + del;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const int &Expected, const int &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { string Arr0[] = {".R.R",
 "R.R.",
 ".R.R"}; vector <string> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 2; verify_case(0, Arg1, getMinFriendlyColoring(Arg0)); }
	void test_case_1() { string Arr0[] = {"RRRRRRRRRRRRRRR"}; vector <string> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 1; verify_case(1, Arg1, getMinFriendlyColoring(Arg0)); }
	void test_case_2() { string Arr0[] = {"...............",
 "...............",
 "...............",
 "...............",
 "...............",
 "..............."}; vector <string> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 0; verify_case(2, Arg1, getMinFriendlyColoring(Arg0)); }
	void test_case_3() { string Arr0[] = {"....R..........",
 ".R...........R.",
 "....R..........",
 ".R........R....",
 "....R..........",
 "....R.....R...."}; vector <string> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 1; verify_case(3, Arg1, getMinFriendlyColoring(Arg0)); }
	void test_case_4() { string Arr0[] = {"R.........R",
 ".R.......R.",
 "..R.....R..",
 "...R...R...",
 "....R.R....",
 ".....R.....",
 "....R.R....",
 "...R...R...",
 "..R.....R..",
 ".R.......R.",
 "R.........R"}; vector <string> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 6; verify_case(4, Arg1, getMinFriendlyColoring(Arg0)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	FriendlyRooks ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
