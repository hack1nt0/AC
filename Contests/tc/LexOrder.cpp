#include <bits/stdc++.h>
using namespace std;

class LexOrder { 
public: 
	string between(string a, string b) { 
		int na = a.size();
		int nb = b.size();
		string NO = "IMPOSSIBLE";
		string c;
		if (na < nb) {
			c = a;
			c += "a";
		}
		if (na > nb) {
			c = a.substr(0, nb);
			c += string(na - nb + 1, 'z');
		}
		if (na == nb) {
			c = a;
			c += "z";
		}
		return (a < c && c < b) ? c : NO;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const string &Expected, const string &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { string Arg0 = "car"; string Arg1 = "dog"; string Arg2 = "careful"; verify_case(0, Arg2, between(Arg0, Arg1)); }
	void test_case_1() { string Arg0 = "car"; string Arg1 = "cat"; string Arg2 = "cash"; verify_case(1, Arg2, between(Arg0, Arg1)); }
	void test_case_2() { string Arg0 = "abcdefghij"; string Arg1 = "abcdefghik"; string Arg2 = "abcdefghijklmnopqrst"; verify_case(2, Arg2, between(Arg0, Arg1)); }
	void test_case_3() { string Arg0 = "man"; string Arg1 = "mana"; string Arg2 = "IMPOSSIBLE"; verify_case(3, Arg2, between(Arg0, Arg1)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	LexOrder ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
