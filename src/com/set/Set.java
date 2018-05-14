package com.set;

import com.lists.List;

/**@author mjsch
 * Ideen:
 *  1. Da Hashing-Methoden noch nicht bekannt sind -> naive Implementierung durch normale Array- oder LinkedList.
 *  2. Es muss durch ArrayList und LinkedList iteriert werden, um zu checken, ob ein Element enthalten ist.
 *  3.
 */

public class Set<T> implements ISet<T>
{
    String name;
    private List<T> set;

    public Set(String name)
    {
        this.name = name;
        set = new List<T>(this.name);
    }

    @Override
    public boolean isEmpty()
    {
        return set.isEmpty();
    }

    @Override
    public void add(T element)
    {
        if (set.contains(element) == true)
        {
            System.out.println("@add(" + element.toString() + "): The given element already exists in the set.");
            return;
        }
        set.add(element);
    }

    @Override
    public boolean remove(T element)
    {
        if (set.contains(element) == false)
        {
            System.out.println("@remove(" + element.toString() + "): The given element does not exist in the set.");
            return false;
        }
        set.remove(element);
        return true;
    }

    // Nicht HashCode von this, sondern von den einzelnen Elementen.
    public void printHashCode()
    {
        System.out.println("HashCode(" + name + "): " + this.hashCode());
    }
}
