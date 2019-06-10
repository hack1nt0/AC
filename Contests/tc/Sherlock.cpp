#include <bits/stdc++.h>
using namespace std;

class Sherlock { 
public: 
	int count(string s, string t) {
		int r = 0;
		set<char> sett(t.begin(), t.end());
		for (char c : s) if (sett.count(c))
			++r;
		return r;
	}
	string isItHim(string firstName, string lastName) { 
		bool ok = firstName.size() > 6 && firstName[0] == 'B';
		ok &= lastName.size() > 6 && lastName[0] == 'C';
		ok &= count(firstName, "BENEDICT") >= 3;
		ok &= count(lastName, "CUMBERBATCH") >= 5;
		return ok ? "It is him" : "It is someone else";
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); if ((Case == -1) || (Case == 5)) test_case_5(); if ((Case == -1) || (Case == 6)) test_case_6(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const string &Expected, const string &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { string Arg0 = "BENEDICT"; string Arg1 = "CUMBERBATCH"; string Arg2 = "It is him"; verify_case(0, Arg2, isItHim(Arg0, Arg1)); }
	void test_case_1() { string Arg0 = "BENADRYL"; string Arg1 = "CUCUMBERPATCH"; string Arg2 = "It is him"; verify_case(1, Arg2, isItHim(Arg0, Arg1)); }
	void test_case_2() { string Arg0 = "HARSHIT"; string Arg1 = "MEHTA"; string Arg2 = "It is someone else"; verify_case(2, Arg2, isItHim(Arg0, Arg1)); }
	void test_case_3() { string Arg0 = "BATAXXAT"; string Arg1 = "CURMUDGEON"; string Arg2 = "It is him"; verify_case(3, Arg2, isItHim(Arg0, Arg1)); }
	void test_case_4() { string Arg0 = "BENEDI"; string Arg1 = "CUMBER"; string Arg2 = "It is someone else"; verify_case(4, Arg2, isItHim(Arg0, Arg1)); }
	void test_case_5() { string Arg0 = "BAXAXAXY"; string Arg1 = "CENTRIFUGAL"; string Arg2 = "It is someone else"; verify_case(5, Arg2, isItHim(Arg0, Arg1)); }
	void test_case_6() { string Arg0 = "BUMBLESHACK"; string Arg1 = "CRUMPLEHORN"; string Arg2 = "It is him"; verify_case(6, Arg2, isItHim(Arg0, Arg1)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	Sherlock ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
