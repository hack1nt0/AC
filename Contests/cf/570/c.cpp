#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

int main(int argc, char* args[]) {
	cin.sync_with_stdio(0);
	cin.tie(0);
	int nq; cin >> nq;
	while (nq--) {
		llt k, n, a, b;
		cin >> k >> n >> a >> b;
		llt y = a - b, z = k - n * b;
		//cout << y << " " << z << endl;
		llt x = z % y == 0 ? z / y - 1 : z / y;
		x = min(n, x);
		if (x < 0) x = -1;
		cout << x << '\n';
	}
	return 0;
}
