#include <bits/stdc++.h>
using namespace std;


template <typename T = double, int dim = 2>
struct point {
	static constexpr T eps = 1e-9;
	typedef point<T, dim> type;
	typedef const point<T, dim>& constref;
	typedef point<T, dim>& ref;
	T x[dim];
	point(vector<T> xs) { for (int i = 0; i < dim; ++i) x[i] = xs[i]; }
	type operator+(constref o) { return point<T>(
};
struct line {};
struct segment {};
struct circle {};
struct polygon {};
struct plane {};
