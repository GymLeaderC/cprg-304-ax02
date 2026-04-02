/**
 * MyDLL.java
 * 
 * @description A doubly linked list implementation of the ListADT interface. 
 * Stores elements in a chain of bidirectionally linked nodes, supporting 
 * efficient insertion and removal at any position. Provides indexed access 
 * and iteration over generic elements.
 * 
 * @author Team Sidon - Aaron Reid, Cielo Pacot, Joshua Couto, Ryan Burns
 * Course: CPRG-304 (Object-Oriented Programming 3)
 * Institution: Southern Alberta Institute of Technology 
 * Date: March 28th, 2026
 */

package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

/**
 * MyDLL.java
 * 
 * @description Doubly Linked List implementation of ListADT.
 */
public class MyDLL<E> implements ListADT<E> {

    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    /**
     * Constructs an empty list.
     */
    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Optimized node retrieval
     */
    private MyDLLNode<E> getNode(int index) {
        MyDLLNode<E> current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++)
                current = current.getNext();
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--)
                current = current.getPrev();
        }

        return current;
    }

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null)
            throw new NullPointerException("Element cannot be null.");

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        size++;
        return true;
    }

    @Override
    public boolean add(int index, E toAdd)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toAdd == null)
            throw new NullPointerException("Element cannot be null.");

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if (index == size)
            return add(toAdd);

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (index == 0) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        } else {
            MyDLLNode<E> current = getNode(index);
            MyDLLNode<E> prev = current.getPrev();

            prev.setNext(newNode);
            newNode.setPrev(prev);

            newNode.setNext(current);
            current.setPrev(newNode);
        }

        size++;
        return true;
    }

    @Override
    public boolean addAll(ListADT<? extends E> toAdd)
            throws NullPointerException {

        if (toAdd == null)
            throw new NullPointerException();

        Iterator<? extends E> it = toAdd.iterator();

        while (it.hasNext()) {
            add(it.next());
        }

        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        return getNode(index).getData();
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        MyDLLNode<E> current = getNode(index);

        if (size == 1) {
            head = tail = null;
        } else if (current == head) {
            head = head.getNext();
            head.setPrev(null);
        } else if (current == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }

        size--;
        return current.getData();
    }

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null)
            throw new NullPointerException();

        MyDLLNode<E> current = head;
        int index = 0;

        while (current != null) {
            if (toRemove.equals(current.getData())) {
                return remove(index);
            }
            current = current.getNext();
            index++;
        }

        return null;
    }

    @Override
    public E set(int index, E toChange)
            throws NullPointerException, IndexOutOfBoundsException {

        if (toChange == null)
            throw new NullPointerException();

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        MyDLLNode<E> node = getNode(index);
        E old = node.getData();
        node.setData(toChange);

        return old;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null)
            throw new NullPointerException();

        MyDLLNode<E> current = head;

        while (current != null) {
            if (toFind.equals(current.getData()))
                return true;
            current = current.getNext();
        }

        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        MyDLLNode<E> current = head;
        int i = 0;

        while (current != null) {
            arr[i++] = current.getData();
            current = current.getNext();
        }

        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] holder) throws NullPointerException {

        if (holder == null)
            throw new NullPointerException();

        if (holder.length < size) {
            holder = (E[]) java.lang.reflect.Array
                    .newInstance(holder.getClass().getComponentType(), size);
        }

        MyDLLNode<E> current = head;
        int i = 0;

        while (current != null) {
            holder[i++] = current.getData();
            current = current.getNext();
        }

        return holder;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private MyDLLNode<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
            	if (!hasNext()) {
            		throw new NoSuchElementException();
            	}
                E data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }
}