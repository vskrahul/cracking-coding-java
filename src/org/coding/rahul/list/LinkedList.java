package org.coding.rahul.list;

public class LinkedList<E> {
	
	private Node<E> head;
	
	private Node<E> tail;
	
	public E getFirst() {
		return head.item;
	}
	
	public E getStart() {
		return tail.item;
	}

	public void add(E element) {
		Node<E> node = new Node<E>(element);
		tail.next = node;
		tail = node;
	}

	public Node<E> getHead() {
		return this.head;
	}
	
	private class Node<E> {
		
		E item;
		Node<E> next;
		
		Node(E element) {
			this.item = item;
		}
	}

}