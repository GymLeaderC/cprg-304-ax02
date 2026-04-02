/**
 * MyDLLNode.java
 * 
 * @description A node class used internally by MyDLL. Each node 
 * stores a single generic element along with references to both 
 * the previous and next nodes in the doubly linked list.
 * 
 * @author Team Sidon - Aaron Reid, Cielo Pacot, Joshua Couto, Ryan Burns
 * Course: CPRG-304 (Object-Oriented Programming 3)
 * Institution: Southern Alberta Institute of Technology 
 * Date: March 28th, 2026
 */

package implementations;

/**
 * MyDLLNode.java
 * 
 * @description Node class for a doubly linked list. Stores data and
 * references to both next and previous nodes.
 */
public class MyDLLNode<E> {

    private E data;
    private MyDLLNode<E> next;
    private MyDLLNode<E> prev;

    /**
     * Constructor to initialize node with data.
     */
    public MyDLLNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    // ---------- GETTERS ----------
    public E getData() {
        return data;
    }

    public MyDLLNode<E> getNext() {
        return next;
    }

    public MyDLLNode<E> getPrev() {
        return prev;
    }

    // ---------- SETTERS ----------
    public void setData(E data) {
        this.data = data;
    }

    public void setNext(MyDLLNode<E> next) {
        this.next = next;
    }

    public void setPrev(MyDLLNode<E> prev) {
        this.prev = prev;
    }
}