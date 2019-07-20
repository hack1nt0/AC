
template <typename T>
struct rational {
	typedef rational<T> self_t;
	typedef const self_t& cref_t;
	typedef self_t& ref_t;
	T x, y;
	rational(T _x, T _y) : x(_x), y(_y) {
		if (x > 0 && y == 0) {
			x = +1;
		} else if (x < 0 && y == 0) {
			x = -1;
		} else if (x == 0 && y == 0) {
		} else {
			int sign = (x < 0 ? -1 : +1) * (y < 0 ? -1 : +1);
			x = abs(x), y = abs(y);
			T gcd = __builtin_gcd(x, y);
			x /= gcd, y /= gcd;
			if (sign < 0) x = -x;
		}
	}

	bool isposinf() { return x > 0 && y == 0; }
	bool isneginf() { return x < 0 && y == 0; }
	bool isinf() { return x != 0 && y == 0; }
	bool isnan() { return x == 0 && y == 0; }
	bool isnormal() { return y != 0; }

	explicit rational(T _x) : x(_x), y(1) {}
	self_t& operator=(T _x) { x = _x, y = 1; return *this; }
	self_t operator+(const self_t& o) { 
		assert(isnormal() && o.isnormal());
		T lcm = y * o.y / __builtin_gcd(y, o.y);
		return {lcm / o.y * y + lcm / y * o.y, lcm};
	}
	int compare(cref_t o) {
		assert(!isnan() && !o.isnan());
		if (isposinf() && isposinf()) return 0;
		if (isposinf() && isneginf()) return +1;
		if (isneginf() && isposinf()) return -1;
		if (isneginf() && isneginf()) return 0;
		return x * o.y < o.x * y; 
	}
	bool operator<(cref_t o) { return this->compare(o) < 0; }
	friend ostream& operator<<(ostream& os, cref_t o) { 
		if (this->isposinf) os << "+oo";
		if (this->isneginf) os << "-oo";
		if (this->isnan()) os << "nan";
		os << o.x << "/" << o.y; 
		return os; 
	}
};
