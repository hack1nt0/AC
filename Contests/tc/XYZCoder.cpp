#include <bits/stdc++.h>
using namespace std;

class XYZCoder { 
public: 
	int countWays(int room, int size) { 
		int n = room * size;
		const int mod = (int) 1e9 + 7;
		vector<vector<long long>> dp(room + 1, vector<long long>(n, 0));
		vector<int> r(room + 1, 1);
		for (int i = 2; i <= room; ++i) r[i] = (i - 1) * size + 1;
		for (int x = room; x >= 1; --x)
			for (int y = r[x]; y >= x; --y) {
				long long& v = dp[x][y];
				if (x == room) v = 1;
				else if (y == r[x]) {
					for (int yy = y + 1; yy <= r[x + 1]; ++yy)
						v = (v + dp[x + 1][yy]) % mod;
				} else {
					v = (dp[x + 1][y + 1] + dp[x][y + 1]) % mod;
				}
			}
		long long ret = dp[1][1];
		for (int i = 1; i <= room; ++i) ret = ret * i % mod;
		return (int) ret;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const int &Expected, const int &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 2; int Arg1 = 1; int Arg2 = 2; verify_case(0, Arg2, countWays(Arg0, Arg1)); }
	void test_case_1() { int Arg0 = 1; int Arg1 = 2; int Arg2 = 1; verify_case(1, Arg2, countWays(Arg0, Arg1)); }
	void test_case_2() { int Arg0 = 2; int Arg1 = 2; int Arg2 = 4; verify_case(2, Arg2, countWays(Arg0, Arg1)); }
	void test_case_3() { int Arg0 = 4; int Arg1 = 5; int Arg2 = 6840; verify_case(3, Arg2, countWays(Arg0, Arg1)); }
	void test_case_4() { int Arg0 = 100; int Arg1 = 100; int Arg2 = 718243627; verify_case(4, Arg2, countWays(Arg0, Arg1)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	XYZCoder ___test; 
	___test.run_test(-1); 
} 
// END CUT HERE 
