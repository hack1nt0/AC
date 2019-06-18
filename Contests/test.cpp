#include "ranlib.h"
#include "matrix.h"

int main(int argc, char* args[]) {
	cout.precision(3);
	cout << fixed << endl;
	int n = argc <= 1 ? 3 : atoi(args[1]);
	int m = argc <= 2 ? 3 : atoi(args[2]);
	matrix<float> a(n, n, 0);
	for (int i = 0; i < n; ++i) a[i][i] = 1;
	matrix<float> b = rfloatmat(n, n, 0, 10);
	cout << (a.pow(10) == a) << endl << "----------" << endl;
	return 0;
}


