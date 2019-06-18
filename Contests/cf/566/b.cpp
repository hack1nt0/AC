#include <bits/stdc++.h>
using namespace std;

typedef long long llt;
int main() {
	cin.sync_with_stdio(0);
	cin.tie(0);

	int h, w;
	cin >> h >> w;
	vector<string> m(h);
	for (int i = 0; i < h; ++i) cin >> m[i];
	vector<int> ans;
	int tot = 0;
	int di[] = {-1, +1, 0, 0};
	int dj[] = {0, 0, -1, +1};
	for (int i = 0; i < h; ++i)
		for (int j = 0; j < w; ++j) {
			if (m[i][j] == '*') tot++;
			bool ok = m[i][j] == '*';
			int add = 1;
			for (int d = 0; d < 4; ++d) {
				int x = 0;
				int i2 = i, j2 = j;
				while (true) {
					int i3 = i2 + di[d];
					int j3 = j2 + dj[d];
					if (0 <= i3 && i3 < h && 0 <= j3 && j3 < w && m[i3][j3] == '*')
						x++;
					else 
						break;
					i2 = i3, j2 = j3;
				}
				add += x;
				ok &= x > 0;
			}
			if (ok) ans.push_back(add); 
		}
	cout << (ans.size() == 1 && ans[0] == tot ? "YES" : "NO") << endl;
	return 0;
}
