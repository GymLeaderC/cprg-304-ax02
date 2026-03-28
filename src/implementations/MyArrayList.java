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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
