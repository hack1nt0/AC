#include <bits/stdc++.h>
using namespace std;
#ifdef LOCAL
#include "../libs/debug.h"
//#include "../libs/bigint.h"
#endif
//WA
struct bigint {
	vector<int> xs;
	int base, sign;
	bigint(string input, int base = 10) : base(base) {
		assert(2 <= base && base <= 16);
		int n = input.size();
		int i = 0; sign = +1;
		xs.resize(n);
		for (int i = 0; i < n; ++i) xs[i] = mapd(input[n - i - 1]);
	}
	bigint(int base = 10) : base(base) {}
	bigint(const bigint& o) : xs(o.xs), base(o.base) {}
	static int mapd(char c) {
		if ('0' <= c && c <= '9') return c - '0';
		if ('a' <= c && c <= 'f') return c - 'a' + 10;
		return c - 'A' + 10;
	}
	static char mapc(int d) {
		if (0 <= d && d <= 9) return '0' + d;
		return 'A' + d - 10;
	}
	int size() const { return xs.size(); }
	int& operator[](int i) { return xs[i]; }
	int operator[](int i) const { return xs[i]; }
	int& x(int i) { return xs[size() - i - 1]; }
	int x(int i) const { return xs[size() - i - 1]; }
	bigint operator+(const bigint& o) {
		const bigint& a = *this, b = o;
		assert(a.base == b.base);
		bigint c(base);
		int r = 0;
		for (int i = 0; i < max(a.size(), b.size()); ++i) {
			int x = i < a.size() ? a[i] : 0;
			int y = i < b.size() ? b[i] : 0;
			c.xs.push_back((r + x + y) % base);
			r = (r + x + y) / base;
		}
		if (r > 0) c.xs.push_back(r);
		c.sign = +1;
		return c;
	}
	bigint operator+(int d) { return *this + bigint(to_string(d)); }
	bigint& operator+=(const bigint& o) { return *this = *this + o; }
	bigint& operator+=(int d) { return *this = *this + d; }
	
	friend ostream& operator<<(ostream& os, const bigint& o) {
		if (o.sign < 0) os << '-';
		if (o.base == 8) os << 0;
		if (o.base == 16) os << "0x";
		if (o.size() == 0) os << 0;
		else for (int i = o.size() - 1; i >= 0; --i) os << mapc(o[i]);
		return os;
	}
	string str() {
		ostringstream os;
		os << *this;
		return os.str();
	}
	void push_back(int d) { xs.push_back(d); }
	void pop_back() { xs.pop_back(); }
	int back() { return xs.back(); }
	void resize(int len) { xs.resize(len); }
	auto begin() { return xs.rbegin(); }
	auto end() { return xs.rend(); }
	void clear() { xs.clear(); }
};
typedef bigint vi;

class AddPeriodic { 
public: 
	string add(string A, string B) { 
		vi ax, ay, az, bx, by, bz;
		split(A, B, ax, ay, az, bx, by, bz);
		vi x = ax + bx, y = ay + by, z = getz(az, bz);
		if (z.size() > az.size()) {
			int nz = z.size();
			y += z.back();
			z.pop_back();
			z.resize(nz - 1);
		}
		if (y.size() > ay.size()) {
			int ny = y.size();
			x += y.back(); y.pop_back();
			y.resize(ny - 1);
		}
		//debug(A, ax, ay, az);
		//debug(B, bx, by, bz);
		//exit(1);
		shrinkz(z);
		//debug(z);
		shrinky(y, z);
		//debug(y);
		check9(x, y, z);
		//debug(x, y, z);
		return comb(x, y, z);
	}
	string comb(vi x, vi y, vi z) {
		string ret = "";
		for (int i : x) ret += char('0' + i);
		ret += '.';
		for (int i : y) ret += char('0' + i);
		ret += '(';
		for (int i : z) ret += char('0' + i);
		ret += ')';
		return ret;
	}
	void split(string a, string b, vi& ax, vi& ay, vi& az, vi& bx, vi& by, vi& bz) {
		string axs, ays, azs, bxs, bys, bzs;
		{
			string& x = axs;
			string& y = ays;
			string& z = azs;
			string& s = a;
			int i = 0;
			while (s[i] != '.') x.push_back(s[i++]);
			i++;
			while (s[i] != '(') y.push_back(s[i++]);
			i++;
			while (s[i] != ')') z.push_back(s[i++]);
			i++;
			assert(i == s.size());
		}
		{
			string& x = bxs;
			string& y = bys;
			string& z = bzs;
			string& s = b;
			int i = 0;
			while (s[i] != '.') x.push_back(s[i++]);
			i++;
			while (s[i] != '(') y.push_back(s[i++]);
			i++;
			while (s[i] != ')') z.push_back(s[i++]);
			i++;
			assert(i == s.size());
		}
		int ny = max(bys.size(), ays.size());
		int nz = bzs.size() * azs.size();
		{
			string& x = axs;
			string& y = ays;
			string& z = azs;
			string& s = a;
			string yy, zz;
			for (int i = 0; i < ny + nz; ++i) {
				int d = i < y.size() ? y[i] : z[(i - y.size()) % z.size()];
				if (i < ny) yy.push_back(d);
				else zz.push_back(d);
			}
			y = yy, z = zz;
		}
		{
			string& x = bxs;
			string& y = bys;
			string& z = bzs;
			string& s = b;
			string yy, zz;
			for (int i = 0; i < ny + nz; ++i) {
				int d = i < y.size() ? y[i] : z[(i - y.size()) % z.size()];
				if (i < ny) yy.push_back(d);
				else zz.push_back(d);
			}
			y = yy, z = zz;
		}
		ax = vi(axs);
		ay = vi(ays);
		az = vi(azs);
		bx = vi(bxs);
		by = vi(bys);
		bz = vi(bzs);
	}
	vi getz(vi a, vi b) {
		vi z = a + b;
		int na = a.size();
		int adv = 0;
		while (z.size() > na) {
			int add = z[na];
			adv += add;
			z.resize(na);
			z += add;
		}
		if (adv > 0) z.push_back(adv);
		return z;
	}
	void shrinkz(vi& a) {
		int na = a.size();
		int mn = na;
		for (int i = 1; i <= na; ++i) if (na % i == 0) {
			bool ok = true;
			for (int j = 0; j + i < na; ++j) ok &= a[j] == a[j + i];
			if (ok) { mn = i; break;}
		}
		a.resize(mn);
	}
	void shrinky(vi& y, vi& z) {
		int nz = z.size(), ny = y.size();
		int mn = ny;
		debug(y, z, mn);
		for (int i = 0; i < ny; ++i) {
			bool ok = true;
			for (int j = i; j < ny; ++j) {
				int a = y[j];
				int b = (j + nz < ny ? y.x(j + nz) : z.x(j + nz - ny));
				ok &= a == b;
				if (ok) { mn = i; break; }
			}
		}
		vi zz;
		for (int i = mn; i < nz + mn; ++i) zz.push_back(i < ny ? y.x(i) : z.x(i - ny));
		z = zz;
		y.resize(mn);
		debug(y, z, mn);
	}
	bool endwith(vi a, vi b) {
		bool ok = true;
		for (int i = 0; i < b.size(); ++i) ok &= a[a.size() - i - 1] == b[b.size() - i - 1];
		return ok;
	}
	void check9(vi& x, vi& y, vi& z) {
		if (z.size() == 1 && z[0] == 9) {
			z[0] = 0;
			if (y.size() > 0) {
				y += 1;
			} else {
				x += 1;
			}
		}
	}
// BEGIN CUT HERE
	public:
	void run_test(int Case) { if ((Case == -1) || (Case == 0)) test_case_0(); if ((Case == -1) || (Case == 1)) test_case_1(); if ((Case == -1) || (Case == 2)) test_case_2(); if ((Case == -1) || (Case == 3)) test_case_3(); if ((Case == -1) || (Case == 4)) test_case_4(); }
	private:
	template <typename T> string print_array(const vector<T> &V) { ostringstream os; os << "{ "; for (typename vector<T>::const_iterator iter = V.begin(); iter != V.end(); ++iter) os << '\"' << *iter << "\","; os << " }"; return os.str(); }
	void verify_case(int Case, const string &Expected, const string &Received) { cerr << "Test Case #" << Case << "..."; if (Expected == Received) cerr << "PASSED" << endl; else { cerr << "FAILED" << endl; cerr << "\tExpected: \"" << Expected << '\"' << endl; cerr << "\tReceived: \"" << Received << '\"' << endl; } }
	void test_case_0() { string Arg0 = "0.33(333)"; string Arg1 = "0.(66)"; string Arg2 = "1.(0)"; verify_case(0, Arg2, add(Arg0, Arg1)); }
	void test_case_1() { string Arg0 = "2.41(5)"; string Arg1 = "5.36(22)"; string Arg2 = "7.(7)"; verify_case(1, Arg2, add(Arg0, Arg1)); }
	void test_case_2() { string Arg0 = "685.4(757)"; string Arg1 = "45.356(43)"; string Arg2 = "730.832(210119)"; verify_case(2, Arg2, add(Arg0, Arg1)); }
	void test_case_3() { string Arg0 = "0.(101)"; string Arg1 = "0.(23)"; string Arg2 = "0.(333424)"; verify_case(3, Arg2, add(Arg0, Arg1)); }
	void test_case_4() { string Arg0 = "0.0(999999)"; string Arg1 = "1.5(00000)"; string Arg2 = "1.6(0)"; verify_case(4, Arg2, add(Arg0, Arg1)); }

// END CUT HERE
 
}; 

// BEGIN CUT HERE 
int main() {
	AddPeriodic ___test; 
//	___test.run_test(-1); 
	AddPeriodic obj;
	cout << obj.add("5.860(1750)", "153.8(06)") << endl;
} 
// END CUT HERE 
