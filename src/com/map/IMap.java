package com.map;

/**
 *
 * @param <K>
 * @param <V>
 * @author mjsch
 */
public interface IMap<K, V>
{
    void clear();

    boolean containsKey(K key);

    boolean containsValue(V value);

    // Set<Map.Entry<K, V>> entrySet()

    //boolean equals(Object o);

    V get(K key);

    int hashCode();

    boolean isEmpty();

    // Set<K>

    V put(K key, V value);

    //void putAll(Map<>)

    V remove(K key);

    int size();

    // Collection<V>
}
