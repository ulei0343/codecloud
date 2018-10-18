package generics;

/**
 * @author ulei
 * @date 2018/10/18
 */
public interface Pair<K, V> {

    void put(K k, V v);

    K getKey();

    V getValue();
}
