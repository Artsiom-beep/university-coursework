package pl.edu.pw.ee.TextHuffman.comp_huff;

public class HuffmanTreeNode {
    String data;
    int count;
    HuffmanTreeNode left, right;

    HuffmanTreeNode(String data, int count){
        this.data = data;
        this.count = count;
        left = null;
        right = null;
    }
}