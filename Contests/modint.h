#include <bits/stdc++.h>
using namespace std;

modint pow(const modint& x, int p) {
	modint y = 1;
	modint acc = x;
	while (p) {
		if (p & 1) y *= acc;
		p >>= 1;
		acc *= acc;
	}
	return y;
}
struct modint {
	typedef long long T;
	static T mod;
	T x;
	modint(T o) : x((o % mod + o) % mod) {}
	modint(const modint& o) : x(o.x) {}
	modint& operator=(const modint& o) { x = o.x; return *this; }
	modint& operator=(T o) { x = (o % mod + o) % mod; return *this; }
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
	T modinv(T x) {
		T y = 1;
		T p = mod - 2;
		T acc = x;
		while (p) {
			if (p & 1) y = y * acc % mod;
			p >>= 1;
			acc = acc * acc % mod;
		}
		return y;
	}
};

	
