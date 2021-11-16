package com.refractional.disassembler;

public class BinaryTree {
	Node root; 
	
	public void add(String value, String code) {
		root = addRecursive(root, value, code);
	} 
	
	public String get(String code) {
		return getRecursive(root, code);
	}
	
	private Node addRecursive(Node current, String value, String code) {
		if (code.length() <= 1) {
			return new Node(value);
		}
		
		if (current == null) {
			current = new Node(null);
		}
		
		if (code.charAt(0) == '0') {
			current.zero = addRecursive(current.zero, value, code.substring(1, code.length() - 1));
		}
		else if (code.charAt(0) == '1') {
			current.one = addRecursive(current.one, value, code.substring(1, code.length() - 1));
		}
		
		return current;
	}
	
	private String getRecursive(Node current, String code) {
		if (current == null) return "oof";
		
		if (code.contentEquals("0")) return current.zero.value;
		if (code.contentEquals("1")) return current.one.value;
		
		if (code.charAt(0) == '0') return getRecursive(current.zero, code.substring(1, code.length() - 1));
		if (code.charAt(0) == '1') return getRecursive(current.one, code.substring(1, code.length() - 1));
		
		return "big oof";
	}
}