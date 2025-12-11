package pl.edu.pw.ee.TextHuffman.Classes;

public class Pair {
    public String data;
    public int count;

    public Pair(String data, int count){
        this.data = data;
        this.count = count;
    }

    public int compareTo(Pair o){
        return this.count - o.count;
    }
}