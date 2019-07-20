
#include <bits/stdc++.h>

template <typename T>
struct generator {
	std::mt19937 mt;
	std::uniform_int_distribution<T> dist;
	generator(T l = std::numeric_limits<T>::min(), T r = std::numeric_limits<T>::max()) : mt(std::chrono::steady_clock::now().time_since_epoch().count()), dist(l, r) {}
	T operator()() { return dist(mt); }
};

int main() {
	generator<long long> g(1, 2);
	std::cout << g() << std::endl;
	std::cout << g() << std::endl;
	return 0;
}
