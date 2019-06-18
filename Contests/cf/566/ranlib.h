#include <bits/stdc++.h>
using namespace std;

mt19937 mt(std::chrono::steady_clock::now().time_since_epoch().count());
uniform_int_distribution<int> distint;

int rint(int l = 0, long long r = numeric_limits<int>::max() + 1LL) {
	assert(l < r);
	return (int) (l + distint(mt) % (r - l));
}

string rtree(int n) {
	assert(n > 0);
	stringstream o;
	o << n << "\n";
	vector<int> perm(n), fa(n);
	for (int i = 0; i < n; ++i) perm[i] = i;
	shuffle(perm.begin(), perm.end(), mt);
	for (int i = 1; i < n; ++i) fa[i] = rint(0, i);
	for (int i = 1; i <= n - 1; ++i) {
		int u = perm[i] + 1, v = perm[fa[i]] + 1;
		o << u << " " << v << "\n";
	}
	o.flush();
	return o.str();
}

template <typename T>
void error(string input, T output, T correct) {
	cout << "Input:\n" << input << "\n--------------\n";
	cout << "Output:\n" << output << "\n--------------\n";
	cout << "Correct:\n" << correct << "\n==============\n";
	exit(1);
}
