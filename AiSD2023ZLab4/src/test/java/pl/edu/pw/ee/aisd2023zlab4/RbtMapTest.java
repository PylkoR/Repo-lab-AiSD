package pl.edu.pw.ee.aisd2023zlab4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class RbtMapTest {

    private RbtMap<Integer, String> tree;
    private boolean sameNumberOfBlackNodes;

    @Before
    public void setup() {
        tree = new RbtMap<>();
        sameNumberOfBlackNodes = true;
    }

    @Test
    public void should_makeBalancedTreeWithSameNumberOfBlackNodesInEveryPath() {
        //given
        int size = 173;
        int numberOfBlackNodes = 1;

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
        countPathsLength(tmp, 0, numberOfBlackNodes);


        //then
        System.out.println(numberOfBlackNodes);
        assertThat(sameNumberOfBlackNodes).isEqualTo(true);
    }

    @Test
    public void should_correctlyPutAndGetSomeValues(){
        //given
        String value1 = "Ala";
        String value2 = "Ola";
        String value3 = "Ela";
        String value4 = "Bla";

        //when
        tree.setValue(1, value1);
        tree.setValue(3, value2);
        tree.setValue(6, value3);
        tree.setValue(2, value4);

        String elem1 = tree.getValue(1);
        String elem2 = tree.getValue(3);
        String elem3 = tree.getValue(6);
        String elem4 = tree.getValue(2);

        //then
        assertThat(elem1).isEqualTo(value1);
        assertThat(elem2).isEqualTo(value2);
        assertThat(elem3).isEqualTo(value3);
        assertThat(elem4).isEqualTo(value4);
    }

    public void countPathsLength(Node<?, ?> root, int currentLength, int compareLength) {
        if (root == null) {
            if (currentLength != compareLength)
                sameNumberOfBlackNodes = false;
        }
        else if (root.getColor().equals(Color.BLACK))
            currentLength++;

        if (root != null) {
            countPathsLength(root.getLeft(), currentLength, compareLength);
            countPathsLength(root.getRight(), currentLength, compareLength);
        }
    }
}
