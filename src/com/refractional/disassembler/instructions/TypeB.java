package com.refractional.disassembler.instructions;

import com.refractional.disassembler.Node;

public class TypeB extends BranchTypes{
    public int brAddress;

    public TypeB(String opcode,
                 String name,
                 int brAddress) {
        super(opcode, name);
        this.brAddress = brAddress;
    }

    @Override
    public String toString() {
        return name + " " + label;
    }

    public static TypeB from(Node node, String fullCode){
        int brAddress = b2dSigned(fullCode.substring(6, 32));
        return new TypeB(node.opcode, node.mnemonic, brAddress);
    }
}
