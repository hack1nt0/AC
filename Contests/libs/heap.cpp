#include <bits/stdc++.h>
using namespace std;
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/priority_queue.hpp>
using namespace __gnu_pbds;

typedef __gnu_pbds::priority_queue<int, greater<int>, pairing_heap_tag> minheap;
typedef __gnu_pbds::priority_queue<int, less<int>, pairing_heap_tag> maxheap;

int main() {
	vector<int> xs = {2, 3, 1, 0};
	maxheap h(xs.begin(), xs.end());
	maxheap h2;
	h.split([](int x){return x <= 2;}, h2);
	auto print = [](const maxheap& _h) {
		maxheap h = _h;
		while (h.size()) {
			cout << h.top() << endl;
			h.pop();
		}
	};
	print(h);
	cout << "-------" << endl;
	print(h2);

	return 0;
}
