#include <bits/stdc++.h>
using namespace std;

const string cs = "ab";
const int maxn = 100;
vector<char> a(maxn, '0');

class FindStringEasy { 
public: 
	int na = 0;
	bool dfs(int x, vector<int> ips, int left) {
		if (left == 0)
			return true;
		if (left < 0)
			return false;
		if (x >= maxn)
			return false;
		for (char c : cs) {
			a[x] = c;
			++na;
			vector<int> ips2;
			for (int ip : ips) {
				if (ip - 1 >= 0 && a[ip - 1] == c)
					ips2.push_back(ip - 1);
			}
			int left2 = left - ips2.size() - 1;
			ips2.push_back(x);
			if (x - 1 >= 0 && a[x] == a[x - 1]) {
				ips2.push_back(x - 1);
				left2--;
			}
			if (dfs(x + 1, ips2, left2))
				return true;
			--na;
		}
		return false;
	}
		
	string withPalindromicSubstrings(int n) { 
		na = 0;
		return !dfs(0, {}, n) ? "" : string(a.begin(), a.begin() + na);
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const string &Expected, const string &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 3; string Arg1 = "aa"; verify_case(0, Arg1, withPalindromicSubstrings(Arg0)); }
	void test_case_1() { int Arg0 = 9; string Arg1 = "aaaba"; verify_case(1, Arg1, withPalindromicSubstrings(Arg0)); }
	void test_case_2() { int Arg0 = 12; string Arg1 = "aabbaba"; verify_case(2, Arg1, withPalindromicSubstrings(Arg0)); }
	void test_case_3() { int Arg0 = 20; string Arg1 = "bbbaabbaba"; verify_case(3, Arg1, withPalindromicSubstrings(Arg0)); }
	void test_case_4() { int Arg0 = 140; string Arg1 = "bbaababaabaabbbabbbabbbaabaaaabbbaabbabbabababbbaaa"; verify_case(4, Arg1, withPalindromicSubstrings(Arg0)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	FindStringEasy ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
