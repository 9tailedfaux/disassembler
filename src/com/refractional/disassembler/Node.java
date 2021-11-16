package com.refractional.disassembler;

public class Node {
	String value; 
	Node zero; 
	Node one; 
	
	Node(String value) { 
		this.value = value; 
		one = null; 
		zero = null; 
	} 
}