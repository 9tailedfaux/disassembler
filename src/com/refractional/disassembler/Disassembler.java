package com.refractional.disassembler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import com.refractional.disassembler.instructions.Instruction;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Disassembler {
    private static BinaryTree binaryTree;
    public static HashMap<Integer, Label> labels;

    public static void main(String[] args) throws IOException {
        binaryTree = initialParse();
        labels = new HashMap<>();
        ArrayList<String> binary = binaryFileToBinaryArray(args[0]);
        ArrayList<Instruction> instructions = binaryArrayToInstructions(binary);
        printInstructions(instructions);
    }

    private static void printInstructions(ArrayList<Instruction> instructions){
        for (int i = 0; i < instructions.size(); i++) {
            if (labels.containsKey(i)) {
                System.out.println(labels.get(i).toString());
            }
            System.out.println(instructions.get(i).toString());
        }
    }

    private static ArrayList<Instruction> binaryArrayToInstructions(ArrayList<String> binary) {
        ArrayList<Instruction> instructions = new ArrayList<>();
        for (int i = 0; i < binary.size(); i++) {
            Node node = binaryTree.get(binary.get(i));
            instructions.add(Instruction.from(node, binary.get(i), i, labels));
        }
        return instructions;
    }

    private static ArrayList<String> binaryFileToBinaryArray(String filename) throws IOException {
        ArrayList<String> binaryArrayList = new ArrayList<>();
        FileInputStream binaryFile = new FileInputStream(filename);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(binaryFile);
        ThirtyTwoBits bits = new ThirtyTwoBits();
        while (true) {
            int val = bufferedInputStream.read();
            if (val < 0) break;
            if (bits.addByte(val)) {
                binaryArrayList.add(bits.toString());
                bits.clear();
            }
        }
        return binaryArrayList;
    }

    private static BinaryTree initialParse() throws FileNotFoundException {
        Gson gson = new Gson();
        URL url = Disassembler.class.getResource("resources/opcodes.json");
        assert url != null;
        File opcodes = new File(url.getPath());
        FileReader fileReader = new FileReader(opcodes);
        JsonReader reader = new JsonReader(fileReader);
        JsonArray array = gson.fromJson(reader, JsonArray.class);
        return generateTree(array);
    }

    private static BinaryTree generateTree(JsonArray data){
        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < data.size(); i++){
            JsonArray instruction = data.get(i).getAsJsonArray();
            tree.add(
                    instruction.get(0).getAsString(),
                    instruction.get(1).getAsString(),
                    instruction.get(2).getAsString()
            );
        }
        return tree;
    }
}
