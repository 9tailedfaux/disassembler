package com.refractional.disassembler.instructions;

import com.refractional.disassembler.Label;
import com.refractional.disassembler.Node;

import java.util.Map;

public abstract class Instruction {
	String opcode;
	String name;
	int lineNum;

	public abstract String toString();

	public Instruction(String opcode, String name) {
		this.opcode = opcode;
		this.name = name;
	}

	private static String twosComplement(String binary){
		StringBuilder twos = new StringBuilder();
		boolean foundOne = false;
		for (int i = binary.length() - 1; i >= 0; i--) {
			if (foundOne) {
				twos.insert(0, ((binary.charAt(i) == '0') ? '1' : '0'));
			} else {
				twos.insert(0, (binary.charAt(i)));
			}
			if (binary.charAt(i) == '1' && !foundOne) {
				foundOne = true;
			}
		}
		return twos.toString();
	}

	public static int b2dSigned(String binary){
		int mult = 1;
		if (binary.charAt(0) == '1') {
			binary = twosComplement(binary);
			mult = -1;
		}
		return Integer.parseUnsignedInt(binary, 2) * mult;
	}

	public static int b2dUnsigned(String binary){
		return Integer.parseUnsignedInt(binary, 2);
	}

	private static Label itsABranch(int lineNum, int brAddress, Map<Integer, Label> labels) {
		int labelAddress = lineNum + brAddress;
		Label label = new Label(labelAddress, "Label" + labelAddress);
		labels.put(labelAddress, label);
		return label;
	}

	public static Instruction from(Node node, String fullCode, int lineNum, Map<Integer, Label> labels){
		Instruction returnBoi;
		switch (node.type) {
			case "B": {
				TypeB instruction = TypeB.from(node, fullCode);
				instruction.label = itsABranch(lineNum, instruction.brAddress, labels);
				returnBoi = instruction;
			} break;
			case "CB": {
				TypeCB instruction = TypeCB.from(node, fullCode);
				instruction.label = itsABranch(lineNum, instruction.condBrAddress, labels);
				returnBoi = instruction;
			} break;
			case "D": returnBoi = TypeD.from(node, fullCode); break;
			case "I": returnBoi = TypeI.from(node, fullCode); break;
			case "R": returnBoi = TypeR.from(node, fullCode); break;
			default: return null;
		}
		returnBoi.lineNum = lineNum;
		return returnBoi;
	}
}