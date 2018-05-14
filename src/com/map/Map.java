package com.map;

import com.lists.List;

public class Map<K, V> implements IMap<K, V>
{
    // Pair oder 2 Listen. Mehr Link Objekte oder mehr Pair Objekte. Pair könnte schneller sein, da es nicht 2 Listen gibt.
//    private List<K> keys = new List<K>("Keys");
//    private List<V> values = new List<V>("Values");
    String name;
    List<Pair<K, V>> map;

    public Map(String name)
    {
        map = new List<Pair<K, V>>(name);
    }

    @Override
    public void clear()
    {
        map.flush();
    }

    @Override
    public boolean containsKey(K key)
    {
        for (Pair<K, V> e: map)
            if (e.hasKey(key) == true)
                return true;
        return false;
    }

    @Override
    public boolean containsValue(V value)
    {
        for (Pair<K, V> e: map)
            if (e.hasValue(value))
                return true;
        return false;
    }

    @Override
    public V get(K key)
    {
        if (containsKey(key) == false)
        {
            System.out.println("@get(" + key.toString() + "): The key doesn't exist in the map.");
            return null;
        }

        for (Pair<K, V> e: map)
            if (e.hasKey(key) == true)
                return e.getValue();
        return null; // Kann eigentlich auch nicht passieren.
    }

    @Override
    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    @Override
    public V put(K key, V value)
    {
        // Ersetzen
        if (containsKey(key) == true)
            remove(key);

        map.add(new Pair<>(key, value));
        return null;
    }

    @Override
    public V remove(K key)
    {
        if (containsKey(key) == false)
        {
            System.out.println("Key '" + key.toString() + "' doesn't exist in the map.");
            return null;
        }

        V temp;
        // Value auch löschen.
        for (Pair<K, V> e: map)
        {
            if (e.hasKey(key) == true)
            {
                temp = e.getValue();
                map.remove(e);
                return temp;
            }
        }
        return null; // Eigentlich kann das nie auftreten, da schon bekannt ist, dass der Key enthalten ist und er somit gefunden werden muss.
    }

    @Override
    public int size()
    {
        return map.size();
    }

    public void printMap()
    {
        if (map.size() == 0)
        {
            System.out.println("@printMap(): Map is empty.");
            return;
        }
        for (Pair<K, V> e: map)
            System.out.println(e.toString());
    }
}
