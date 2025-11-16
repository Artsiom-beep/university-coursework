package pl.edu.pw.ee.aisd2025zex4.performance;

import org.junit.jupiter.api.Test;
import pl.edu.pw.ee.aisd2025zex4.RedBlackTree;

public class TestOfGeneratedData {
    private final DataGenerator generator = new DataGenerator();

    private void measureTime(String name, Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        System.out.println(name + " took " + (end - start) / 1_000_000.0 + " ms");
    }

    @Test
    public void insertRandomStrings() {
        int n = 1000;
        String[] keys = generator.generateStringRandData(n);
        measureTime("Insert Random", () -> {
            RedBlackTree<String, String> tree = new RedBlackTree<>();
            for (String key : keys) tree.put(key, "val");
        });
    }

    @Test
    public void searchRandomStrings() {
        int n = 1000;
        String[] keys = generator.generateStringRandData(n);
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        for (String key : keys) tree.put(key, "val");
        measureTime("Search Random", () -> {
            for (String key : keys) tree.get(key);
        });
    }

    @Test
    public void deleteMinRandomStrings() {
        int n = 500;
        String[] keys = generator.generateStringRandData(n);
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        for (String key : keys) tree.put(key, "val");
        measureTime("Delete Min Random", () -> {
            for (int i = 0; i < n; i++) tree.deleteMin();
        });
    }

    @Test
    public void insertGrowingSortedStrings() {
        int n = 1000;
        String[] keys = generator.generateStringAscData(n);
        measureTime("Insert Ascending", () -> {
            RedBlackTree<String, String> tree = new RedBlackTree<>();
            for (String key : keys) tree.put(key, "val");
        });
    }

    @Test
    public void searchGrowingSortedStrings() {
        int n = 1000;
        String[] keys = generator.generateStringAscData(n);
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        for (String key : keys) tree.put(key, "val");
        measureTime("Search Ascending", () -> {
            for (String key : keys) tree.get(key);
        });
    }

    @Test
    public void deleteMinGrowingSortedStrings() {
        int n = 500;
        String[] keys = generator.generateStringAscData(n);
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        for (String key : keys) tree.put(key, "val");
        measureTime("Delete Min Ascending", () -> {
            for (int i = 0; i < n; i++) tree.deleteMin();
        });
    }

    @Test
    public void insertLoweringSortedStrings() {
        int n = 1000;
        String[] keys = generator.generateStringDescData(n);
        measureTime("Insert Descending", () -> {
            RedBlackTree<String, String> tree = new RedBlackTree<>();
            for (String key : keys) tree.put(key, "val");
        });
    }

    @Test
    public void searchLoweringSortedStrings() {
        int n = 1000;
        String[] keys = generator.generateStringDescData(n);
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        for (String key : keys) tree.put(key, "val");
        measureTime("Search Descending", () -> {
            for (String key : keys) tree.get(key);
        });
    }

    @Test
    public void deleteMinLoweringSortedStrings() {
        int n = 500;
        String[] keys = generator.generateStringDescData(n);
        RedBlackTree<String, String> tree = new RedBlackTree<>();
        for (String key : keys) tree.put(key, "val");
        measureTime("Delete Min Descending", () -> {
            for (int i = 0; i < n; i++) tree.deleteMin();
        });
    }
}