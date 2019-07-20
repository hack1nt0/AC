#include <bits/stdc++.h>
using namespace std;

template <typename T>
struct generator {
	std::mt19937 mt;
	std::uniform_int_distribution<T> dist;
	generator(T l = std::numeric_limits<T>::min(), T r = std::numeric_limits<T>::max()) : mt(std::chrono::steady_clock::now().time_since_epoch().count()), dist(l, r) {}
	T operator()() { return dist(mt); }
};
const int maxn = (int) 3e5 + 4;

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int n = maxn;
	cout << n << endl;
	generator<int> g(1, n);
	for (int i = 0; i < n; ++i) cout << (i + 1) << " ";
	cout << endl;
	return 0;
}
	
