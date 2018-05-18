package com.set;

/**
 *
 * @param <T>
 * @author mjsch
 */
public interface ISet<T>
{
    public boolean isEmpty();

    //public Iterator<T> iterator();

    public void add(T element);

    public boolean remove(T element);
}
