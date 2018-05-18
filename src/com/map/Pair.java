package com.map;

/**
 *
 * @param <K>
 * @param <V>
 * @author mjsch
 */
public class Pair<K, V>
{
    private K key;
    private V value;

    public Pair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public boolean hasKey(K key)
    {
        return key.equals(this.key);
    }

    public boolean hasValue(V value)
    {
        return value.equals(this.value);
    }

    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "Key: " + key + ", Value: " + value;
    }
}
