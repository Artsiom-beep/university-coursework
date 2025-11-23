package org.example;

import java.util.*;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

    public HuffmanNode build(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        for (var entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        if (pq.size() == 1) {
            HuffmanNode only = pq.poll();
            return new HuffmanNode(only.freq, only, null);
        }

        while (pq.size() > 1) {
            HuffmanNode a = pq.poll();
            HuffmanNode b = pq.poll();
            HuffmanNode parent = new HuffmanNode(a.freq + b.freq, a, b);
            pq.add(parent);
        }

        return pq.poll();
    }

    public void buildCodes(HuffmanNode root, String code, Map<Character, String> codes) {
        if (root == null) return;

        if (root.isLeaf()) {
            codes.put(root.ch, code.length() > 0 ? code : "0");
            return;
        }

        buildCodes(root.left, code + "0", codes);
        buildCodes(root.right, code + "1", codes);
    }
}