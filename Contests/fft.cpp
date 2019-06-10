#include <bits/stdc++.h>
using namespace std;

typedef double T;
typedef complex<T> C;
typedef vector<C> P;
const T pi = acos(-1);

P ext(vector<T>& p, int n) {
	P e(n, 0);
	for (int i = 0; i < p.size(); ++i) e[i] = p[i];
	return e;
}
int reverse(int x, int nb) {
	int y = 0;
	for (int b = 0; b < nb; ++b) if ((x >> b & 1) != 0) {
		y |= 1 << (nb - 1 - b);
	}
	return y;
}
void fft(int l, int n, P& p, T d, P& y) {
	if (n == 1) {
		y[l] = p[l];
		return;
	}
	int h = n / 2;
	fft(l, h, p, d, y);
	fft(l + h, h, p, d, y);
	for (int i = 0; i < h; ++i) {
		T t = i * 2 * pi / n * d;
		C wi(cos(t), sin(t));
		C a = y[l + i];
		C b = y[l + i + h];
		y[l + i] = a + wi * b;
		y[l + i + h] = a - wi * b;
	}
}

P f(const P& p, T d) {
	int n = p.size();
	P pp(n);
	int nb = 0;
	while ((1 << nb) < n) ++nb;
	for (int i = 0; i < n; ++i) {
		pp[reverse(i, nb)] = p[i];
	}
	P y(n);
	fft(0, n, pp, d, y);
	return y;
}
vector<T> operator*(vector<T>& x, vector<T>& y) {
	int size = x.size() + y.size() - 1;
	int n = 1;
	while (n < size) n <<= 1;
	P fx = f(ext(x, n), +1);
	P fy = f(ext(y, n), +1);
	
	P fz(n);
	for (int i = 0; i < n; ++i) fz[i] = fx[i] * fy[i];
	P z = f(fz, -1);
	
	vector<T> zz(size);
	for (int i = 0; i < size; ++i) zz[i] = round(z[i].real() / n);
	return zz;
}

vector<T> random(int n) {
	vector<T> r(n);
	for (int i = 0; i < n; ++i) r[i] = rand() % 10;
	return r;
}
int main() {
	srand(time(0));
	int n = (int) 1e6;
	/*
	vector<T> x = random(n);
	vector<T> y = random(n);
	*/
	vector<T> x = {1, 2};
	vector<T> y = {1, 2};
	vector<T> z = x * y;
	for (int i = 0; i < z.size(); ++i) cout << z[i] << " ";
	cout << endl;
	return 0;
}
