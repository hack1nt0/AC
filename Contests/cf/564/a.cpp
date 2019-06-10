#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int x, y, z;
	cin >> x >> y >> z;
	set<char> ans;
	if (x + z > y) ans.insert('+');
	if (x + z < y) ans.insert('-');
	if (x > y + z) ans.insert('+');
	if (x < y + z) ans.insert('-');
	if (x + z == y) ans.insert('0');
	if (x == y + z) ans.insert('0');
	assert(!ans.empty());
	if (ans.size() == 1)
		cout << *ans.begin() << endl;
	else 
		cout << '?' << endl;
	return 0;
}
