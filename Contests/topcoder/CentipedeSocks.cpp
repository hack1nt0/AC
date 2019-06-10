#include <bits/stdc++.h>
using namespace std;

class CentipedeSocks { 
public: 
        int fewestSocks(int C, int F, vector <int> sockCounts) { 
		int ns = sockCounts.size();
		int tot = 0;
		for (int i = 0; i < ns; ++i) tot += sockCounts[i];
		int l = 0, r = tot + 1;
		while (l + 1 < r) {
			int mid = r - (r - l) / 2;
			vector<int> cnt = sockCounts;
			int c = 0;
			int s = mid;
			for (int i = 0; i < ns; ++i) {
				int del = min(min(cnt[i], F - 1), s);
				s -= del;
				cnt[i] -= del;
			}
			while (s > 0 && c < C) {
				sort(cnt.begin(), cnt.end());
				if (cnt[ns - 1] == 0)
					break;
				int del = min(min(cnt[ns - 1], 1 + F - 1), s);
				s -= del;
				cnt[ns - 1] -= del;
				++c;
			}
			assert(s >= 0);
			if (c >= C)
				r = mid;
			else 
				l = mid;
		}
		int ans = r;
		if (ans == tot + 1) ans = -1;
		return ans;
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
