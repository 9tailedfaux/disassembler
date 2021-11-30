package com.refractional.disassembler.instructions;

import com.refractional.disassembler.Node;

public class TypeI extends Instruction{
    public int aluImmediate;
    public int rn;
    public int rd;

    public TypeI(String opcode,
                 String name,
                 int aluImmediate,
                 int rn,
                 int rd) {
        super(opcode, name);
        this.aluImmediate = aluImmediate;
        this.rn = rn;
        this.rd = rd;
    }

    @Override
    public String toString() {
        return name + " X" + rd + ", X" + rn + ", #" + aluImmediate;
    }

    public static TypeI from(Node node, String fullCode){
        int aluImmediate = binaryToDec(fullCode.substring(10, 22));
        int rn = binaryToDec(fullCode.substring(22, 27));
        int rd = binaryToDec(fullCode.substring(27, 32));
        return new TypeI(node.opcode, node.mnemonic, aluImmediate, rn, rd);
    }
}
