package org.example;

import java.util.*;

import java.util.HashMap;
import java.util.Map;

public class HuffmanEncoder {

    public Map<Character, String> generateCodes(String input, HuffmanTree tree) {
        HuffmanNode root = tree.build(input);
        Map<Character, String> codes = new HashMap<>();
        tree.buildCodes(root, "", codes);
        return codes;
    }

    public String encode(String input) {
        HuffmanTree tree = new HuffmanTree();
        Map<Character, String> codes = generateCodes(input, tree);

        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            sb.append(codes.get(c));
        }
        return sb.toString();
    }
}