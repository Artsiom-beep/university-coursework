package org.example;

public class HuffmanNode implements Comparable<HuffmanNode> {
    char ch;
    int freq;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = '\0';
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return Integer.compare(this.freq, o.freq);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}