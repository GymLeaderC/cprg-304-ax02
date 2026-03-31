/**
 * MyStack.java
 * 
 * @description A stack implementation of the StackADT interface, backed by a 
 * MyArrayList. Follows Last-In-First-Out (LIFO) ordering, where elements are 
 * pushed onto and popped from the top of the stack.
 * 
 * @author Team Sidon - Aaron Reid, Cielo Pacot, Joshua Couto, Ryan Burns
 * Course: CPRG-304 (Object-Oriented Programming 3)
 * Institution: Southern Alberta Institute of Technology 
 * Date: March 28th, 2026
 */

package implementations;

import utilities.StackADT;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import utilities.Iterator;

public class MyStack<E> implements StackADT<E> {
	private MyArrayList<E> list;

	/**
	 * Constructs an empty MyStack using MyArrayList implementation as the
	 * underlying data structure.
	 */
	public MyStack() {
		this.list = new MyArrayList<>();
	}
	
	/**
	 * Pushes an item onto the top of this stack.
	 * 
	 * @param toAdd item to be pushed onto the top of the stack.
	 * @throws NullPointerException when attempting to add a null element to the
	 * 		   stack.
	 */
	@Override
	public void push(E toAdd) throws NullPointerException {
		if (toAdd != null) {
			list.add(toAdd);
		} else {
			throw new NullPointerException("Element cannot be null.");
		}
	}

	/**
	 * Removes the object at the top of this stack and returns that object as the
	 * value of this function.
	 * 
	 * @return the item popped off the top of the stack.
	 * @throws EmptyStackException if there are not items in the stack.
	 */
	@Override
	public E pop() throws EmptyStackException {
		int size = list.size();
		if (size > 0) {
			return list.remove(size - 1);
		} else {
			throw new EmptyStackException();
		}
	}

	/**
	 * Looks at the object at the top of this stack without removing it from the
	 * stack.
	 * 
	 * @return the object at the top of this stack.
	 * @throws EmptyStackException
	 */
	@Override
	public E peek() throws EmptyStackException {
		int size = list.size();
		if (size > 0) {
			return list.get(size - 1);
		} else {
			throw new EmptyStackException();
		}
	}

	/**
	 * Clears all the items from this Stack. This method returns, unless there is an
	 * Exception (Runtime) thrown.
	 */
	@Override
	public void clear() {
		list.clear();
	}

	/**
	 * Returns <code>true</code> if this Stack contains no items.
	 * 
	 * @return <code>true</code> if this Stack contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence. Obeys the general contract of the Collection.toArray method.
	 * 
	 * @return an array containing all of the elements in this list in proper
	 *         sequence.
	 */
	@Override
	public Object[] toArray() {
		int size = list.size();
		Object[] newArray = new Object[size];
		
		for (int i = 0; i < size; i++) {
			newArray[i] = list.get(size - 1 - i);
		}
		
		return newArray;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array. Obeys the general contract of the Collection.toArray(Object[]) method.
	 * 
	 * @param toHold the array into which the elements of this stack are to be
	 *               stored, if it is big enough; otherwise, a new array of the same
	 *               runtime type is allocated for this purpose.
	 * @return an array containing the elements of this stack.
	 * @throws NullPointerException if the specified array is null.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray(E[] holder) throws NullPointerException {
		// Throw error if holder is empty
		if (holder == null) {
			throw new NullPointerException("Array cannot be null.");
		}
		
		// If holder capacity is too small, copy to a larger array
		int size = list.size();
		if (holder.length < size) {
			holder = (E[]) Arrays.copyOf(holder, size, holder.getClass());
		}
		
		// Add items to holder
		for (int i = 0; i < size; i++) {
			holder[i] = list.get(size - 1 - i);
		}
		
		return holder;
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element e such
	 * that (o==null ? e==null : o.equals(e)).
	 * 
	 * @param toFind element whose presence in this list is to be tested.
	 * @return true if this list contains the specified element.
	 * @throws NullPointerException if the specified element is null and this list
	 *                              does not support null elements.
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		
		return list.contains(toFind);
	}

	/**
	 * Returns the 1-based position where an object is on this stack. If the object
	 * o occurs as an item in this stack, this method returns the distance from the
	 * top of the stack of the occurrence nearest the top of the stack; the topmost
	 * item on the stack is considered to be at distance 1. The equals method is
	 * used to compare o to the items in this stack.
	 * 
	 * @param toFind the desired object.
	 * @return the 1-based position from the top of the stack where the object is
	 *         located; the return value -1 indicates that the object is not on the
	 *         stack.
	 */
	@Override
	public int search(E toFind) {
		int size = list.size();
		
		for (int i = 0; i < size; i++) {
			if (toFind.equals(list.get(i))) {
				return size - i;
			} 
		}
		
		return -1;
	}

	/**
	 * Returns an iterator over the elements in this stack in proper sequence.
	 * 
	 * @return an iterator over the elements in this stack in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int cursor = list.size() - 1;
			
			@Override
			public boolean hasNext() {
				return cursor >= 0;
			}
			
			@Override
			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				E element = list.get(cursor);
				cursor--;
				return element;
			}
		};
	}

	/**
	 * Used to compare two Stack ADT's. To be equal two stacks must contain equal
	 * items appearing in the same order.
	 * 
	 * @param that the Stack ADT to be compared to this stack.
	 * @return <code>true</code> if the stacks are equal.
	 */
	@Override
	public boolean equals(StackADT<E> that) {
		// Create iterators for both stacks
		Iterator<E> listIt = this.iterator();
		Iterator<E> thatIt = that.iterator();
		
		// Compare values (return false if non-matching values found)
		while (listIt.hasNext() && thatIt.hasNext()) {
			if (!listIt.next().equals(thatIt.next())) {
				return false;
			}
		}
		
		// Check that all values of both lists were compared
		// (if so, return true; if not, return false)
		return (!listIt.hasNext() && !thatIt.hasNext());
	}

	/**
	 * Returns the depth of the current stack as an integer value.
	 * 
	 * @return the current size to the stack as an integer.
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Returns true if the number of items in the stack equals the length.  
	 * This operation is only implement when a fixed size stack is required.
	 * @return <code>true</code> if stack is at capacity.
	 */
	@Override
	public boolean stackOverflow() {
		// Stack does not have a fixed size, so always return false
		return false;
	}
}
