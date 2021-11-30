package com.refractional.disassembler;

public class Label {
    int lineNum;
    String name;

    public Label(int lineNum, String name){
        this.lineNum = lineNum;
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}