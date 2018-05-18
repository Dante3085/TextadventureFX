package com.lists;

/**
 * Link for Linked List.
 * @param <T>
 * @author mjsch
 */
public class Link<T>
{
    public T data;
    public Link<T> next;

    public Link(T data, Link<T> next)
    {
        this.data = data;
        this.next = next;
    }
}
