package com.refractional.disassembler;

public class ThirtyTwoBits {
    private int index = 0;
    private int[] bytes;

    public ThirtyTwoBits() {
        init();
    }

    public boolean addByte(int b){
        bytes[index++] = b;
        if (index >= 4) {
            return true;
        }
        return false;
    }

    public String toString() {
        return buildString();
    }

    private String buildString() {
        StringBuilder builder = new StringBuilder();
        for (int aByte : bytes) {
            String bitString = Integer.toBinaryString(aByte);
            bitString = String.format("%8s", bitString);
            bitString = bitString.replace(' ', '0');
            builder.append(bitString);
        }
        return builder.toString();
    }

    private void init() {
        index = 0;
        bytes = new int[4];
    }

    public void clear() {
        init();
    }
}
