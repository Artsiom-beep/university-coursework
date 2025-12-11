package pl.edu.pw.ee.byteHuffMan;

import java.util.ArrayList;
import java.util.Arrays;

public class SimpleHuffman {

    public static ArrayList<Byte> compress(byte[] input, int blockSize, ArrayList<Pair> outPairs, ArrayList<String> outCodes) {
        ArrayList<byte[]> blocks = splitIntoBlocks(input, blockSize);

        ArrayList<Pair> pairs = calculateFrequencies(blocks);

        HuffmanTree tree = new HuffmanTree(pairs);
        ArrayList<String> codes = tree.generateCodes(pairs);

        outPairs.clear();
        outPairs.addAll(pairs);
        outCodes.clear();
        outCodes.addAll(codes);

        return encode(blocks, pairs, codes);
    }

    public static byte[] decompress(ArrayList<Byte> compressed, ArrayList<Pair> pairs, ArrayList<String> codes) {
        Node root = HuffmanTree.buildTreeFromCodes(pairs, codes);
        return decode(compressed, root, pairs);
    }

    private static ArrayList<byte[]> splitIntoBlocks(byte[] input, int N) {
        ArrayList<byte[]> podz = new ArrayList<>();
        int i = 0;
        while (i + N <= input.length) {
            podz.add(Arrays.copyOfRange(input, i, i+N));
            i += N;
        }
        if (i < input.length) podz.add(Arrays.copyOfRange(input, i, input.length));
        return podz;
    }

    private static ArrayList<Pair> calculateFrequencies(ArrayList<byte[]> blocks) {
        ArrayList<Pair> pairs = new ArrayList<>();
        for (byte[] block : blocks) {
            boolean found = false;
            for (Pair p : pairs) {
                if (Arrays.equals(p.block, block)) {
                    p.count++;
                    found = true;
                    break;
                }
            }
            if (!found) pairs.add(new Pair(block, 1));
        }
        return pairs;
    }

    private static ArrayList<Byte> encode(ArrayList<byte[]> blocks, ArrayList<Pair> pairs, ArrayList<String> codes) {
        StringBuilder bits = new StringBuilder();
        for (byte[] block : blocks) {
            for (int i = 0; i < pairs.size(); i++) {
                if (Arrays.equals(pairs.get(i).block, block)) {
                    bits.append(codes.get(i));
                    break;
                }
            }
        }

        ArrayList<Byte> compressed = new ArrayList<>();
        int len = bits.length();
        for (int i = 0; i < len; i += 8) {
            byte b = 0;
            for (int j = 0; j < 8 && i+j < len; j++) {
                if (bits.charAt(i+j) == '1') b |= (byte) (1 << (7-j));
            }
            compressed.add(b);
        }
        return compressed;
    }

    private static byte[] decode(ArrayList<Byte> compressed, Node root, ArrayList<Pair> pairs) {
        StringBuilder bitStr = new StringBuilder();
        for (Byte b : compressed) {
            for (int j = 7; j >= 0; j--) {
                bitStr.append(((b >> j) & 1) == 1 ? '1' : '0');
            }
        }

        ArrayList<Byte> output = new ArrayList<>();
        Node node = root;
        int decodedBlocks = 0;
        int totalBlocks = pairs.stream().mapToInt(p -> p.count).sum();

        for (int i = 0; i < bitStr.length() && decodedBlocks < totalBlocks; i++) {
            char c = bitStr.charAt(i);
            node = (c == '0') ? node.left : node.right;
            if (node.isLeaf()) {
                for (byte by : node.block) output.add(by);
                node = root;
                decodedBlocks++;
            }
        }

        byte[] outBytes = new byte[output.size()];
        for (int i = 0; i < output.size(); i++) outBytes[i] = output.get(i);
        return outBytes;
    }
}
