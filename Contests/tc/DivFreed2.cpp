#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../libs/debug.h"
#endif
#include "../libs/modint.h"
typedef modint llt;
#define pb push_back
#define all(x) x.begin(),x.end()
typedef vector<llt> vi;
typedef vector<vi> vvi;

//WA
class DivFreed2 { 
public: 
	int count(int n, int k) { 
		const int mod = (int) 1e9 + 7;
		llt tot = pow(llt(k), n);
		llt del = 0;
		vector<vector<int>> divs(k + 1);
		for (int i = 2; i <= k; ++i) {
			for (int d = 2; d * d <= i; ++d) if (i % d == 0) {
				divs[i].pb(d);
				if (d != i / d) divs[i].pb(i / d);
			}
			divs[i].pb(1);
		}
		//debug(divs);
		vvi dp(k + 1, vi(n + 1, 1));
		vi dp2(n + 1, 0);
		for (int i = 1; i <= k; ++i)
			for (int j = 1; j <= n; ++j) {
				auto& r = dp[i][j];
				r = 0;
				for (int d : divs[i])
					r += dp[d][j - 1];
				dp2[j] += r;
			}
		//debug(dp2);
		for (int s = 1; s < 1 << n - 1; ++s) {
			llt rs = 1;
			int sign = 0;
			vector<int> xs;
			for (int i = 0; i < n - 1; ++i) if ((s >> i & 1)) {
				xs.pb(i), xs.pb(i + 1);
				sign++;
			}
			sign = sign % 2 ? +1 : -1;
			sort(all(xs));
			xs.resize(unique(all(xs)) - xs.begin());
			rs *= pow(llt(k), n - xs.size());
			for (int i = 0; i < xs.size();) {
				int j = i + 1;
				while (j < xs.size() && xs[j] == xs[j - 1] + 1) j++;
				assert(j - i >= 2);
				rs *= dp2[j - i - 1];
				i = j;
			}
			rs *= sign;
			del += rs;
		}
		//cout << (tot - del) % mod << endl;
		cout << tot - del << endl;
		return -1;
	}
	
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); if ((Case == -1) || (Case == 5)) test_case_5(); if ((Case == -1) || (Case == 6)) test_case_6(); if ((Case == -1) || (Case == 7)) test_case_7(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const int &Expected, const int &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arg0 = 2; int Arg1 = 2; int Arg2 = 3; verify_case(0, Arg2, count(Arg0, Arg1)); }
	void test_case_1() { int Arg0 = 9; int Arg1 = 1; int Arg2 = 1; verify_case(1, Arg2, count(Arg0, Arg1)); }
	void test_case_2() { int Arg0 = 3; int Arg1 = 3; int Arg2 = 15; verify_case(2, Arg2, count(Arg0, Arg1)); }
	void test_case_3() { int Arg0 = 1; int Arg1 = 107; int Arg2 = 107; verify_case(3, Arg2, count(Arg0, Arg1)); }
	void test_case_4() { int Arg0 = 2; int Arg1 = 10; int Arg2 = 83; verify_case(4, Arg2, count(Arg0, Arg1)); }
	void test_case_5() { int Arg0 = 2; int Arg1 = 1234; int Arg2 = 1515011; verify_case(5, Arg2, count(Arg0, Arg1)); }
	void test_case_6() { int Arg0 = 3; int Arg1 = 8; int Arg2 = 326; verify_case(6, Arg2, count(Arg0, Arg1)); }
	void test_case_7() { int Arg0 = 10; int Arg1 = 100000; int Arg2 = 526882214; verify_case(7, Arg2, count(Arg0, Arg1)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	/*
	DivFreed2 ___test; 
	___test.run_test(7); 
	*/
	int n = 5e4;
	set<multiset<int>> st;
	for (int i = 2; i <= n; ++i) {
		multiset<int> ms;
		int ii = i;
		for (int d = 2; d * d <= ii; ++d) if (ii % d == 0) {
			int x = 0;
			while (ii % d == 0) x++, ii /= d;
			ms.insert(x);
		}
		if (ii > 1) ms.insert(1);
		st.insert(ms);
	}
	debug(st);
	debug(st.size());
} 
// END CUT HERE 
