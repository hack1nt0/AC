#include <bits/stdc++.h>
using namespace std;

mt19937 mt(std::chrono::steady_clock::now().time_since_epoch().count());

int rint(int l, int r) {
	assert(l <= r);
	uniform_int_distribution<int> dist(l, r);
	return dist(mt);
}
vector<int> rints(int n, int l, int r) {
	assert(n > 0 && l <= r);
	vector<int> xs(n);
	uniform_int_distribution<int> dist(l, r);
	for (int i = 0; i < n; ++i) xs[i] = dist(mt);
	return xs;
}
float rfloat(float l, float r) {
	assert(l <= r);
	uniform_real_distribution<float> dist(l, r);
	return dist(mt);
}
vector<float> rfloats(int n, float l, float r) {
	assert(n > 0 && l <= r);
	vector<float> xs(n);
	uniform_real_distribution<float> dist(l, r);
	for (int i = 0; i < n; ++i) xs[i] = dist(mt);
	return xs;
}
vector<int> rchoose(long long l, long long r, int k, bool back = false) {
	long long n = r - l + 1;
	assert(n > 0 && 0 <= k);
	if (k == 0) return {};
	vector<int> ret(k);
	if (!back) {
		assert(k <= n);
		for (int i = 0; i < k; ++i) ret[i] = l + i;
		for (int i = k + 1; i <= n; ++i)
			if (rint(1, i) <= k)
				ret[rint(0, k - 1)] = l + i - 1;
	} else {
		for (int i = 0; i < k; ++i)
			ret[i] = l + rint(0, n - 1);
	}
	return ret;
}
vector<int> rchoose(vector<float> weights, int k) {
	int n = weights.size();
	assert(n > 0 && 0 <= k && k <= n);
	if (k == 0) return {};
	map<float, int> mp;
	auto key = [](float w) { return log(rfloat(0, 1)) / w; };
	for (int i = 0; i < k; ++i) mp.insert({key(weights[i]), i});
	for (int i = k; i < n; ++i) {
		float wi = key(weights[i]);
		if (wi > mp.begin()->first) {
			mp.erase(mp.begin());
			mp.insert({wi, i});
		}
	}
	vector<int> ret(k);
	auto it = mp.begin();
	for (int i = 0; i < k; ++i, it++) ret[i] = it->second;
	return ret;
}
string rtree(int n, bool forest = false) {
	assert(n > 0);
	stringstream o;
	o << n << "\n";
	vector<int> perm(n), fa(n);
	for (int i = 0; i < n; ++i) perm[i] = i;
	shuffle(perm.begin(), perm.end(), mt);
	for (int i = 1; i < n; ++i) fa[i] = rint(0, i - 1);
	int lb = forest ? 0 : 1;
	for (int i = 1; i <= n - 1; ++i) {
		int u = perm[i] + 1, v = perm[fa[i]] + 1;
		if (rint(lb, 1))
			o << u << " " << v << "\n";
	}
	o.flush();
	return o.str();
}
string rbipartitie(long long na, long long nb, long long m) {
	long long n = na + nb;
	assert(n >= 0);
	stringstream o;
	vector<int> perm(n);
	for (int i = 0; i < n; ++i) perm[i] = i;
	shuffle(perm.begin(), perm.end(), mt);
	o << n << " " << m << "\n";
	long long tote = na * nb;
	for (int xy : rchoose(0, tote - 1, m)) {
		int x = xy / nb;
		int y = xy % nb;
		o << perm[x] + 1 << " " << perm[y] + 1 << "\n";
	}
	o.flush();
	return o.str();
}

string rdag(long long n, long long m) {
	assert(n > 0 && m >= 0);
	stringstream o;
	o << n << " " << m << "\n";
	if (n > 1 && m > 0) {
		vector<int> perm(n);
		vector<vector<int>> sons(n);
		for (int i = 0; i < n; ++i) perm[i] = i;
		shuffle(perm.begin(), perm.end(), mt);
		long long tote = n * (n - 1) / 2;
		auto getxy = [&](long long xy)->pair<long long, long long> {
			if (xy == tote - 1) return {n - 1, n - 2};
			long long x = 0, sum = 0;
			while (sum <= xy) sum += n - x - 1, x++;
			x--, sum -= n - x - 1;
			int y = x + xy - sum + 1;
			assert(y > x);
			return {y, x};
		};
		for (long long xy : rchoose(0, tote - 1, m)) {
			long long x = getxy(xy).first;
			long long y = getxy(xy).second;
			o << perm[x] + 1 << " " << perm[y] + 1 << "\n";
		}
	}
	o.flush();
	return o.str();
}
string rdg(long long n, long long m) {
	assert(n > 0);
	stringstream o;
	o << n << " " << m << "\n";
	long long tote = n * n;
	for (long long xy : rchoose(0, tote - 1, m)) {
		long long x = xy / n;
		long long y = xy % n;
		o << x + 1 << " " << y + 1 << "\n";
	}
	o.flush();
	return o.str();
}
string rug(long long n, long long m) {
	assert(n > 0 && m >= 0);
	stringstream o;
	o << n << " " << m << "\n";
	long long tote = n * (n + 1) / 2;
	auto getxy = [&](long long xy)->pair<long long, long long> {
		if (xy == tote - 1) return {n - 1, n - 1};
		long long x = 0, sum = 0;
		while (sum <= xy) sum += n - x, x++;
		sum -= x, x--;
		return {x, x + xy - sum};
	};
	for (long long xy : rchoose(0, tote - 1, m)) {
		long long x = getxy(xy).first;
		long long y = getxy(xy).second;
		o << x + 1 << " " << y + 1 << "\n";
	}
	o.flush();
	return o.str();
}

vector<vector<float>> rfloatmat(int n, int m, float l, float r) {
	vector<vector<float>> a(n, vector<float>(m));
	for (int i = 0; i < n; ++i) for (int j = 0; j < m; ++j) a[i][j] = rfloat(l, r);
	return a;
}

template <typename T>
void error(string input, T output, T correct) {
	cerr << "Input:\n" << input << "\n--------------\n";
	cerr << "Output:\n" << output << "\n--------------\n";
	cerr << "Correct:\n" << correct << "\n==============\n";
	exit(1);
}

template <typename T>
ostream& operator<<(ostream& os, const vector<T>& xs) { for (int x : xs) os << x << " "; return os;}
