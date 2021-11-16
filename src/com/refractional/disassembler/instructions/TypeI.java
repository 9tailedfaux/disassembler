package com.refractional.disassembler.instructions;

public class TypeI extends Instruction{
    public String aluImmediate;
    public String rn;
    public String rd;

    public TypeI(String opcode,
                 String name,
                 String aluImmediate,
                 String rn,
                 String rd) {
        super(opcode, name);
        this.aluImmediate = aluImmediate;
        this.rn = rn;
        this.rd = rd;
    }
}
