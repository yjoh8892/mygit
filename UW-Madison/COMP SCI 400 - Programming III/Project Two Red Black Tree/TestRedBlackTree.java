import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class TestRedBlackTree {
    RedBlackTree<Integer> tree = new RedBlackTree<>();

    @Before
    public void setup() {
        List<Integer> dataOrder = new ArrayList<>();
        dataOrder.add(10);
        dataOrder.add(18);
        dataOrder.add(7);
        dataOrder.add(15);
        dataOrder.add(16);
        dataOrder.add(30);
        dataOrder.add(25);
        dataOrder.add(40);
        dataOrder.add(60);
        dataOrder.add(2);
        dataOrder.add(1);
        dataOrder.add(70);

        for (Integer i : dataOrder) {
            tree.insert(i);
        }
    }

    @Test
    public void testRootNodeShouldBeBlack() {
        assertTrue(tree.root.isBlack);
    }

    @Test
    public void testNoRedRedClash() {
        RedBlackTree.Node<Integer> node = tree.root;
        LinkedList<RedBlackTree.Node<Integer>> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            RedBlackTree.Node<Integer> next = q.removeFirst();
            if (next.leftChild != null) q.add(next.leftChild);
            if (next.rightChild != null) q.add(next.rightChild);
            if (next.parent != null) {
                assertFalse(!next.isBlack && !next.parent.isBlack);
            }
        }
    }

    @Test
    public void testDataOrder() {
        List<Integer> rotatedDataOrder = new ArrayList<>();
        rotatedDataOrder.add(16);
        rotatedDataOrder.add(10);
        rotatedDataOrder.add(25);
        rotatedDataOrder.add(2);
        rotatedDataOrder.add(15);
        rotatedDataOrder.add(18);
        rotatedDataOrder.add(40);
        rotatedDataOrder.add(1);
        rotatedDataOrder.add(7);
        rotatedDataOrder.add(30);
        rotatedDataOrder.add(60);
        rotatedDataOrder.add(70);
        String expectedString = "[" + rotatedDataOrder.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]";
        assertEquals(expectedString, tree.toString());
    }

    @Test
    public void testRedBlackOrder() {
        RedBlackTree.Node<Integer> node = tree.root;
        String output = "[";
        LinkedList<RedBlackTree.Node<Integer>> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            RedBlackTree.Node<Integer> next = q.removeFirst();
            if (next.leftChild != null) q.add(next.leftChild);
            if (next.rightChild != null) q.add(next.rightChild);
            output += next.isBlack;
            if (!q.isEmpty()) output += ", ";
        }
        output += "]";
        String expected = "[true, true, true, true, true, true, false, false, false, true, true, false]";
        assertEquals(expected, output);
    }

}
