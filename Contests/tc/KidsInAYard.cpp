#include <bits/stdc++.h>
using namespace std;

class KidsInAYard { 
public: 
	int howMany(int r2, int r3, int r5) { 
		int k = 0;
		while (true) {
			int x = k * 5 + r5;
			if (x % 2 == r2 && x % 3 == r3 && x > 0) {
				return x;
			}
			++k;
		}
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const int &Expected, const int &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 1; int Arg1 = 1; int Arg2 = 1; int Arg3 = 1; verify_case(0, Arg3, howMany(Arg0, Arg1, Arg2)); }
	void test_case_1() { int Arg0 = 1; int Arg1 = 0; int Arg2 = 4; int Arg3 = 9; verify_case(1, Arg3, howMany(Arg0, Arg1, Arg2)); }
	void test_case_2() { int Arg0 = 0; int Arg1 = 0; int Arg2 = 0; int Arg3 = 30; verify_case(2, Arg3, howMany(Arg0, Arg1, Arg2)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	KidsInAYard ___test; 
	___test.run_test(-1); 
	for (int i = 0; i < 2; ++i)
		for (int j = 0; j < 3; ++j)
			for (int k = 0; k < 5; ++k)
				cout << ___test.howMany(i, j, k) << endl;
}
// END CUT HERE 
