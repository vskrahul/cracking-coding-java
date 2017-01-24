package org.coding.rahul.tree;

public class Tree<V extends Comparable<V>> {

	Tree.Node<V> root;
	
	public static final String RIGHT_CHILD = "RIGHT";
	
	public static final String LEFT_CHILD = "LEFT";
	
	static final class Node<V> implements Comparable<V>{
		Node<V> parent;
		Node<V> left;
		Node<V> right;
		V value;
		String childType;
		
		Node(V value) {
			this.value = value;
		}
		
		void setLeft(Node<V> node) {
			this.left = node;
		}
		
		void setRight(Node<V> node) {
			this.right = node;
		}

		@Override
		public int compareTo(V v) {
			return 0;
		}
	}
	
	public Node<V> getRoot() {
		return this.root;
	}
	
	public void setRoot(Node<V> root){
		this.root = root;
	}
}