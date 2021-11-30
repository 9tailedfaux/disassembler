package com.refractional.disassembler.instructions;

import com.refractional.disassembler.Node;

public class TypeR extends Instruction{
    public int rm;
    public int shamt;
    public int rn;
    public int rd;

    public TypeR(String opcode,
                 String name,
                 int rm,
                 int shamt,
                 int rn,
                 int rd) {
        super(opcode, name);
        this.rm = rm;
        this.shamt = shamt;
        this.rn = rn;
        this.rd = rd;
    }

    @Override
    public String toString() {
        return name + " X" + rd + ", X" + rn + ", X" + rm;
    }

    public static TypeR from(Node node, String fullCode){
        int rm = binaryToDec(fullCode.substring(11, 16));
        int shamt = binaryToDec(fullCode.substring(16, 22));
        int rn = binaryToDec(fullCode.substring(22, 27));
        int rd = binaryToDec(fullCode.substring(27, 32));
        return new TypeR(node.opcode, node.mnemonic, rm, shamt, rn, rd);
    }
}
