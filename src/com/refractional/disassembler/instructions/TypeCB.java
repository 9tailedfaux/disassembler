package com.refractional.disassembler.instructions;

import com.refractional.disassembler.Node;

public class TypeCB extends BranchTypes {
    public int condBrAddress;
    public int rt;

    public TypeCB (String opcode,
                   String name,
                   int condBrAddress,
                   int rt) {
        super(opcode, name);
        this.condBrAddress = condBrAddress;
        this.rt = rt;
    }

    @Override
    public String toString() {
        return name + " X" + rt + ", " + label;
    }

    public static TypeCB from(Node node, String fullCode){
        int condBrAddress = b2dUnsigned(fullCode.substring(8, 27));
        int rt = b2dUnsigned(fullCode.substring(27, 32));
        if (node.mnemonic.contentEquals("B.cond")) {
            return new CBcond(
                    node.opcode,
                    node.mnemonic.substring(0, 6),
                    condBrAddress,
                    rt);
        }
        return new TypeCB(node.opcode, node.mnemonic, condBrAddress, rt);
    }
}

class CBcond extends TypeCB {

    private final String condition;

    public CBcond(String opcode, String name, int condBrAddress, int rt) {
        super(opcode, name, condBrAddress, rt);
        condition = getCondition(rt);
    }

    @Override
    public String toString() {
        return name + "." + condition + " " + condBrAddress;
    }

    private String getCondition(int code) {
        switch (code) {
            case 0: return "EQ";
            case 1: return "NE";
            case 2: return "HS";
            case 3: return "LO";
            case 4: return "MI";
            case 5: return "PL";
            case 6: return "VS";
            case 7: return "VC";
            case 8: return "HI";
            case 9: return "LS";
            case 10: return "GE";
            case 11: return "LT";
            case 12: return "GT";
            case 13: return "LE";
            default: return "oof";
        }
    }
}