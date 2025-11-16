package pl.edu.pw.ee.aisd2025zex4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeGetTest {

    @Test
    public void testGetFromEmptyTree() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        assertNull(tree.get(10));
    }

    @Test
    public void testGetExistingValues() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(1, 10);
        tree.put(2, 20);
        tree.put(3, 30);

        assertEquals(10, tree.get(1));
        assertEquals(20, tree.get(2));
        assertEquals(30, tree.get(3));
    }

    @Test
    public void testGetNotExisting() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(1, 10);

        assertNull(tree.get(100));
    }
}