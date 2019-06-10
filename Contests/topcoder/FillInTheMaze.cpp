#include <bits/stdc++.h>
using namespace std;

class FillInTheMaze { 
public: 
        vector <string> filled(int R, int C, int X) { 
		int n = R * 2 + 1;
		int m = C * 2 + 1;
		vector<string> ans(n, string(m, 0));
		int x = 0;
		int tot = 0;
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) {
				char c = '#';
				int pi = i % 2;
				int pj = j % 2;
				if (pi && pj)
					c = '.';
				else if (pi && !pj || !pi && pj) {
					if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
						if (x < X)
							c = '.', ++x;
					} else if (!pi && j == 1 || pi)
						c = '.';
				}
				ans[i][j] = c;
				if (c == '#') ++tot;
			}
		cerr << tot << endl;
		return ans;
        }

        
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const vector <string> &Expected, const vector <string> &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: " << print_array(Expected) << endl; cerr << "\tReceived: " << print_array(Received) << endl; } }
	void test_case_0() { int Arg0 = 3; int Arg1 = 6; int Arg2 = 3; string Arr3[] = {"#######.#####", "......#.#...#", "#.###.#.#.#.#", "#.#.......#.#", "#.#######.#.#", "#.......#.#.#", "#########.###" }; vector <string> Arg3(Arr3, Arr3 + (sizeof(Arr3) / sizeof(Arr3[0]))); verify_case(0, Arg3, filled(Arg0, Arg1, Arg2)); }
	void test_case_1() { int Arg0 = 1; int Arg1 = 3; int Arg2 = 2; string Arr3[] = {"#######", ".......", "#######" }; vector <string> Arg3(Arr3, Arr3 + (sizeof(Arr3) / sizeof(Arr3[0]))); verify_case(1, Arg3, filled(Arg0, Arg1, Arg2)); }
	void test_case_2() { int Arg0 = 2; int Arg1 = 2; int Arg2 = 8; string Arr3[] = {"#.#.#", ".....", "#.###", ".....", "#.#.#" }; vector <string> Arg3(Arr3, Arr3 + (sizeof(Arr3) / sizeof(Arr3[0]))); verify_case(2, Arg3, filled(Arg0, Arg1, Arg2)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	FillInTheMaze ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
