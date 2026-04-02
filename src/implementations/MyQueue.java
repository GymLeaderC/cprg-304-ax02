/**
 * MyQueue.java
 * 
 * @description A queue implementation of the QueueADT interface, backed by 
 * a MyDLL. Follows First-In-First-Out (FIFO) ordering, where elements are 
 * enqueued at the tail and dequeued from the head of the queue.
 * 
 * @author Team Sidon - Aaron Reid, Cielo Pacot, Joshua Couto, Ryan Burns
 * Course: CPRG-304 (Object-Oriented Programming 3)
 * Institution: Southern Alberta Institute of Technology 
 * Date: March 28th, 2026
 */

package implementations;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;

public class MyQueue<E> implements QueueADT<E>{
	private MyDLL<E> queue;
	
	public MyQueue() {
		queue = new MyDLL<>();
	}
	
	/**
	 * Enqueue will place the added item at the last position in the queue. This
	 * method will not allow <code>null</code> values to be added to the Queue.
	 * 
	 * @param toAdd the item to be added to the Queue.
	 * @throws NullPointerException raised when a <code>null</code> object is placed
	 *                              in the Queue.
	 */
	
	@Override
	public void enqueue(E toAdd) throws NullPointerException {
		// TODO Auto-generated method stub
		queue.add(toAdd);
		
	}
	
	/**
	 * Dequeue will remove the first item that was placed in the Queue.
	 * 
	 * @return the first item in the Queue.
	 * @throws EmptyQueueException raised when the queue's length is zero (0).
	 */
	
	@Override
	public E dequeue() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if (queue.isEmpty()) {
			throw new EmptyQueueException();
		}
		return queue.remove(0);
	}
	
	/**
	 * Peek provides a reference to the first item in the queue without removing
	 * from the queue.
	 * 
	 * @return the first item in the queue.
	 * @throws EmptyQueueException raised when the queue's length is zero (0).
	 */
	@Override
	public E peek() throws EmptyQueueException {
		// TODO Auto-generated method stub
		if (queue.isEmpty()) {
			throw new EmptyQueueException();
		}
		return queue.get(0);
	}
	
	/**
	 * dequeueAll removes all items in the queue.
	 */
	@Override
	public void dequeueAll() {
		// TODO Auto-generated method stub
		queue.clear();
	}

	/**
	 * Returns <code>true</code> when the queue contains no items.
	 * 
	 * @return <code>true</code> when queue length is zero (0).
	 */
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}
	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element e
	 * such that (o==null ? e==null : o.equals(e)).
	 * 
	 * @param toFind
	 *            element whose presence in this list is to be tested.
	 * @return true if this list contains the specified element.
	 * @throws NullPointerException
	 *             if the specified element is null and this list does not
	 *             support null elements.
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		return queue.contains(toFind);
	}

	/**
	 * Returns the 1-based position where an object is on this queue. If the
	 * object o occurs as an item in this queue, this method returns the
	 * distance from the front of the queue of the occurrence nearest the front of
	 * the queue; the first item on the stack is considered to be at distance
	 * 1. The equals method is used to compare o to the items in this queue.
	 * 
	 * @param toFind
	 *            the desired object.
	 * @return the 1-based position from the top of the queue where the object
	 *         is located; the return value -1 indicates that the object is not
	 *         on the queue.
	 */
	
	@Override
	public int search(E toFind) {
		if (toFind == null) {
			throw new NullPointerException();
		}
		
		Iterator<E> i = queue.iterator();
		int position =1;
		
		while(i.hasNext()) {
			if (toFind.equals(i.next())) {
				return position;
			}
			position++;
		}
		return -1;
	}
	/**
	 * Returns an iterator over the elements in this queue in proper sequence.
	 * 
	 * @return an iterator over the elements in this queue in proper sequence.
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return queue.iterator();
	}

	/**
	 * Used to compare two Queue ADT's. To be equal two queues must contain equal
	 * items appearing in the same order.
	 * 
	 * @param that the Queue ADT to be compared to this queue.
	 * @return <code>true</code> if the queues are equal.
	 */
	
	
	@Override
	public boolean equals(QueueADT<E> that) {
		// TODO Auto-generated method stub
		if (that == null) {
			return false;
		}
		if (this.size() != that.size()) {
			return false;
		}
		
		Iterator <E> thisIt = this.iterator();
		Iterator <E> thatIt = that.iterator();
		
		while(thisIt.hasNext() && thatIt.hasNext()) {
			Object a = thisIt.next();
			Object b = thatIt.next();
			
			if (!a.equals(b)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence. Obeys the general contract of the Collection.toArray method.
	 * 
	 * @return an array containing all of the elements in this list in proper
	 *         sequence.
	 */
	
	@Override
	public Object [] toArray() {
		// TODO Auto-generated method stub
		return queue.toArray();
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence; the runtime type of the returned array is that of the specified
	 * array. Obeys the general contract of the Collection.toArray(Object[]) method.
	 * 
	 * @param toHold the array into which the elements of this queue are to be
	 *               stored, if it is big enough; otherwise, a new array of the same
	 *               runtime type is allocated for this purpose.
	 * @return an array containing the elements of this queue.
	 * @throws NullPointerException if the specified array is null.
	 */
	
	@Override
	public E[] toArray(E[] holder) throws NullPointerException {
		// TODO Auto-generated method stub
		return queue.toArray(holder);
	}

	/**
	 * Returns true if the number of items in the queue equals the
	 * length. This operation is only implement when a fixed length queue is
	 * required.
	 * 
	 * @return <code>true</code> if queue is at capacity.
	 */
	
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Returns the length of the current queue as an integer value.
	 * 
	 * @return the current size to the queue as an integer.
	 */
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return queue.size();
	}

}
