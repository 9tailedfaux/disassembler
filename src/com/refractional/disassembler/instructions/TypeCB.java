package com.refractional.disassembler.instructions;

public class TypeCB extends Instruction{
    public String condBrAddress;
    public String rt;

    public TypeCB (String opcode,
                   String name,
                   String condBrAddress,
                   String rt) {
        super(opcode, name);
        this.condBrAddress = condBrAddress;
        this.rt = rt;
    }
}
