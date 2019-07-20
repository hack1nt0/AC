#include <bits/stdc++.h>
using namespace std;
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace __gnu_pbds;

typedef tree<int, null_type, less<int>, rb_tree_tag, tree_order_statistics_node_update> redblack;
typedef tree<int, null_type, less<int>, splay_tree_tag, tree_order_statistics_node_update> splay;

template<class citerator, class iterator, class compare, typename allocator>
struct node_update {
	virtual citerator node_begin() const = 0;
	virtual citerator node_end() const = 0;
	virtual ~node_update() {}
	typedef unsigned int metadata_type;
	void foo() {
		auto it = node_begin(), null = node_end();
		while (it != null) {
		}
	}
	inline void operator()(iterator it, citerator null) {
		it.get_l_child().get_metadata();
		it.get_r_child().get_metadata();
		auto ptr_keyvalue = *it;
		const_cast<metadata_type&>(it.get_metadata()) = 0;
	}
};

ostream& operator<<(ostream& os, redblack xs) {
	for (auto x : xs) os << x << " ";
	return os;
}
ostream& operator<<(ostream& os, splay xs) {
	for (auto x : xs) os << x << " ";
	return os;
}

int main() {
	{
		vector<int> xs = {2, 3, 1};
		redblack t1(xs.begin(), xs.end());
		redblack t2;
		t1.split(2, t2);
		cout << "t1: " << t1 << endl << "-------" << endl;
		cout << "t2: " << t2 << endl << "-------" << endl;
		t1.join(t2);
		cout << "t1: " << t1 << endl << "-------" << endl;
		cout << "t2: " << t2 << endl << "-------" << endl;
	}
	return 0;
}
