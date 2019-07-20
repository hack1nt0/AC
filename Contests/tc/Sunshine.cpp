#include <bits/stdc++.h>
using namespace std;

class Sunshine { 
public: 
	string weatherReport(int tot, int r) { 
		int s = tot - r;
		int mn = 0;
		for (int x = 1; x <= s; ++x) {
			if (r >= (s + x - 1) / x - 1) {
				mn = x;
				break;
			}
		}
		string ret = "";
		int ss = s, rr = r;
		while (ss > 0 || rr > 0) {
			int x = min(ss, mn);
			ss -= x;
			ret += string(x, 'S');
			int y = min(rr, ss == 0 ? rr : 1);
			rr -= y;
			ret += string(y, 'R');
		}
		assert(ss == 0 && rr == 0);
		return ret;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const string &Expected, const string &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 6; int Arg1 = 1; string Arg2 = "SSSRSS"; verify_case(0, Arg2, weatherReport(Arg0, Arg1)); }
	void test_case_1() { int Arg0 = 3; int Arg1 = 3; string Arg2 = "RRR"; verify_case(1, Arg2, weatherReport(Arg0, Arg1)); }
	void test_case_2() { int Arg0 = 12; int Arg1 = 8; string Arg2 = "RRSRRSRSRRRS"; verify_case(2, Arg2, weatherReport(Arg0, Arg1)); }
	void test_case_3() { int Arg0 = 8; int Arg1 = 2; string Arg2 = "SSRSSRSS"; verify_case(3, Arg2, weatherReport(Arg0, Arg1)); }
	void test_case_4() { int Arg0 = 5; int Arg1 = 0; string Arg2 = "SSSSS"; verify_case(4, Arg2, weatherReport(Arg0, Arg1)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	Sunshine ___test; 
	___test.run_test(-1); 
} 
// END CUT HERE 
