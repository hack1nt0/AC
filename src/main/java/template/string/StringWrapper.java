package template.string;

import com.google.common.base.Objects;

import java.util.ArrayList;

/**
 * @author dy[jealousing@gmail.com] on 17-3-20.
 */
public class StringWrapper {
    private String value;
    private int hash;

    public StringWrapper(String str) {
        this.value = str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringWrapper that = (StringWrapper) o;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    /**
     * BKDR Hash Function (https://www.byvoid.com/zhs/blog/string-hash-compare)
     */
    @Override
    public int hashCode() {
        if (value == null) return 0;
        if (hash == 0 && value.length() > 0) {
            int seed = 131; // 31 131 1313 13131 131313 etc..
            for (int i = 0; i < value.length(); ++i) hash = hash * seed + value.charAt(i);
            hash &= 0x7FFFFFFF;
        }
        if (hash == 0) throw new RuntimeException();
        return hash;
    }
}
