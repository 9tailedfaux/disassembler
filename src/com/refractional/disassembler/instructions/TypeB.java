package com.refractional.disassembler.instructions;

public class TypeB extends Instruction{
    public String brAddress;

    public TypeB(String opcode,
                 String name,
                 String brAddress) {
        super(opcode, name);
        this.brAddress = brAddress;
    }
}
