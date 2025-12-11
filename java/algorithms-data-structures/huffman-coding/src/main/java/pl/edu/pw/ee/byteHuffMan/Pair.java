package pl.edu.pw.ee.byteHuffMan;

import java.util.Arrays;

public class Pair {
    public byte[] block;
    public int count;

    public Pair(byte[] block, int count) {
        this.block = block;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) return false;
        return Arrays.equals(block, ((Pair) obj).block);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(block);
    }
}