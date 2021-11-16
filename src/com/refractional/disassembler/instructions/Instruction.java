package com.refractional.disassembler.instructions;

public abstract class Instruction {
	String opcode;
	String name;

	public Instruction(String opcode, String name) {
		this.opcode = opcode;
		this.name = name;
	}
}