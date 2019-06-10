#include <bits/stdc++.h>
using namespace std;

class PairProduct { 
public: 
	typedef long long llt;
	vector <int> findPair(int n, int a0, int step, long long p) { 
		llt x = step * 1LL * step;
		llt y = step * 1LL * a0;
		llt z = a0 * 1LL * a0;
		for (int i = 0; i < n; ++i) {
			//z + y * (i + j) + x * i * j = p
			//j * (y + x * i) = p - z - y * i
			llt a = (y + x * i);
			llt b = p - z - y * i;
			llt j = b / a;
			if (j * a == b && 0 <= j && j < n) {
				return {i, (int) j};
			}
		}
		return {};
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const vector <int> &Expected, const vector <int> &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: " << print_array(Expected) << endl; cerr << "\tReceived: " << print_array(Received) << endl; } }
	void test_case_0() { int Arg0 = 6; int Arg1 = 2; int Arg2 = 5; long long Arg3 = 14LL; int Arr4[] = {0, 1 }; vector <int> Arg4(Arr4, Arr4 + (sizeof(Arr4) / sizeof(Arr4[0]))); verify_case(0, Arg4, findPair(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_1() { int Arg0 = 6; int Arg1 = 2; int Arg2 = 5; long long Arg3 = 144LL; int Arr4[] = {2, 2 }; vector <int> Arg4(Arr4, Arr4 + (sizeof(Arr4) / sizeof(Arr4[0]))); verify_case(1, Arg4, findPair(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_2() { int Arg0 = 6; int Arg1 = 2; int Arg2 = 5; long long Arg3 = 47LL; int Arr4[] = { }; vector <int> Arg4(Arr4, Arr4 + (sizeof(Arr4) / sizeof(Arr4[0]))); verify_case(2, Arg4, findPair(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_3() { int Arg0 = 6; int Arg1 = -200000; int Arg2 = -500000; long long Arg3 = 2040000000000LL; int Arr4[] = {2, 3 }; vector <int> Arg4(Arr4, Arr4 + (sizeof(Arr4) / sizeof(Arr4[0]))); verify_case(3, Arg4, findPair(Arg0, Arg1, Arg2, Arg3)); }
	void test_case_4() { int Arg0 = 20; int Arg1 = -5; int Arg2 = 1; long long Arg3 = -6LL; int Arr4[] = {2, 7 }; vector <int> Arg4(Arr4, Arr4 + (sizeof(Arr4) / sizeof(Arr4[0]))); verify_case(4, Arg4, findPair(Arg0, Arg1, Arg2, Arg3)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	PairProduct ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
