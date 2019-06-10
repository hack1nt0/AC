#include <bits/stdc++.h>
using namespace std;

class MostFrequentLastDigit { 
public: 
	vector <int> generate(int n, int d) { 
		vector<int> a(n);
		if (d % 2 == 0) {
			int last = d == 0 ? 5 : d / 2;
			for (int i = 0; i < n; ++i)
				a[i] = last;
		} else {
			int l = 0, r = n - 1;
			int x = 1;
			while (l < r) {
				// (x + y) % 10 = d;
				int y = (d - x + 10) % 10;
				if (y != 0) {
					a[l++] = x;
					a[r--] = y;
				}
				x = (x + 2) % 10;
			}
			if (n % 2 == 1 && n > 1) a[n / 2] = a[n / 2 + 1];
		}
		for (int i = 0; i < n; ++i) {
			a[i] += i * 10;
		}
		return a;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const vector <int> &Expected, const vector <int> &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: " << print_array(Expected) << endl; cerr << "\tReceived: " << print_array(Received) << endl; } }
	void test_case_0() { int Arg0 = 4; int Arg1 = 7; int Arr2[] = {1, 6, 13, 4 }; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); verify_case(0, Arg2, generate(Arg0, Arg1)); }
	void test_case_1() { int Arg0 = 2; int Arg1 = 8; int Arr2[] = {44, 444 }; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); verify_case(1, Arg2, generate(Arg0, Arg1)); }
	void test_case_2() { int Arg0 = 7; int Arg1 = 2; int Arr2[] = {109, 22, 74, 23, 29, 9, 1003 }; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); verify_case(2, Arg2, generate(Arg0, Arg1)); }

// END CUT HERE
 
};

// BEGIN CUT HERE 
int main() {
	MostFrequentLastDigit ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
