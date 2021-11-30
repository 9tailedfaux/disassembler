package com.refractional.disassembler.instructions;

import com.refractional.disassembler.Label;

public abstract class BranchTypes extends Instruction{
    public Label label;

    public BranchTypes(String opcode, String name) {
        super(opcode, name);
    }
}
