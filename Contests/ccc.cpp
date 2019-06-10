#include <bits/stdc++.h>
using namespace std;


int main() {
	int no, nz;
	cin >> nz >> no;
	if (nz > no + 1 || no > (nz + 1) * 2) {
		cout << -1 << endl;
		return 0;
	}
	int n = nz + no;
	vector<int> xs(n);
	if (nz < no) {
		for (int i = 0, p = 0; i < nz * 2 + 1; ++i) {
			if (i % 2 == 1) {
				xs[p++] = 0;
			} else {
				int n1 = 1 + (i / 2 + 1 + nz + 1 <= no ? 1 : 0);
				for (int j = 0; j < n1; ++j)
					xs[p++] = 1;
			}
		}
	} else {
		for (int i = 0; i < n; ++i)
			xs[i] = i % 2 == 0 ? 0 : 1;
	}
	for (int i = 0; i < n; ++i)
		cout << xs[i];
	cout << endl;
	return 0;
}
