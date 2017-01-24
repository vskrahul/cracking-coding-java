package org.coding.rahul.tree;

public class Tree<V extends Comparable<V>> {

	Tree.Node<V> root;
	
	static final class Node<V> implements Comparable<V> {
		Node<V> parent;
		Node<V> left;
		Node<V> right;
		V value;
		String childType;
		
		Node() {
			
		}
		
		Node<V> value(V v) {
			this.value = v;
			return this;
		}
		
		Node<V> right(Node<V> node) {
			this.right = node;
			return this;
		}

		Node<V> left(Node<V> node) {
			this.right = node;
			return this;
		}
		
		@Override
		public int compareTo(V v) {
			return 0;
		}
	}
	
	public Node<V> getRoot() {
		return this.root;
	}
	
	public void root(Node<V> root){
		this.root = root;
	}
}