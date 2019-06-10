#include <bits/stdc++.h>
using namespace std;

class LongJump2 { 
public: 
        int countNewLeaders(int N, vector <int> js) { 
		int ans = 0;
		int ma = -1;
		int win = -1;
		for (int i = 0; i < N * 3; ++i) {
			if (js[i] > ma && win != i % N) {
				win = i % N;
				++ans;
			}
			ma = max(ma, js[i]);
		}
		return ans;
        }

        
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const int &Expected, const int &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 1; int Arr1[] = {812, 780, 815}; vector <int> Arg1(Arr1, Arr1 + (sizeof(Arr1) / sizeof(Arr1[0]))); int Arg2 = 1; verify_case(0, Arg2, countNewLeaders(Arg0, Arg1)); }
	void test_case_1() { int Arg0 = 2; int Arr1[] = {0, 0, 0, 0, 0, 0}; vector <int> Arg1(Arr1, Arr1 + (sizeof(Arr1) / sizeof(Arr1[0]))); int Arg2 = 1; verify_case(1, Arg2, countNewLeaders(Arg0, Arg1)); }
	void test_case_2() { int Arg0 = 2; int Arr1[] = {810, 811, 812, 813, 814, 815}; vector <int> Arg1(Arr1, Arr1 + (sizeof(Arr1) / sizeof(Arr1[0]))); int Arg2 = 6; verify_case(2, Arg2, countNewLeaders(Arg0, Arg1)); }
	void test_case_3() { int Arg0 = 3; int Arr1[] = {800, 10, 20, 810, 30, 40, 50, 830, 830}; vector <int> Arg1(Arr1, Arr1 + (sizeof(Arr1) / sizeof(Arr1[0]))); int Arg2 = 2; verify_case(3, Arg2, countNewLeaders(Arg0, Arg1)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	LongJump2 ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
