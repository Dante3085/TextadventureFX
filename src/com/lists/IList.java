package com.lists;

/**
 * Describes features that a List should have.
 * @param <T>
 * @author mjsch
 */
public interface IList<T> {
    /**
     * Returns the number of elements in the list
     * @return number of elements in list
     */
    int size();

    /**
     * Returns true if this list contains no elements.
     * @return true if list is empty
     */
    boolean isEmpty();

    /**
     * Adds the given element to the end of the list.
     * @param data element to add
     */
    void add( T data );

    /**
     * Inserts the given element at the given index; does nothing for invalid indices!
     * @param index where to add
     * @param data element to add
     */
    void insert( int index, T data );

    /**
     * Removes the element at the given position.
     * @param index
     */
    void remove( int index );

    /**
     * Removes the given element from the list.
     * @param data
     */
    void remove( T data );

    /**
     * Returns the element at the given index, or null for invalid indices.
     * @param index index of the element to retrieve
     * @return retrieved element
     */
    T get( int index );

    /**
     * Check if list contains the given element.
     * @param data element to check
     * @return true if list contains element
     */
    boolean contains( T data );

    /**
     * Splits the list
     * This list contains the first 'n' elements, the returned (new) list the remaining elements.
     *
     * @param n number of elements to retain in this list
     * @return new list with remaining size() - n elements
     */
    IList<T> split( int n );
}
