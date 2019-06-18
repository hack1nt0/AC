#include <bits/stdc++.h>
using namespace std;
class CentipedeSocks {
public:
	int fewestSocks(int C, int F, vector <int> sockCounts) {
		int n = sockCounts.size();
		vector<int> cnt(n);
		int used = 0;
		for (int i = 0; i < n; ++i) {
			int x = min(sockCounts[i], F - 1);
			cnt[i] = sockCounts[i] - x;
			used += x;
		}
		int pets = 0;
		while (pets < C) {
			sort(cnt.begin(), cnt.end(), greater<int>());
			int left = cnt[0];
			if (!left) break;
			pets++;
			if (pets == C) {
				used++;
				break;
			} else {
				int x = min(left, F);
				used += x;
				left -= x;
				cnt[0] = left;
			}
		}
		return pets < C ? -1 : used;
	}
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const int &Expected, const int &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 1; int Arg1 = 100; int Arr2[] = {1, 1, 1, 1, 100}; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); int Arg3 = 104; verify_case(0, Arg3, fewestSocks(Arg0, Arg1, Arg2)); }
	void test_case_1() { int Arg0 = 1; int Arg1 = 100; int Arr2[] = {40, 50, 60, 70}; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); int Arg3 = -1; verify_case(1, Arg3, fewestSocks(Arg0, Arg1, Arg2)); }
	void test_case_2() { int Arg0 = 3; int Arg1 = 10; int Arr2[] = {12345}; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); int Arg3 = 30; verify_case(2, Arg3, fewestSocks(Arg0, Arg1, Arg2)); }
	void test_case_3() { int Arg0 = 2; int Arg1 = 3; int Arr2[] = {4, 4, 5}; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); int Arg3 = 10; verify_case(3, Arg3, fewestSocks(Arg0, Arg1, Arg2)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	CentipedeSocks ___test; 
	___test.run_test(-1); 
} 
// END CUT HERE 
