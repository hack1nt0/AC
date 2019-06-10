#include <bits/stdc++.h>

const int mod = (int) 1e9 + 7;
typedef long long T;

T pow2(T pow) {
	T res = 1;
	T acc = 2;
	while (pow > 0) {
		if ((pow & 1) != 0) res = res * acc % mod;
		acc = acc * acc % mod;
		pow >>= 1;
	}
	return res;
}

typedef std::vector<int> vi;
const int N = (int) 1e5 + 4;
int xs[N], pxor[N], pnz[N];
std::map<int, vi> pos;
T dp[2][2];
int n;

T go(const vi& nz, int d) {
	for (auto x : nz) std::cerr << x << ","; std::cerr << std::endl;
	int cur = 0;
	dp[cur][0] = 0;
	dp[cur][1] = 0;
	for (int i = 0; i < nz.size(); ++i) {
		int nxt = cur ^ 1;
		if (i % 2 == 0) {
			dp[nxt][1] = (dp[cur][1] + dp[cur][0] * nz[i] % mod + nz[i]) % mod;
			dp[nxt][0] = dp[cur][0];
		} else {
			dp[nxt][0] = (dp[cur][0] + dp[cur][1] * nz[i] % mod) % mod;
			dp[nxt][1] = dp[cur][1];
		}
		cur = nxt;
	}
	return dp[cur][d];
}
vi compress(int x) {
	vi& px = pos[x];
	vi nz;
	int i = 0, k = 0;
	while (i < px.size()) {
		if (k == 0) {
			int j = i + 1;
			while (j < px.size() && pnz[px[j]] - pnz[px[i]] == 0)
				++j;
			nz.push_back(j - i);
			i = j;
		} else {
			nz.push_back(pnz[px[i]] - pnz[px[i - 1]]);
		}
		k ^= 1;
	}
	if (pxor[n] != 0)
		nz[nz.size() - 1] = 1;
	else
		nz.push_back(1);
	return nz;
}
int main() {
	std::cin.sync_with_stdio(0), std::cin.tie(0);
	std::cin >> n;
	for (int i = 0; i < n; ++i) std::cin >> xs[i];
	std::fill(pxor, pxor + n + 1, 0);
	std::fill(pnz, pnz + n + 1, 0);
	pos.clear();
	for (int i = 0; i < n; ++i) {
		pxor[i + 1] = pxor[i] ^ xs[i];
		if (pxor[i + 1] == 0) pnz[i + 1]++;
		pos[pxor[i + 1]].push_back(i + 1);
	}
	for (int i = 1; i <= n; ++i) pnz[i] += pnz[i - 1];
	for (int i = 1; i <= n; ++i) std::cerr << pxor[i] << std::endl;
	T ans = 0;
	if (pxor[n] != 0) {
		ans += go(compress(pxor[n]), 1);
	} else {
		for (auto& xp : pos) {
			int x = xp.first;
			if (x == 0)
				ans += pow2(pos[x].size() - 1);
			else
				ans += go(compress(x), 0);
			ans %= mod;
			std::cerr << x << " = " << ans << std::endl;
		}
	}
	std::cout << ans << std::endl;
	return 0;
}
