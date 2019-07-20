#include <bits/stdc++.h>
using namespace std;

class Pickaxe { 
public: 
	int n, m;
	vector<string> maze;
	vector<int> di = {-1, +1, 0, 0};
	vector<int> dj = {0, 0, -1, +1};
	int countWalls(vector <string> maze) { 
		this->maze = maze;
		n = maze.size(), m = maze[0].size();
		vector<vector<bool>> vis1(n, vector<bool>(m, false));
		dfs(0, 0, vis1);
		vector<vector<bool>> vis2(n, vector<bool>(m, false));
		dfs(n - 1, m - 1, vis2);
		int ret = 0;
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) if (maze[i][j] == '#' && vis1[i][j] && vis2[i][j]) {
				ret++;
			}
		return ret;
	} 
	void dfs(int i, int j, vector<vector<bool>>& vis) {
		if (!(0 <= i && i < n && 0 <= j && j < m)) return;
		if (vis[i][j]) return;
		vis[i][j] = true;
		if (maze[i][j] == '#') {
			return;
		}
		for (int d = 0; d < 4; ++d) {
			dfs(i + di[d], j + dj[d], vis);
		}
		return;
	}
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const int &Expected, const int &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { string Arr0[] = {"..#",
 ".#.",
 "#.."}; vector <string> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 3; verify_case(0, Arg1, countWalls(Arg0)); }
	void test_case_1() { string Arr0[] = {"..##..",
 "..##..",
 "...#..",
 "..##.."}; vector <string> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 1; verify_case(1, Arg1, countWalls(Arg0)); }
	void test_case_2() { string Arr0[] = {"..##..",
 "..##..",
 "..##..",
 "..##.."}; vector <string> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 0; verify_case(2, Arg1, countWalls(Arg0)); }
	void test_case_3() { string Arr0[] = {".....#.",
 ".###.#.",
 ".#.#...",
 ".###.##",
 ".#.#.#.",
 ".#.###.",
 "##....."}; vector <string> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); int Arg1 = 6; verify_case(3, Arg1, countWalls(Arg0)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	Pickaxe ___test; 
	___test.run_test(-1); 
} 
// END CUT HERE 
