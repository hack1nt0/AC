#include <bits/stdc++.h>
using namespace std;

class NewBanknote { 
public: 
	int sum(const vector<int>& xs) {
		int r = 0;
		for (int x : xs) r += x;
		return r;
	}
	vector <int> fewestPieces(int newBanknote, vector <int> amountsToPay) { 
		vector<int> cents = {1, 2, 5, 10, 20, 50, 100, 200,
			500, 1000, 2000, 5000, 10000, 20000, 50000
		};
		int n = cents.size();
		vector<int> ret;
		auto decode = [&](int y) {
			vector<int> xs(n);
			for (int i = n - 1; i >= 0; --i) {
				int mul = y / cents[i];
				xs[i] = mul;
				y -= cents[i] * mul;
			}
			assert(!y);
			return xs;
		};
		auto print = [](vector<int>& xs) {
			for (int i = 0; i < xs.size(); ++i) cout << xs[i] << " ";
			cout << endl;
		};
		const int oo = (int) 2e9 + 3;
		for (int y : amountsToPay) {
			int mul = 0;
			int x = oo;
			while (mul < x && (long long) mul * newBanknote <= y) {
				x = min(x, mul + sum(decode(y - mul * newBanknote)));
				mul++;
			}
			ret.push_back(x);
		}
		return ret;
	} 
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const vector <int> &Expected, const vector <int> &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: " << print_array(Expected) << endl; cerr << "\tReceived: " << print_array(Received) << endl; } }
	void test_case_0() { int Arg0 = 4700; int Arr1[] = {53, 9400, 9401, 30000}; vector <int> Arg1(Arr1, Arr1 + (sizeof(Arr1) / sizeof(Arr1[0]))); int Arr2[] = {3, 2, 3, 2 }; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); verify_case(0, Arg2, fewestPieces(Arg0, Arg1)); }
	void test_case_1() { int Arg0 = 1234; int Arr1[] = {1233, 1234, 1235}; vector <int> Arg1(Arr1, Arr1 + (sizeof(Arr1) / sizeof(Arr1[0]))); int Arr2[] = {6, 1, 2 }; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); verify_case(1, Arg2, fewestPieces(Arg0, Arg1)); }
	void test_case_2() { int Arg0 = 1000; int Arr1[] = {1233, 100047}; vector <int> Arg1(Arr1, Arr1 + (sizeof(Arr1) / sizeof(Arr1[0]))); int Arr2[] = {6, 6 }; vector <int> Arg2(Arr2, Arr2 + (sizeof(Arr2) / sizeof(Arr2[0]))); verify_case(2, Arg2, fewestPieces(Arg0, Arg1)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	NewBanknote ___test; 
	___test.run_test(-1); 
} 
// END CUT HERE 
