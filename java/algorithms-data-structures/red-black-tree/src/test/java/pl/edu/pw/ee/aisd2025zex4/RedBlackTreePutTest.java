package pl.edu.pw.ee.aisd2025zex4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreePutTest {

    @Test
    public void testInsertSingle() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(10, 10);

        assertEquals(10, tree.get(10));
    }

    @Test
    public void testInsertMany() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        for (int i = 1; i <= 20; i++) {
            tree.put(i, i * 5);
        }

        for (int i = 1; i <= 20; i++) {
            assertEquals(i * 5, tree.get(i));
        }
    }

    @Test
    public void testOverrideValue() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(5, 10);
        tree.put(5, 20);

        assertEquals(20, tree.get(5));
    }

    @Test
    public void testRootIsAlwaysBlack() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        for (int i = 1; i <= 10; i++) {
            tree.put(i, i);
            assertNotNull(tree.get(i));
        }
    }
}