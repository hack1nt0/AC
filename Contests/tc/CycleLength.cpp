#include <bits/stdc++.h>
using namespace std;

class CycleLength { 
public: 
	int solve(int seed, int a, int b, int m) { 
		map<long long, int> mp;
		long long state = seed;
		int p = 0;
		while (true) {
			state = (state * a + b) % m;
			if (mp.count(state))
				return p - mp[state];
			mp[state] = p;
			++p;
		}
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const int &Expected, const int &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 7; int Arg1 = 3; int Arg2 = 4; int Arg3 = 10; int Arg4 = 4; verify_case(0, Arg4, solve(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_1() { int Arg0 = 1; int Arg1 = 2; int Arg2 = 0; int Arg3 = 997; int Arg4 = 332; verify_case(1, Arg4, solve(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_2() { int Arg0 = 47; int Arg1 = 0; int Arg2 = 23; int Arg3 = 100; int Arg4 = 1; verify_case(2, Arg4, solve(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_3() { int Arg0 = 548687; int Arg1 = 538918; int Arg2 = 376524; int Arg3 = 931161; int Arg4 = 690; verify_case(3, Arg4, solve(Arg0, Arg1, Arg2, Arg3)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	CycleLength ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
