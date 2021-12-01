package com.refractional.disassembler;

public class BinaryTree {
	Node root; 
	
	public void add(String mnemonic, String code, String type) {
		root = addRecursive(root, mnemonic, code, type);
	} 
	
	public Node get(String code) {
		return getRecursive(root, code);
	}
	
	private Node addRecursive(Node current, String mnemonic, String code, String type) {
		if (code.length() < 1) {
			return new Node(mnemonic, type, code);
		}
		
		if (current == null) {
			current = new Node(null, null, null);
		}
		
		if (code.charAt(0) == '0') {
			current.zero = addRecursive(current.zero, mnemonic, code.substring(1), type);
		}
		else if (code.charAt(0) == '1') {
			current.one = addRecursive(current.one, mnemonic, code.substring(1), type);
		}
		
		return current;
	}
	
	private Node getRecursive(Node current, String code) {
		if (current == null) return new Node("oof", "oof", "oof");

		if (current.zero == null && current.one == null) {
			return current;
		}
		
		if (code.charAt(0) == '0') return getRecursive(current.zero, code.substring(1));
		if (code.charAt(0) == '1') return getRecursive(current.one, code.substring(1));
		
		return new Node("big oof", "big oof", "big oof");
	}
}