#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

const int maxl = (int) 1e5 + 3;
int stk[maxl];
llt prod[maxl];

int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int l; cin >> l;
	llt add = 0;
	const llt oo = 1LL << 32;
	int ptr = 0;
	prod[0] = 1;
	while (l-- > 0) {
		string s; cin >> s;
		if (s[0] == 'a') {
			llt mul = prod[ptr];
			if (mul >= oo) {
				add = -1;
				break;
			}
			add += mul;
			if (add >= oo) {
				add = -1;
				break;
			}
		} else if (s[0] == 'f') {
			int x; cin >> x;
			stk[ptr++] = x;
			prod[ptr] = prod[ptr - 1] * x;
			if (prod[ptr] >= oo) 
				prod[ptr] = oo;
		} else {
			--ptr;
		}
	}
	if (add == -1)
		cout << "OVERFLOW!!!" << endl;
	else 
		cout << add << endl;
	return 0;
}
