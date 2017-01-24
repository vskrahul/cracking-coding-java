package org.coding.rahul.tree;

import org.coding.rahul.tree.Tree.Node;

public class Main {

	public static void main(String[] args) {
		BinaryTree<Integer> t = new BinaryTree<Integer>();
		
		Node<Integer> root = new Node<Integer>(100);
		
		t.setRoot(root);
		
		t.insert(50);
		t.insert(30);
		t.insert(20);
		t.insert(10);
		t.insert(25);
		t.insert(40);
		
		t.insert(35);
		t.insert(45);
		
		t.insert(80);
		t.insert(70);
		
		t.insert(60);
		t.insert(65);
		
		t.insert(90);
		t.insert(85);
		t.insert(81);
		t.insert(88);
		t.insert(95);
		
		t.insert(200);
		
		t.printPreOrder(root);
		t.delete(80);
		System.out.println("**************************");
		t.printPreOrder(root);
		
	}
}