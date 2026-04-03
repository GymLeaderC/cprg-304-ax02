/**
 * MyArrayList.java
 * 
 * @description A dynamic array-based implementation of the ListADT interface. 
 * Stores elements in a resizable array, automatically expanding capacity as 
 * needed. Provides indexed access, insertion, removal, and iteration over generic 
 * elements.
 * 
 * @author Team Sidon - Aaron Reid, Cielo Pacot, Joshua Couto, Ryan Burns
 * Course: CPRG-304 (Object-Oriented Programming 3)
 * Institution: Southern Alberta Institute of Technology 
 * Date: March 28th, 2026
 */

package implementations;
import java.util.Arrays;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E> {
	private static final int DEFAULT_CAPACITY = 10; // Initial array capacity
	
	private Object[] array;
	private int size;

	/**
	 * Constructs an empty MyArrayList with a default capacity of 10.
	 */
	public MyArrayList() {
		this.array = new Object[DEFAULT_CAPACITY]; 
		this.size = 0;
	}

	/**
	 * Returns the number of elements currently in this list.
	 *
	 * @return the current element count.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Removes all elements from this list and resets the internal array
	 * to its default capacity.
	 */
	@Override
	public void clear() {
		array = new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	/**
	 * Inserts the specified element at the specified position, shifting
	 * subsequent elements right. Resizes the internal array if necessary.
	 *
	 * @param index the index at which to insert the element.
	 * @param toAdd the element to be inserted.
	 * @return <code>true</code> if the element was added successfully.
	 * @throws NullPointerException      if the specified element is <code>null</code>.
	 * @throws IndexOutOfBoundsException if (<code>index < 0 || index > size()</code>).
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		
		if (toAdd == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		if (size == array.length) {
			array = Arrays.copyOf(array, size * 2);
		}
		
		for (int i = size; i > index; i--) {
			array[i] = array[i - 1];
		}
			
		array[index] = toAdd;
		size++;
		return true;
	}
	
	/**
	 * Appends the specified element to the end of this list,
	 * resizing the internal array if necessary.
	 *
	 * @param toAdd the element to be appended.
	 * @return <code>true</code> if the element was appended successfully.
	 * @throws NullPointerException if the specified element is <code>null</code>.
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException {
		
		if (toAdd == null) {
			throw new NullPointerException("Element cannot be null.");
		} 
		if (size == array.length) {
			array = Arrays.copyOf(array, size * 2);
		} 
		
		array[size] = toAdd;
		size++;
		return true;
	}

	/**
	 * Appends all elements from the specified list to the end of this list,
	 * in the order returned by the specified list's iterator.
	 *
	 * @param toAdd the list of elements to be appended.
	 * @return <code>true</code> if the operation was successful.
	 * @throws NullPointerException if the specified list is <code>null</code>.
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		
		if (toAdd == null) {
			throw new NullPointerException("List cannot be null.");
		}
		
		Iterator<? extends E> it = toAdd.iterator();
		while (it.hasNext()) {
			add(it.next());
		}
		
		return true;
	}
	
	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index the index of the element to return.
	 * @return the element at the specified position.
	 * @throws IndexOutOfBoundsException if (<code>index < 0 || index >= size()</code>).
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E get(int index) throws IndexOutOfBoundsException {
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		
		return (E) array[index];
	}
	
	/**
	 * Removes and returns the element at the specified position,
	 * shifting any subsequent elements to the left.
	 *
	 * @param index the index of the element to remove.
	 * @return the removed element.
	 * @throws IndexOutOfBoundsException if (<code>index < 0 || index >= size()</code>).
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E remove(int index) throws IndexOutOfBoundsException {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		
		E removed = (E) array[index];
		
		for (int i = index; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		
		array[size - 1] = null;
		size--;
		return removed;
	}
	
	/**
	 * Removes and returns the first occurrence of the specified element,
	 * or returns <code>null</code> if not found.
	 *
	 * @param toRemove the element to be removed.
	 * @return the removed element, or <code>null</code> if not found.
	 * @throws NullPointerException if the specified element is <code>null</code>.
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException {

		if (toRemove == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		
		for (int i = 0; i < size; i++) {
			if (toRemove.equals(array[i])) {
				return remove(i);
			}	
		}
		
		return null;	
	}
	
	/**
	 * Replaces the element at the specified position with the specified
	 * element and returns the element previously at that position.
	 *
	 * @param index    the index of the element to replace.
	 * @param toChange the element to store at the specified position.
	 * @return the element previously at the specified position.
	 * @throws NullPointerException      if the specified element is <code>null</code>.
	 * @throws IndexOutOfBoundsException if (<code>index < 0 || index >= size()</code>).
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {

		if (toChange == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		
		E replaced = (E) array[index];
		array[index] = toChange;
		return replaced;
	}
	
	/**
	 * Returns <code>true</code> if this list contains no elements.
	 *
	 * @return <code>true</code> if this list is empty.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns <code>true</code> if this list contains the specified element,
	 * using equals() for comparison.
	 *
	 * @param toFind the element to search for.
	 * @return <code>true</code> if the element is found.
	 * @throws NullPointerException if the specified element is <code>null</code>.
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {

		if (toFind == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		
		for (int i = 0; i < size; i++) {
			if (toFind.equals(array[i])) {
				return true;
			} 
		}
		
		return false;
	}
	
	/**
	 * Returns an array containing all elements in this list. If the provided
	 * array is too small, a new array of the same type is allocated instead.
	 *
	 * @param toHold the array to store elements in, if large enough.
	 * @return an array containing all elements in this list.
	 * @throws NullPointerException if the specified array is <code>null</code>.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] toHold) throws NullPointerException {

		if (toHold == null) {
			throw new NullPointerException("Array cannot be null.");
		}
		
		if (toHold.length < size) {
			toHold = (E[]) Arrays.copyOf(array, size, toHold.getClass());
		}
		
		for (int i = 0; i < size; i++) {
			toHold[i] = (E) array[i];
		}
		
		return toHold;
	}
	
	/**
	 * Returns an Object array containing all elements in this list
	 * in proper sequence.
	 *
	 * @return an Object array containing all elements in this list.
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}
	
	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * Note: returns {@code utilities.Iterator<E>}, not {@code java.util.Iterator}.
	 *
	 * @return an iterator over the elements in this list.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {

		return new Iterator<E>() {
			private int cursor = 0;
			
			@Override
			public boolean hasNext() {
				return cursor < size;
			}
			
			@Override
			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				
				E element = (E) array[cursor];
				cursor++;
				return element;
			}
		};
	}
}
