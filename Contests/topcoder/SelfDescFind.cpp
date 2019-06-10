#include <bits/stdc++.h>
using namespace std;

class SelfDescFind { 
public: 
	int n;
	vector<pair<int, int>> ans;
	int all;
	int left;
	vector<int> del;
	typedef pair<int, vector<int>> state;
	set<state> cache;
	bool rec(int i) {
		state cur = {left, del};
		if (cache.count(cur)) {
			//cerr << "HIT" << endl;
			return false;
		}
		if (i >= n) {
			bool ok = true;
			for (int j = 0; j < 10; ++j) ok &= !del[j];
			return ok;
		}
		int sum = 0;
		for (int j = 0; j < 10; ++j) sum += abs(del[j]);
		if (sum > n - i)
			return false;
		for (int x = 1; x < 10; ++x) if ((all >> x) & 1) {
			for (int y = 0; y < 10; ++y) if ((left >> y) & 1) {
				--del[x];
				del[y] += x - 1;
				left ^= 1 << y;
				if (rec(i + 1)) {
					ans.push_back({x, y});
					return true;
				}
				++del[x];
				del[y] -= x - 1;
				left |= 1 << y;
			}
		}
		cache.insert(cur);
		return false;
	}
        string construct(vector <int> digits) { 
		n = digits.size();
		ans.resize(0);
		cache.clear();
		all = 0;
		del.resize(10, 0);
		for (int d : digits) all |= 1 << d;
		left = all;
		bool ok = rec(0);
		string s;
		if (!ok) s = "";
		else {
			s.resize(n * 2);
			sort(ans.begin(), ans.end());
			for (int i = 0; i < n; ++i) {
				s[i * 2 + 0] = (char) ('0' + ans[i].first);
				s[i * 2 + 1] = (char) ('0' + ans[i].second);
			}
		}
		return s;
        }

        
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); if ((Case == -1) || (Case == 5)) test_case_5(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const string &Expected, const string &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { int Arr0[] = {1}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); string Arg1 = ""; verify_case(0, Arg1, construct(Arg0)); }
	void test_case_1() { int Arr0[] = {2}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); string Arg1 = "22"; verify_case(1, Arg1, construct(Arg0)); }
	void test_case_2() { int Arr0[] = {0,1,3,4}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); string Arg1 = "10143133"; verify_case(2, Arg1, construct(Arg0)); }
	void test_case_3() { int Arr0[] = {0,1,2,4,5,6,8,9}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); string Arg1 = ""; verify_case(3, Arg1, construct(Arg0)); }
	void test_case_4() { int Arr0[] = {0,1,2,3,5,6,8,9}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); string Arg1 = "1016181923253251"; verify_case(4, Arg1, construct(Arg0)); }
	void test_case_5() { int Arr0[] = {4}; vector <int> Arg0(Arr0, Arr0 + (sizeof(Arr0) / sizeof(Arr0[0]))); string Arg1 = ""; verify_case(5, Arg1, construct(Arg0)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	SelfDescFind ___test; 
	___test.run_test(-1); 
}
// END CUT HERE 
