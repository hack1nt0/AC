#include <bits/stdc++.h>
using namespace std;
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/trie_policy.hpp>
using namespace __gnu_pbds;

template<class citerator, class iterator, class compare, typename allocator>
struct trie_node_update {
	virtual citerator node_begin() const = 0;
	virtual citerator node_end() const = 0;
	virtual ~trie_node_update() {}
	typedef unsigned int metadata_type;
	void foo() {
		auto it = node_begin(), null = node_end();
		while (it != null) {
		}
	}
	inline void operator()(iterator it, citerator null) {
		it.extract_key();
		it.get_l_child().get_metadata();
		it.get_r_child().get_metadata();
		auto ptr_keyvalue = *it;
		const_cast<metadata_type&>(it.get_metadata()) = 0;
	}
};

struct trie_dna_traits {
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
typedef trie<string, int, trie_dna_traits> trie1;
typedef trie<string, int, trie_string_access_traits<>, pat_trie_tag, trie_prefix_search_node_update> trie2;
typedef trie<string, int, trie_string_access_traits<>, pat_trie_tag, trie_order_statistics_node_update> trie3;

int main() {
	{
		trie1 t;
		t["ACCGGTTACTGGTA"] = 1;
		t["ACGTTATCGGTA"] = 2;
		assert(t.find("ACCGGTTACTGGTA") != t.end());
	}
	{
		trie2 t = t;
		t["ACCGGTTACTGGTA"] = 1;
		t["ACGTTATCGGTA"] = 2;
		auto match_range = t.prefix_range("A");
		for (auto it = match_range.first; it != match_range.second; ++it)
			cout << it->first << "\t" << it->second << endl;
	}
	{
		trie3 t = t;
		t["ACCGGTTACTGGTA"] = 1;
		t["ACGTTATCGGTA"] = 2;
		cout << t.find_by_order(1)->first << endl;
		cout << t.order_of_key("AC") << endl;
		cout << t.order_of_key("AD") << endl;
	}
	return 0;
}
