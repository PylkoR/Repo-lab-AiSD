package pl.edu.pw.ee.aisd2023zlab4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class RbtMapTest {

    private RbtMap<Integer, String> tree;

    @Before
    public void setup() {
        tree = new RbtMap<>();
    }

    @Test
    public void should_makeBalancedTreeWithSameNumberOfBlackNodesInEveryPath() {
        //given
        int size = 126;
        int numberOfBlackNodes = 1;
        boolean arePathsEqual;
        for (int i = 0; i < size; i++) {
            tree.setValue(i, "P. Czarnek " + i);
        }
        Node<Integer, String> tmp = tree.getRootNode();

        //when
        while (tmp.getLeft() != null) {
            if (tmp.getLeft().getColor().equals(Color.BLACK))
                numberOfBlackNodes++;

            tmp = tmp.getLeft();
        }

        tmp = tree.getRootNode();
        arePathsEqual = countPathsLength(tmp, 0, numberOfBlackNodes);


        //then
        System.out.println(numberOfBlackNodes);
        assertThat(arePathsEqual).isEqualTo(true);
    }

    public boolean countPathsLength(Node<?, ?> root, int currentLength, int compareLength) {
        if (root == null) {
            return currentLength == compareLength;
        }
        if (root.getColor().equals(Color.BLACK))
            currentLength++;

        countPathsLength(root.getLeft(), currentLength, compareLength);
        countPathsLength(root.getRight(), currentLength, compareLength);

        return true;
    }
}
