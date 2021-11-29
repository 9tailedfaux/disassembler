package com.refractional.disassembler;

public class Node {
	String mnemonic;
	String type;
	Node zero; 
	Node one; 
	
	Node(String mnemonic, String type) {
		this.mnemonic = mnemonic;
		this.type = type;
		one = null; 
		zero = null; 
	} 
}