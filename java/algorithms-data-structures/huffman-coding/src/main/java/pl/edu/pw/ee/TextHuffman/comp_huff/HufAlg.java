package pl.edu.pw.ee.TextHuffman.comp_huff;

import pl.edu.pw.ee.TextHuffman.Classes.ComparePair;
import pl.edu.pw.ee.TextHuffman.Classes.HelpPair;
import pl.edu.pw.ee.TextHuffman.Classes.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HufAlg {
    public String input;
    public String output = "";
    int N = 1;
    public List<String> podz = new ArrayList<>();
    public List<Pair> czest = new ArrayList<>();
    List<HuffmanTreeNode> lknot = new ArrayList<>();
    List<HuffmanTreeNode> lnodes = new ArrayList<>();

    public HufAlg(String input, int N){
        this.N = N;
        this.input = input;
    }

    public String huffComp(){
        podz.clear();
        czest.clear();
        lknot.clear();
        lnodes.clear();

        addToPodz();
        addToCzest();

        ComparePair comp = new ComparePair();
        czest.sort(comp);
        czestToOutput();

        for(int i = 0; i < czest.size(); i++){
            lnodes.add(new HuffmanTreeNode(czest.get(i).data, czest.get(i).count));
        }

        while (lnodes.size() + lknot.size() > 1) {

            HuffmanTreeNode left;
            HuffmanTreeNode right;

            if (lnodes.size() == 0) {
                left = lknot.get(0);
                right = lknot.get(1);
                lknot.remove(0);
                lknot.remove(0);
            }
            else if (lknot.size() == 0) {
                left = lnodes.get(0);
                right = lnodes.get(1);
                lnodes.remove(0);
                lnodes.remove(0);
            }
            else {
                if (lnodes.get(0).count < lknot.get(0).count) {
                    left = lnodes.get(0);
                    lnodes.remove(0);
                } else {
                    left = lknot.get(0);
                    lknot.remove(0);
                }

                if (lnodes.size() == 0) {
                    right = lknot.get(0);
                    lknot.remove(0);
                } else if (lknot.size() == 0) {
                    right = lnodes.get(0);
                    lnodes.remove(0);
                } else if (lnodes.get(0).count < lknot.get(0).count) {
                    right = lnodes.get(0);
                    lnodes.remove(0);
                } else {
                    right = lknot.get(0);
                    lknot.remove(0);
                }
            }

            HuffmanTreeNode node = new HuffmanTreeNode("", left.count + right.count);
            node.left = left;
            node.right = right;
            lknot.add(node);

            lknot.sort(Comparator.comparingInt(n -> n.count));
        }

        HuffmanTreeNode root = lknot.get(lknot.size() - 1);

        List<HelpPair> kody = new ArrayList<>();
        printCodes(root, new StringBuilder(), kody);

        return output;
    }

    private void addToPodz(){
        int i = 0;
        for (; i + N <= input.length(); i += N) {
            podz.add(input.substring(i, i + N));
        }
        if (i < input.length()) {
            podz.add(input.substring(i));
        }
    }

    private void addToCzest(){
        for(int i = 0; i < podz.size(); i++){
            boolean found = false;
            for(int j = 0; j < czest.size(); j++){
                if(czest.get(j).data.equals(podz.get(i))) {
                    found = true;
                    czest.get(j).count += 1;
                    break;
                }
            }
            if(!found){
                czest.add(new Pair(podz.get(i), 1));
            }
        }
    }

    private void czestToOutput(){
        for (int i = 0; i < czest.size(); i++){
            output += "\n" + czest.get(i).data + " " + czest.get(i).count;
        }
    }

    public static void printCodes(HuffmanTreeNode root2, StringBuilder code, List<HelpPair> codes) {
        if (root2 == null) return;

        if (root2.left == null && root2.right == null) {
            System.out.println(root2.data + ": " + code);
            codes.add(new HelpPair(root2.data, code.toString()));
            return;
        }

        if (root2.left != null) {
            printCodes(root2.left, code.append('0'), codes);
            code.deleteCharAt(code.length() - 1);
        }

        if (root2.right != null) {
            printCodes(root2.right, code.append('1'), codes);
            code.deleteCharAt(code.length() - 1);
        }
    }

    public String decompressString(String in){
        StringBuilder out = new StringBuilder();

        // FIX #4 — берём корень правильно
        HuffmanTreeNode root = lknot.get(lknot.size() - 1);
        HuffmanTreeNode node = root;

        for (int i = 0; i < in.length(); i++){
            char bit = in.charAt(i);

            if (bit == '0') {
                node = node.left;
            } else {
                node = node.right;
            }

            if (node.left == null && node.right == null) {
                out.append(node.data);
                node = root;
            }
        }

        return out.toString();
    }

    public String encodeString() {
        StringBuilder bits = new StringBuilder();

        List<HelpPair> codes = new ArrayList<>();
        HuffmanTreeNode root = lknot.get(lknot.size() - 1);
        printCodes(root, new StringBuilder(), codes);

        for (String chunk : podz) {
            for (HelpPair hp : codes) {
                if (hp.data.equals(chunk)) {
                    bits.append(hp.code);
                    break;
                }
            }
        }
        return bits.toString();
    }
}
