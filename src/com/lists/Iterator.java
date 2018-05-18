package com.lists;

/**
 * Linked List iterator.
 * @param <T>
 * @author mjsch
 */
public class Iterator<T> implements java.util.Iterator
{
	private Link<T> current;
	private Link<T> end;

	Iterator(Link<T> start, Link<T> end)
	{
		current = start;
		this.end = end;
	}

	public boolean hasNext()
	{
		return current != null;
	}

	public T next()
	{
		T daten = current.data;
		current = current.next;

		return daten;
	}

	public void remove()
	{
		throw new UnsupportedOperationException();
	}
}