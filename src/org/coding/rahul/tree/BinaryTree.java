package org.coding.rahul.tree;

import java.util.Objects;

public class BinaryTree<V extends Comparable<V>> extends Tree<V> {

	public void insert(V value) {
		Objects.requireNonNull(value, "null is not allowed.");
		
		if(Objects.isNull(root)){
			new Node<V>().value(value);
		} else {
			add(this.root, value);
		}
	}
	
	private boolean delete(V value, Node<V> p) {
		
		if(p == null)
			return false;
		
		if(value.compareTo(p.value) < 0) {
			delete(value, p.left);
		} else {
			if(value.compareTo(p.value) > 0) {
				delete(value, p.right);
			} else {
				/**
				 * This is the node.
				 */
				Node<V> deletingNode = p;
				Node<V> replacedBy = null;
				
				/**
				 * Deleting node has right subtree
				 */
				if(!child(deletingNode)){
					deletingNode.parent = null;
					return true;
				}
				
				if(deletingNode.right == null) {
					replacedBy =  find_left_most_leaf(deletingNode);
					swipe(deletingNode, replacedBy, 1);
				} else {
					if(deletingNode.left == null) {
						replacedBy = deletingNode.right;
						swipe(deletingNode, replacedBy, 2);
					} else {
						if(deletingNode.right.left == null) {
							replacedBy = deletingNode.right;
							swipe(deletingNode, replacedBy, 3);
						} else {
							if(deletingNode.right.left != null) {
								replacedBy = find_left_most_leaf(deletingNode.right);
								swipe(deletingNode, replacedBy, 4);
							}
						}
					}
				}/*end finding of replacedBy node.*/
			}
		}
		
		return false;
	}
	
	private void swipe(Node<V> deletingNode, Node<V> replacedBy, int type) {
		
		switch(type){
		case 1 : 
			while(deletingNode == replacedBy.parent) {
				Node<V> temp = replacedBy.parent;
				replacedBy.parent = temp.parent;
				temp.parent = replacedBy;
				replacedBy.left = temp;
			}
			replacedBy.parent = deletingNode.parent;
			deletingNode.parent.left = replacedBy;
		case 2 :
			replacedBy.parent = deletingNode.parent;
			deletingNode.parent.right = replacedBy;
		case 3 : 
			replacedBy.parent = deletingNode.parent;
			deletingNode.parent.right = replacedBy;
			
		case 4 : 
			if(replacedBy.right != null) {
				Node<V> temp = replacedBy.parent;
				Node<V> child = replacedBy.right;
				child.parent = replacedBy.parent;
				temp.left = child;
				child.left = replacedBy;
				replacedBy.parent = child;
			}
			while(deletingNode == replacedBy.parent) {
				Node<V> temp = replacedBy.parent;
				replacedBy.parent = temp.parent;
				temp.parent = replacedBy;
				replacedBy.left = temp;
			}
			replacedBy.parent = deletingNode.parent;
			deletingNode.parent.right = replacedBy;
		}
	}
	
	private boolean child(Node<V> node) {
		return node.left != null || node.right != null;
	}
	
	private Node<V> find_left_most_leaf(Node<V> node) {
		if(node.left != null)
			return find_left_most_leaf(node.left);
		return node;
	}
	
	public boolean delete(V value) {
		return delete(value, this.root);
	}
	
	public V find(V value) {
		return null;
	}
	
	public V random(V value) {
		return null;
	}
	
	private void add(Node<V> parent, V value) {
		
		if(Objects.isNull(parent))
			return;
		
		if(value.compareTo(parent.value) > 0) {
			if(Objects.isNull(parent.right)){
				Node<V> node = new Node<V>().value(value);
				parent.right = node;
				node.parent = parent;
			} else {
				add(parent.right, value);
			}
		} else {
			if(value.compareTo(parent.value) < 0) {
				if(parent.left == null) {
					Node<V> node = new Node<V>().value(value);
					parent.left = node;
					node.parent = parent;
				} else {
					add(parent.left, value);
				}
			}
		}
	}
	
	public void pre_order(Node<V> node) {
		if(node != null) {
			System.out.print(String.format("[ %s ]--", node.value));
			pre_order(node.left);
			pre_order(node.right);
		}
	}
	
	public int height(Node<V> root) {
		if(Objects.isNull(root) || (Objects.isNull(root.left) && Objects.isNull(root.right))) {
			return 0;
		}
		int hl = 1 + height(root.left);
		int hr = 1 + height(root.right);
		return 1 + hl > hr ? hl : hr;
	}
	
	@SuppressWarnings("unused")
	private Node<V> sibling(Node<V> node) {
		if(Objects.isNull(node) || Objects.isNull(node.parent)) {
			return null;
		}
		if(node.value.compareTo(node.parent.value) < 0)
			return node.parent.right;
		else
			return node.parent.left;
	}
}
