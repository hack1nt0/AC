#include <bits/stdc++.h>
using namespace std;

//TLE
class EllysHash { 
public: 
        long long getHash(int N, string A, string B, string C) { 
		return -1;
        }

        
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const long long &Expected, const long long &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 4; string Arg1 = "ELLY"; string Arg2 = "KRIS"; string Arg3 = "STAN"; long long Arg4 = 142572564LL; verify_case(0, Arg4, getHash(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_1() { int Arg0 = 11; string Arg1 = "TOPCODERSRM"; string Arg2 = "INTHEMIDDLE"; string Arg3 = "OFTHEDAYNOO"; long long Arg4 = 2840613885160LL; verify_case(1, Arg4, getHash(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_2() { int Arg0 = 18; string Arg1 = "EVERYSTEPTHATITAKE"; string Arg2 = "ISANOTHERMISTAKETO"; string Arg3 = "YOOOOOOOOOOOOOOOOO"; long long Arg4 = 325013178LL; verify_case(2, Arg4, getHash(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_3() { int Arg0 = 28; string Arg1 = "ANDNEVERMINDTHENOISEYOUHEARD"; string Arg2 = "ITSJUSTTHEBEASTSUNDERYOURBED"; string Arg3 = "INYOURCLOSETINYOURHEAAAAAAAD"; long long Arg4 = 745LL; verify_case(3, Arg4, getHash(Arg0, Arg1, Arg2, Arg3)); }

// END CUT HERE
 
        }; 

// BEGIN CUT HERE 
int main() {
	EllysHash ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
