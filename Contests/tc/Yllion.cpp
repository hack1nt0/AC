#include <bits/stdc++.h>
using namespace std;

class Yllion { 
public: 
	string getPower(string a, string b) { 
		vector<string> names = {
			"hundred",
			"myriad",
			"myllion",
			"byllion", "tryllion", "quadryllion", "quintyllion", "sextyllion", "septyllion", "octyllion", "nonyllion",
			"decyllion",
		};
		int n = names.size();
		auto nz = [&](string s)->int {
			istringstream in(s);
			int r = 0;
			string c;
			while (in >> c) {
				if (c == "one")
					r += 0;
				else if (c == "ten")
					r += 1;
				else {
					for (int i = 0; i < names.size(); ++i)
						if (names[i] == c)
							r += 1 << i + 1;
				}
			}
			return r;
		};
		int nza = nz(a);
		int nzb = nz(b);
		int nzc = nza + nzb;
		vector<string> ret;
		for (int i = n - 1; i >= 0; --i) {
			int d = 1 << i + 1;
			if (nzc >= d) {
				nzc -= d;
				ret.push_back(names[i]);
			}
		}
		assert(nzc <= 1);
		ret.push_back(nzc == 0 ? "one" : "ten");
		reverse(ret.begin(), ret.end());
		string c;
		for (int i = 0; i < ret.size(); ++i) {
			if (i > 0) c += " ";
			c += ret[i];
		}
		return c;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); if ((Case == -1) || (Case == 5)) test_case_5(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const string &Expected, const string &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { string Arg0 = "one"; string Arg1 = "one"; string Arg2 = "one"; verify_case(0, Arg2, getPower(Arg0, Arg1)); }
	void test_case_1() { string Arg0 = "one"; string Arg1 = "ten"; string Arg2 = "ten"; verify_case(1, Arg2, getPower(Arg0, Arg1)); }
	void test_case_2() { string Arg0 = "one hundred"; string Arg1 = "one hundred"; string Arg2 = "one myriad"; verify_case(2, Arg2, getPower(Arg0, Arg1)); }
	void test_case_3() { string Arg0 = "ten hundred"; string Arg1 = "one hundred"; string Arg2 = "ten myriad"; verify_case(3, Arg2, getPower(Arg0, Arg1)); }
	void test_case_4() { string Arg0 = "ten hundred myriad myllion"; string Arg1 = "one hundred myllion tryllion"; string Arg2 = "ten myllion byllion tryllion"; verify_case(4, Arg2, getPower(Arg0, Arg1)); }
	void test_case_5() { string Arg0 = "ten hundred myriad myllion byllion tryllion quadryllion quintyllion sextyllion septyllion octyllion nonyllion"; string Arg1 = "ten"; string Arg2 = "one decyllion"; verify_case(5, Arg2, getPower(Arg0, Arg1)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	Yllion ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
