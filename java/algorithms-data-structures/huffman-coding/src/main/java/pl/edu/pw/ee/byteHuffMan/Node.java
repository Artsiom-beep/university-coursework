package pl.edu.pw.ee.byteHuffMan;

public class Node implements Comparable<Node> {
    byte[] block;     // immutable copy
    public final int count;
    public Node left, right;
    // если >=0 — указывает на индекс листа (соответствует индексу Pair в списке)
    public final int leafIndex;

    public Node(byte[] block, int count) {
        this(block, count, -1);
    }

    public Node(byte[] block, int count, int leafIndex) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        this.block = (block == null) ? null : block.clone();
        this.count = count;
        this.leafIndex = leafIndex;
    }

    public boolean isLeaf() {
        return block != null && left == null && right == null;
    }

    public byte[] getBlock() {
        return (block == null) ? null : block.clone();
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.count, o.count);
    }
}