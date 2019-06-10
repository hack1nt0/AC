#include <bits/stdc++.h>
using namespace std;

typedef long long llt;

int main() {
	llt n; cin >> n;
	vector<int> coef;
	for (llt nn = n; nn > 0;) {
		coef.push_back(nn % 3);
		nn = (nn - nn % 3) / 3;
	}
	llt i = 0;
	while (i < coef.size() && !coef[i]) ++i;
	llt mark = 1;
	for (int j = 0; j < i + 1; ++j) mark *= 3;
	cout << (n + mark - 1) / mark << endl;
	return 0;
}
	
