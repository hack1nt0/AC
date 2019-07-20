#include <bits/stdc++.h>
using namespace std;

int main() {
	int maxn = 2e5;
	cout << maxn << endl;
	for (int i = 0, n1 = 0; i < maxn; ++i) {
		int x = (rand() % 2);
		if (i == maxn - 1 && n1 % 2) x = 1;
		if (x == 1) n1++;
		cout << x;
	}
	cout << endl;
	return 0;
}
