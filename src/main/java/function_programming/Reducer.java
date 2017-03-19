package function_programming;

/**
 * @author dy[jealousing@gmail.com] on 17-3-20.
 */
public interface Reducer<T, U> extends Lambda3<T, U, T> {
    public T apply(T acc, U value);
}
