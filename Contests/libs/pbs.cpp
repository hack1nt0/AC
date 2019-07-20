#include <bits/stdc++.h>
using namespace std;
#include <ext/pb_ds/priority_queue.hpp>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace __gnu_pbds;

typedef __gnu_pbds::priority_queue<int, greater<int>, pairing_heap_tag> minheap;
typedef __gnu_pbds::priority_queue<int, less<int>, pairing_heap_tag> maxheap;
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

struct custom_hash {
	static uint64_t splitmix64(uint64_t x) {
		// http://xorshift.di.unimi.it/splitmix64.c
		x += 0x9e3779b97f4a7c15;
		x = (x ^ (x >> 30)) * 0xbf58476d1ce4e5b9;
		x = (x ^ (x >> 27)) * 0x94d049bb133111eb;
		return x ^ (x >> 31);
	}
	size_t operator()(uint64_t x) const {
		static const uint64_t FIXED_RANDOM = chrono::steady_clock::now().time_since_epoch().count();
		return splitmix64(x + FIXED_RANDOM);
	}
};
typedef gp_hash_table<long long, int, custom_hash> hashmap;
typedef gp_hash_table<long long, null_type, custom_hash> hashset;

struct dna_trie_traits {
	typedef size_t size_type;
	typedef string key_type;
	typedef const key_type& key_const_reference;
	typedef char e_type;
	typedef string::const_iterator const_iterator;
	enum { max_size = 4 };
	inline static const_iterator begin(key_const_reference key) { return key.begin(); }
	inline static const_iterator end(key_const_reference key) { return key.end(); }
	inline static size_t e_pos(e_type e) {
		switch(e) {
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: std::abort();
		};
	}
};
typedef trie<string, int, dna_trie_traits> trie;
typedef trie<string, int, trie_string_access_traits, pat_trie_tag, trie_prefix_search_node_update> trie2;
typedef trie<string, int, trie_string_access_traits, pat_trie_tag, trie_order_statistics_node_update> trie3;

int main() {
	vector<int> xs = {2, 3, 1, 0};
	maxheap h(xs.begin(), xs.end());
	cout << *h.begin() + 1 << endl;
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
