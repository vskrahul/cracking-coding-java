package org.coding.rahul.tree;

public class Main {

	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		
		BinaryTree.Node<Integer> root = new BinaryTree.Node<Integer>().value(10);
		
		tree.root(root);
		
		tree.insert(5);
		tree.insert(4);
		tree.insert(8);
		tree.insert(6);
		tree.insert(7);
		tree.insert(9);
		
		tree.insert(11);
		tree.insert(12);
		tree.insert(13);
		tree.insert(14);
		tree.insert(15);
		
		tree.pre_order(root);
		System.out.println(String.format("\nHegith = %d", tree.height(tree.root)));
		tree.top_view(root);
		tree.level_order(root);
	}
}