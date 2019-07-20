#include <bits/stdc++.h>
using namespace std;

struct modint {
	typedef long long T;
	const static T mod = (int) 1e9 + 7;
	T x;
	modint(T o) : x((o % mod + mod) % mod) {}
	modint(const modint& o) : x(o.x) {}
	modint& operator=(const modint& o) { x = o.x; return *this; }
	modint& operator=(T o) { x = (o % mod + mod) % mod; return *this; }
	bool operator<(const modint& o) { return x < o.x; }
	bool operator<=(const modint& o) { return x <= o.x; }
	bool operator>(const modint& o) { return x > o.x; }
	bool operator>=(const modint& o) { return x >= o.x; }
	bool operator==(const modint& o) { return x == o.x; }
	modint operator+(const modint& o) { return modint(x + o.x); }
	modint operator-(const modint& o) { return modint(x - o.x); }
	modint operator*(const modint& o) { return modint(x * o.x); }
	modint operator/(const modint& o) { return modint(x * modinv(o.x)); }
	modint& operator+=(const modint& o) { *this = x + o.x; return *this; }
	modint& operator-=(const modint& o) { *this = x - o.x; return *this; }
	modint& operator*=(const modint& o) { *this = x * o.x; return *this; }
	modint& operator/=(const modint& o) { *this = x * modinv(o.x); return *this; }
	T modinv(T _x) {
		T y = 1;
		T p = mod - 2;
		T acc = _x;
		while (p) {
			if (p & 1) y = y * acc % mod;
			p >>= 1;
			acc = acc * acc % mod;
		}
		return y;
	}
	modint inverse() { return modint(modinv(x)); }
	friend ostream& operator<<(ostream& os, modint x) { os << x.x; return os; }
	friend modint pow(const modint& x, int p) {
		bool neg = p < 0;
		p = abs(p);
		modint y = 1;
		modint acc = x;
		while (p) {
			if (p & 1) y *= acc;
			p >>= 1;
			acc *= acc;
		}
		if (neg) y = y.inverse();
		return y;
	}
};

	
