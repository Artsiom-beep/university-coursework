package pl.edu.pw.ee.byteHuffMan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HuffmanTree {

    public Node root;

    public HuffmanTree(ArrayList<Pair> pairs) {
        buildTree(pairs);
    }

    private void buildTree(ArrayList<Pair> pairs) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < pairs.size(); i++) {
            Pair p = pairs.get(i);
            nodes.add(new Node(p.block, p.count, i)); // сохраняем индекс
        }
        if (nodes.isEmpty()) {
            root = null;
            return;
        }
        // special case: only one distinct block
        if (nodes.size() == 1) {
            root = new Node(null, nodes.get(0).count);
            root.left = nodes.get(0);
            return;
        }
        while (nodes.size() > 1) {
            nodes.sort(null); // использует compareTo (по count)
            Node a = nodes.remove(0);
            Node b = nodes.remove(0);
            Node parent = new Node(null, a.count + b.count);
            parent.left = a;
            parent.right = b;
            nodes.add(parent);
        }
        root = nodes.get(0);
    }

    public ArrayList<String> generateCodes(ArrayList<Pair> leaves) {
        ArrayList<String> codes = new ArrayList<>(Collections.nCopies(leaves.size(), null));
        if (root == null) return codes;
        generateCodesRecursive(root, "", codes);
        return codes;
    }

    private void generateCodesRecursive(Node node, String code, ArrayList<String> codes) {
        if (node == null) return;
        if (node.isLeaf()) {
            String assigned = code.isEmpty() ? "0" : code;
            if (node.leafIndex >= 0 && node.leafIndex < codes.size()) {
                codes.set(node.leafIndex, assigned);
            }
            return;
        }
        generateCodesRecursive(node.left, code + "0", codes);
        generateCodesRecursive(node.right, code + "1", codes);
    }

    public static Node buildTreeFromCodes(ArrayList<Pair> leaves, ArrayList<String> codes) {
        Node root = new Node(null,0);
        for (int i = 0; i < leaves.size(); i++) {
            Node node = root;
            String code = codes.get(i);
            for (char c : code.toCharArray()) {
                if (c == '0') {
                    if (node.left == null) node.left = new Node(null,0);
                    node = node.left;
                } else {
                    if (node.right == null) node.right = new Node(null,0);
                    node = node.right;
                }
            }
            node.block = leaves.get(i).block;
        }
        return root;
    }
}
