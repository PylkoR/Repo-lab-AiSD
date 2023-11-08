package pl.edu.pw.ee.aisd2023zlab4;

import org.junit.Before;
import org.junit.Test;

public class RbtMapTest {

    private RedBlackTree<Integer, String> tree;

    @Before
    public void setup() {
        tree = new RedBlackTree<>();
    }

    @Test
    public void should_makeBalancedTreeWithSameNumberOfBlackNodesInEveryPath() {
        //given
        int size = 7;
        int numberOfBlackNodes = 1;
        boolean arePathsEqual = true;
        for (int i = 0; i < size; i++) {
            tree.put(i, "P. Czarnek");
        }
        Node<Integer, String> tmp = tree.getRoot();

        //when
        while (tmp.getLeft() != null) {
            if (tmp.getLeft().getColor().equals(Color.BLACK))
                numberOfBlackNodes++;

            tmp = tmp.getLeft();
        }

        //then
        System.out.println(numberOfBlackNodes);
    }
}
