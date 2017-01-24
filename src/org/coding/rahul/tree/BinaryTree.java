package org.coding.rahul.tree;

import org.coding.rahul.tree.exception.BinaryException;

public class BinaryTree<V extends Comparable<V>> extends Tree<V> {

	public boolean insert(V value) {
		if(value == null)
			return false;
		
		if(this.root == null){
			this.root = new Node<V>(value);
			return true;
		}
		
		Node<V> parent = null;
		
		try{
			parent = findParent(this.root, value);
		} catch(BinaryException e) {
			return false;
		}
		
		Node<V> node = new Node<V>(value);
		
		if(value.compareTo(parent.value) > 0) {
			parent.right = node;
			node.childType = Tree.RIGHT_CHILD;
		} else {
			if(value.compareTo(parent.value) < 0) {
				parent.left = node;
				node.childType = Tree.LEFT_CHILD;
			}
		}
		
		node.parent = parent;
		
		return true;
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
				if(!hasChild(deletingNode)){
					deletingNode.parent = null;
					return true;
				}
				
				if(deletingNode.right == null) {
					replacedBy =  findLeftMostLeaf(deletingNode);
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
								replacedBy = findLeftMostLeaf(deletingNode.right);
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
	
	private boolean hasChild(Node<V> node) {
		return node.left != null || node.right != null;
	}
	
	private Node<V> findLeftMostLeaf(Node<V> node) {
		if(node.left != null)
			return findLeftMostLeaf(node.left);
		return node;
	}
	
	public boolean delete(V value) {
		return delete(value, this.root);
	}
	
	public V find(V value) {
		return null;
	}
	
	public V getRandom(V value) {
		return null;
	}
	
	private Node<V> findParent(Node<V> parent, V value) {
		
		if(parent == null)
			return null;
		
		if(value.compareTo(parent.value) > 0) {
			if(parent.right == null){
				return parent;
			} else {
				parent = parent.right;
			}
		} else {
			if(value.compareTo(parent.value) < 0) {
				if(parent.value == value) {
					/**
					 * Node is already exists with given @param : value
					 */
					throw new BinaryException("Node of this value is already exists in this tree");
				}
				if(parent.left == null) {
					return parent;
				} else {
					parent = parent.left;
				}
			}
		}
		return findParent(parent, value);
	}
	
	public void printPreOrder(Node<V> node) {
		if(node != null) {
			System.out.println(node.value);
			printPreOrder(node.left);
			printPreOrder(node.right);
		}
	}
	
	private Node<V> getSibling(Node<V> node) {
		if(node == null) {
			return null;
		}
		if(node.parent == null) {
			/**
			 * this is a root node
			 */
			return null;
		}
		if(node.childType == Tree.LEFT_CHILD)
			return node.parent.right;
		if(node.childType == Tree.RIGHT_CHILD)
			return node.parent.left;
		return null;
	}
}
