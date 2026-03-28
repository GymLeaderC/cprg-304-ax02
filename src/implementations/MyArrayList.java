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

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E> {
	private static final int DEFAULT_CAPACITY = 10;
	
	private Object[] array;
	private int size;

	public MyArrayList() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		array = new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		
		if (toAdd == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
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

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		
		if (toAdd == null) {
			throw new NullPointerException();
		} 
		if (size == array.length) {
			array = Arrays.copyOf(array, size * 2);
			return true;
		} 
		
		array[size] = toAdd;
		size++;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		
		if (toAdd == null) {
			throw new NullPointerException();
		}
		
		Iterator<? extends E> it = toAdd.iterator();
		while (it.hasNext()) {
			add(it.next());
		}
		
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	
	public E get(int index) throws IndexOutOfBoundsException {
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		return (E) array[index];
	}

	@Override
	@SuppressWarnings("unchecked")
	public E remove(int index) throws IndexOutOfBoundsException {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		E removed = (E) array[index];
		
		for (int i = index; i < size - 1; i++) {
			array[i] = array[i + 1];
		}
		
		array[size - 1] = null;
		size--;
		return removed;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {

		if (toRemove == null) {
			throw new NullPointerException();
		}
		
		for (int i = 0; i < size; i++) {
			if (toRemove.equals(array[i])) {
				return remove(i);
			}	
		}
		
		return null;	
	}

	@Override
	@SuppressWarnings("unchecked")
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {

		if (toChange == null) {
			throw new NullPointerException();
		}
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		E replaced = (E) array[index];
		array[index] = toChange;
		return replaced;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {

		if (toFind == null) {
			throw new NullPointerException();
		}
		
		for (int i = 0; i < size; i++) {
			if (toFind.equals(array[i])) {
				return true;
			} 
		}
		
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] toHold) throws NullPointerException {

		if (toHold == null) {
			throw new NullPointerException();
		}
		
		if (toHold.length < size) {
			toHold = (E[]) new Object[size];
		}
		
		for (int i = 0; i < size; i++) {
			toHold[i] = (E) array[i];
		}
		
		return toHold;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}

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
				E element = (E) array[cursor];
				cursor++;
				return element;
			}
		};
	}

}
