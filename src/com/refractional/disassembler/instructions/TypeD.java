package com.refractional.disassembler.instructions;

public class TypeD extends Instruction{
    public String dtAddress;
    public String op;
    public String rn;
    public String rt;

    public TypeD(String opcode,
                 String name,
                 String dtAddress,
                 String op,
                 String rn,
                 String rt) {
        super(opcode, name);
        this.dtAddress = dtAddress;
        this.op = op;
        this.rn = rn;
        this.rt = rt;
    }
}
