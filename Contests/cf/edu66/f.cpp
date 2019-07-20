#include <bits/stdc++.h>
///using namespace std;

typedef long long llt;
template <typename T>
struct generator {
	std::mt19937 mt;
	std::uniform_int_distribution<T> dist;
	generator(T l = std::numeric_limits<T>::min(), T r = std::numeric_limits<T>::max()) : mt(std::chrono::steady_clock::now().time_since_epoch().count()), dist(l, r) {}
	T operator()() { return dist(mt); }
};
typedef long long llt;
const int maxn = (int) 3e5 + 4;
int xs[maxn];
int left[maxn];
int right[maxn];
llt hash[maxn];
llt tcode[maxn];
llt pcode[maxn];
std::set<llt> st;

bool check(int from, int to) {
	st.clear();
	for (int i = from; i < to; ++i) {
		if (st.count(xs[i]))
			return false;
		st.insert(xs[i]);
	}
	return *st.begin() == 1 && *st.end()-- == to - from;
}
int main() {
	std::cin.sync_with_stdio(0);
	std::cin.tie(0);
	
	int n; std::cin >> n;
	for (int i = 0; i < n; ++i)
		std::cin >> xs[i];

	left[0] = -1;
	for (int i = 1; i < n; ++i) {
		int ptr = i - 1;
		while (ptr >= 0 && xs[ptr] < xs[i]) ptr = left[ptr];
		left[i] = ptr;
	}
	right[n - 1] = n;
	for (int i = n - 2; i >= 0; --i) {
		int ptr = i + 1;
		while (ptr < n && xs[i] > xs[ptr]) ptr = right[ptr];
		right[i] = ptr;
	}
	llt ans = 0;

	for (int i = 1; i <= n; ++i) hash[i] = 0;
	generator<llt> gen(1);
	for (int i = 0; i < n; ++i) if (!hash[xs[i]]) {
		llt code = gen();
		while (st.count(code)) code = gen();
		hash[xs[i]] = code;
	}
	tcode[0] = 0;
	for (int i = 1; i <= n; ++i) {
		tcode[i] = tcode[i - 1] ^ hash[i];
	}
	pcode[0] = 0;
	for (int i = 1; i <= n; ++i) {
		pcode[i] = pcode[i - 1] ^ hash[xs[i - 1]];
		///std::cout << pcode[i] << std::endl;
	}

	for (int i = 0; i < n; ++i) {
		int l = std::max(i - xs[i] + 1, left[i] + 1);
		int r = std::min(i + xs[i], right[i]);
		if (r - l < xs[i]) continue;
		int p = l, q = l + xs[i];
		llt code = pcode[l + xs[i]] ^ pcode[l];
		///std::cout << p << " " << q << " " << code << " ? " << tcode[xs[i]] << std::endl;
		int c = 0;
		while (q <= r) {
			if (q - p == xs[i] && code == tcode[xs[i]]) {
				//if (check(p, q)) ++ans;
				++ans;
				++c;
			}
			if (q == r) break;
			code = code ^ hash[xs[q++]];
			if (q - p > xs[i]) code ^= hash[xs[p++]];
		}
		//if (c > 0) std::cerr << i << " of " << n << " = " << c << std::endl;
	}
	std::cout << ans << std::endl;
	return 0;
}
