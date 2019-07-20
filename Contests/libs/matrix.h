#include <bits/stdc++.h>
using namespace std;

template <typename T>
int gauss(vector<vector<T>>& a) {
	int n = a.size(), m = a.at(0).size();
	assert(n <= m);
	int free = 0;
	for (int v = 0; v < n; ++v) {
		int r = v;
		for (int i = r + 1; i < n; ++i) if (abs(a[i][v]) > abs(a[r][v])) r = i;
		swap(a[r], a[v]);
		for (int i = 0; i < n; ++i) if (i != r && a[i][v] != 0) {
			T mulv = a[i][v], muli = a[v][v];
			for (int j = 0; j < m; ++j) a[i][j] = a[i][j] * muli - a[v][j] * mulv;
		}
	}
	for (int i = 0; i < n; ++i) {
		if (a[i][i] == 0 && a[i][n] != 0) free = -n * 2;
		if (a[i][i] == 0 && a[i][n] == 0) free++;
	}
	return free;
}
template <typename T>
struct matrix {
	int n, m;
	vector<vector<T>> xs;
	static constexpr T eps = 1e-9;
	matrix(int _n, int _m) : n(_n), m(_m), xs(n, vector<T>(m)) {}
	matrix(int _n, int _m, T v) : n(_n), m(_m), xs(n, vector<T>(m, v)) {}
	matrix(const matrix<T>& o) : n(o.n), m(o.m), xs(o.xs) {}
	matrix(const vector<vector<T>>& o) : n(o.size()), m(o.at(0).size()), xs(o) {}
	matrix<T>& operator=(T v) {
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) xs[i][j] = v;
		return *this;
	}
	matrix<T>& operator=(const matrix<T>& o) {
		assert(n == o.n && m == o.m);
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) xs[i][j] = o[i][j];
		return *this;
	}
	static matrix<T> identity(int n) {
		matrix<T> r(n, n, 0);
		for (int i = 0; i < n; ++i) r[i][i] = 1;
		return r;
	}
	vector<T>& operator[](int i) { return xs[i]; }
	const vector<T>& operator[](int i) const { return xs[i]; }
	matrix<T> operator+(const matrix<T>& o) {
		assert(n == o.n && m == o.m);
		matrix<T> r(n, m);
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) r[i][j] = xs[i][j] + o[i][j];
		return r;
	}
	matrix<T> operator-(const matrix<T>& o) {
		assert(n == o.n && m == o.m);
		matrix<T> r(n, m);
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < m; ++j) r[i][j] = xs[i][j] - o[i][j];
		return r;
	}
	matrix<T> operator*(const matrix<T>& o) {
		assert(m == o.n);
		matrix<T> r(n, o.m, 0);
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < o.m; ++j)
				for (int k = 0; k < m; ++k) r[i][j] += xs[i][k] * o[k][j];
		return r;
	}
	matrix<T>& operator*=(const matrix<T>& o) { *this = *this * o; return *this; }
	matrix<T> operator/(const matrix<T>& o) {
		assert(n == m && m == o.n && o.n == o.m);
		return *this * o.inverse();
	}
	T determinant() {
		assert(n == m);
		matrix<T> a = *this;
		gauss(a.xs);
		T r = 1;
		for (int i = 0; i < n; ++i) r *= a[i][i];
		return r;
	}
	matrix<T> inverse() const {
		matrix<T> a(n, n + n, 0);
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) a[i][j] = xs[i][j];
			a[i][i + n] = 1;
		}
		for (int v = 0; v < n; ++v) {
			assert(a[v][v] > 0);
			for (int i = 0; i < n; ++i) if (i != v && a[i][v] != 0) {
				T mulv = a[i][v], muli = a[v][v];
				for (int j = 0; j < n + n; ++j) a[i][j] = a[i][j] * muli - a[v][j] * mulv;
			}
		}
		matrix<T> r(n, n);
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j) r[i][j] = a[i][j + n] / a[i][i];
		return r;
	}
	matrix<T> pow(int p) {
		assert(n == m);
		matrix<T> r = identity(n);
		matrix<T> acc = *this;
		while (p) {
			if (p & 1) r *= acc;
			p >>= 1;
			acc *= acc;
		}
		return r;
	}
	friend ostream& operator<<(ostream& os, const matrix<T>& o) {
		for (int i = 0; i < o.n; ++i) {
			for (int j = 0; j < o.m; ++j) os << o[i][j] << " ";
			cout << endl;
		}
		return os;
	}
	bool operator==(const matrix<T>& b) const {
		const matrix<T>& a = *this;
		assert(a.n == b.n && a.m == b.m);
		bool eq = true;
		for (int i = 0; i < a.n; ++i)
			for (int j = 0; j < a.m; ++j) eq &= abs(a[i][j] - b[i][j]) <= eps;
		return eq;
	}
};









