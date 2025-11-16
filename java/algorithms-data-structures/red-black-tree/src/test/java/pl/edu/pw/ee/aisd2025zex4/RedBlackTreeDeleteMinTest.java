package pl.edu.pw.ee.aisd2025zex4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RedBlackTreeDeleteMinTest {

    @Test
    public void testDeleteMinSingle() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(5, 5);
        tree.deleteMin();

        assertNull(tree.get(5));
    }

    @Test
    public void testDeleteMinFromComplexTree() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(5, 5);
        tree.put(3, 3);
        tree.put(7, 7);
        tree.put(1, 1);

        tree.deleteMin();

        assertNull(tree.get(1));
        assertEquals(3, tree.get(3));
        assertEquals(5, tree.get(5));
        assertEquals(7, tree.get(7));
    }

    @Test
    public void testDeleteMinUntilEmpty() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        for (int i = 1; i <= 5; i++) {
            tree.put(i, i);
        }

        for (int i = 1; i <= 5; i++) {
            tree.deleteMin();
            assertNull(tree.get(i));
        }

        tree.deleteMin();
    }

    @Test
    public void testTreeNotBrokenAfterDeleteMin() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        int[] vals = {10, 5, 15, 3, 7, 12, 17, 1};

        for (int v : vals) {
            tree.put(v, v);
        }

        tree.deleteMin();

        assertNull(tree.get(1));

        for (int v : vals) {
            if (v == 1) continue;
            assertEquals(v, tree.get(v));
        }
    }

    @Test
    public void testDeleteMinOnEmptyTreeDoesNotCrash() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.deleteMin();
    }

    @Test
    public void testPutAndDeleteMinRightHeavyTree() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(1, 1);
        tree.put(2, 2);
        tree.put(3, 3);

        tree.deleteMin();

        assertNull(tree.get(1));
        assertEquals(2, tree.get(2));
        assertEquals(3, tree.get(3));
    }

    @Test
    public void testDeleteMinDeepLeftPath() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(50, 50);
        tree.put(30, 30);
        tree.put(20, 20);
        tree.put(10, 10);

        tree.deleteMin();

        assertNull(tree.get(10));
    }

    @Test
    public void testDeleteMinRightOnlyTree() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(5, 5);
        tree.put(7, 7);

        tree.deleteMin();

        assertNull(tree.get(5));
        assertEquals(7, tree.get(7));
    }

    @Test
    public void testDeleteMinLeftOnlyTree() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(5, 5);
        tree.put(3, 3);

        tree.deleteMin();

        assertNull(tree.get(3));
        assertEquals(5, tree.get(5));
    }

    @Test
    public void testDeleteMinTriggersRightLeftRedRotation() {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();

        tree.put(10, 10);
        tree.put(20, 20);
        tree.put(15, 15);

        tree.deleteMin();

        assertNull(tree.get(10));
        assertEquals(15, tree.get(15));
        assertEquals(20, tree.get(20));
    }

}