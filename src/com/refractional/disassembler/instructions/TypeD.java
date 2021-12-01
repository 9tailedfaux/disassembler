package com.refractional.disassembler.instructions;

import com.refractional.disassembler.Node;

public class TypeD extends Instruction{
    public int dtAddress;
    public int op;
    public int rn;
    public int rt;

    public TypeD(String opcode,
                 String name,
                 int dtAddress,
                 int op,
                 int rn,
                 int rt) {
        super(opcode, name);
        this.dtAddress = dtAddress;
        this.op = op;
        this.rn = rn;
        this.rt = rt;
    }

    @Override
    public String toString() {
        return name + " R" + rt + ", [X" + rn + ", #" + dtAddress + "]";
    }

    public static TypeD from(Node node, String fullCode){
        int dtAddress = b2dUnsigned(fullCode.substring(11, 20));
        int op = b2dUnsigned(fullCode.substring(20, 22));
        int rn = b2dUnsigned(fullCode.substring(22, 27));
        int rt = b2dUnsigned(fullCode.substring(27, 32));
        return  new TypeD(node.opcode, node.mnemonic, dtAddress, op, rn, rt);
    }
}
