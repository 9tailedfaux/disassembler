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
        int rm = b2dUnsigned(fullCode.substring(11, 16));
        int shamt = b2dUnsigned(fullCode.substring(16, 22));
        int rn = b2dUnsigned(fullCode.substring(22, 27));
        int rd = b2dUnsigned(fullCode.substring(27, 32));

        if (node.mnemonic.contentEquals("BR")) {
            return new BR(
                    node.opcode,
                    node.mnemonic,
                    rm,
                    shamt,
                    rn,
                    rd);
        }
        if (node.mnemonic.contentEquals("PRNT")) {
            return new PRNT(
                    node.opcode,
                    node.mnemonic,
                    rm,
                    shamt,
                    rn,
                    rd);
        }
        if (node.mnemonic.contentEquals("PRNL")
                || node.mnemonic.contentEquals("DUMP")
                || node.mnemonic.contentEquals("HALT")) {
            return new NoRegs(
                    node.opcode,
                    node.mnemonic,
                    rm,
                    shamt,
                    rn,
                    rd);
        }

        return new TypeR(node.opcode, node.mnemonic, rm, shamt, rn, rd);
    }
}

class NoRegs extends TypeR {

    @Override
    public String toString() {
        return name;
    }

    public NoRegs(String opcode, String name, int rm, int shamt, int rn, int rd) {
        super(opcode, name, rm, shamt, rn, rd);
    }
}

class PRNT extends TypeR {

    public PRNT(String opcode, String name, int rm, int shamt, int rn, int rd) {
        super(opcode, name, rm, shamt, rn, rd);
    }

    @Override
    public String toString() {
        return name + " X" + rd;
    }
}

class BR extends TypeR {
    public BR(String opcode, String name, int rm, int shamt, int rn, int rd) {
        super(opcode, name, rm, shamt, rn, rd);
    }

    @Override
    public String toString(){
        return name + " X" + rn;
    }
}
