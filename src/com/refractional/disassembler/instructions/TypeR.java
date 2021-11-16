package com.refractional.disassembler.instructions;

import com.refractional.disassembler.BinaryTree;

public class TypeR extends Instruction{
    public String rm;
    public String shamt;
    public String rn;
    public String rd;

    public TypeR(String opcode,
                 String name,
                 String rm,
                 String shamt,
                 String rn,
                 String rd) {
        super(opcode, name);
        this.rm = rm;
        this.shamt = shamt;
        this.rn = rn;
        this.rd = rd;
    }
}
