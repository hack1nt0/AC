#include <bits/stdc++.h>
using namespace std;

class IdenticalBags { 
public: 
	typedef long long llt;
	long long makeBags(vector<long long> candy, long long bagSize) { 
		llt l = 0, r = *max_element(candy.begin(), candy.end()) + 1;
		while (l + 1 < r) {
			llt mid = l + (r - l) / 2;
			llt size = 0;
			for (llt x : candy) size += x / mid;
			if (size >= bagSize)
				l = mid;
			else
				r = mid;
		}
		return l;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const long long &Expected, const long long &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { long Arr0[] = {10, 11, 12}; vector<long long> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); long long Arg1 = 3LL; long long Arg2 = 10LL; verify_case(0, Arg2, makeBags(Arg0, Arg1)); }
	void test_case_1() { long Arr0[] = {10, 11, 12, 1, 2, 3}; vector<long long> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); long long Arg1 = 3LL; long long Arg2 = 10LL; verify_case(1, Arg2, makeBags(Arg0, Arg1)); }
	void test_case_2() { long Arr0[] = {100}; vector<long long> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); long long Arg1 = 7LL; long long Arg2 = 14LL; verify_case(2, Arg2, makeBags(Arg0, Arg1)); }
	void test_case_3() { long Arr0[] = {10000000000, 20000000000, 30000000000}; vector<long long> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); long long Arg1 = 6LL; long long Arg2 = 10000000000LL; verify_case(3, Arg2, makeBags(Arg0, Arg1)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	IdenticalBags ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
