package com.refractional.disassembler;

public class Node {
	public String opcode;
	public String type;
	public String mnemonic;
	Node zero; 
	Node one; 
	
	Node(String mnemonic, String type, String opcode) {
		this.opcode = opcode;
		this.type = type;
		this.mnemonic = mnemonic;
		one = null; 
		zero = null; 
	} 
}