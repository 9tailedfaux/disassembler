package com.refractional.disassembler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public class Disassembler {
    public static void main(String[] args) throws FileNotFoundException {
        initialParse();
    }

    private static void initialParse() throws FileNotFoundException {
        Gson gson = new Gson();
        URL url = Disassembler.class.getResource("resources/opcodes.json");
        assert url != null;
        File opcodes = new File(url.getPath());
        FileReader fileReader = new FileReader(opcodes);
        JsonReader reader = new JsonReader(fileReader);
        gson.fromJson(reader, JsonArray.class);
    }

    private BinaryTree generateTree(JsonArray data){
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
