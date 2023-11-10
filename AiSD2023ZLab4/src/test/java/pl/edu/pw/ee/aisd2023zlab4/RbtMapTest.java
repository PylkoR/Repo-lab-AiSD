package pl.edu.pw.ee.aisd2023zlab4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class RbtMapTest {

    private RbtMap<Integer, String> tree;
    private boolean sameNumberOfBlackNodes;
    private boolean noRedRightSons;
    private boolean noTwoRedSons;

    @Before
    public void setup() {
        tree = new RbtMap<>();
        sameNumberOfBlackNodes = true;
        noRedRightSons = true;
        noTwoRedSons = true;
    }

    @Test
    public void should_CorrectlyPutBigNumberOfNodesAndGetOne() {
        //given
        int size = 10_000;
        int tryValue = 7452;
        String tryString = "node " + tryValue;

        //when
        for (int i = 0; i < size; i++) {
            tree.setValue(i, "node " + i);
        }
        String elem = tree.getValue(tryValue);
        checkThatAllNodesDoNotHaveTwoRedSons(tree.getRootNode());
        checkThatAllNodesDoNotHaveRedRightSon(tree.getRootNode());

        //then
        assertThat(elem).isEqualTo(tryString);
        assertThat(tree.getRootNode().getColor()).isEqualTo(Color.BLACK);
        assertThat(noTwoRedSons).isEqualTo(true);
        assertThat(noRedRightSons).isEqualTo(true);
    }

    @Test
    public void should_makeBalancedTreeWithSameNumberOfBlackNodesInEveryPath() {
        //given
        int size = 173;

        for (int i = 0; i < size; i++) {
            tree.setValue(i, "P. Czarnek " + i);
        }

        //when
        compareNumberOfBlackNodesInEveryPath(tree.getRootNode(), 0, countLengthOfLeftBranch(tree.getRootNode()));

        //then
        assertThat(sameNumberOfBlackNodes).isEqualTo(true);
    }

    @Test
    public void should_correctlyPutOneNodeAsARoot() {
        //given
        String value1 = "Ala";

        //when
        tree.setValue(1, value1);

        String elem1 = tree.getValue(1);
        String rootValue = tree.getRootNode().getValue();
        Integer key = tree.getRootNode().getKey();

        //then
        assertThat(elem1).isEqualTo(value1);
        assertThat(rootValue).isEqualTo(value1);
        assertThat(key).isEqualTo(1);
    }

    @Test
    public void should_correctlyPutAndGetSomeValues() {
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

    @Test
    public void should_CorrectlyPutNodeWithKeyThatAlreadyIsInTree() {
        //given
        int size = 255;
        String swap = "P. Czarnek";
        int keyToSwap = 177;

        for (int i = 0; i < size; i++) {
            tree.setValue(i, "Node " + i);
        }

        //when
        tree.setValue(keyToSwap, swap);

        String afterSwap = tree.getValue(keyToSwap);

        //then
        assertThat(afterSwap).isEqualTo(swap);
        assertThat(tree.getRootNode().getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    public void should_AddSmallerNodeOnTheLeftSide() {
        //given
        String root = "root";
        String leftSon = "leftSon";
        int bigger = 5;
        int smaller = bigger - 1;

        //when
        tree.setValue(bigger, root);
        tree.setValue(smaller, leftSon);

        String shouldBeLeftSon = tree.getRootNode().getLeft().getValue();
        //then
        assertThat(shouldBeLeftSon).isEqualTo(leftSon);
    }

    @Test
    public void should_correctlyPutBiggerNodesAndMakeLeftRotation(){
        //given
        String root = "root";
        String rightSon = "rightSon";
        int bigger = 5;
        int smaller = bigger - 1;

        //when
        tree.setValue(smaller, root);
        Integer rootBeforeSet = tree.getRootNode().getKey();

        tree.setValue(bigger, rightSon);
        Integer rootAfterSet = tree.getRootNode().getKey();
        Integer leftSonAfterSet = tree.getRootNode().getLeft().getKey();

        //then
        assertThat(rootBeforeSet).isEqualTo(smaller);
        assertThat(rootAfterSet).isEqualTo(bigger);
        assertThat(leftSonAfterSet).isEqualTo(smaller);
        assertThat(tree.getRootNode().getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    public void should_correctlyPutSmallerNodesAndMakeRightRotation(){
        //given
        String root = "root";
        String leftSon = "leftSon";
        String leftGrandSon = "leftGrandSon";
        int bigger = 5;
        int smaller = bigger - 1;
        int smallest = smaller - 1;

        //when
        tree.setValue(bigger, root);

        tree.setValue(smaller, leftSon);
        tree.setValue(smallest, leftGrandSon);
        Integer rootAfterSet = tree.getRootNode().getKey();
        Integer leftSonAfterSet = tree.getRootNode().getLeft().getKey();
        Integer rightSonAfterSet = tree.getRootNode().getRight().getKey();

        //then
        assertThat(rootAfterSet).isEqualTo(smaller);
        assertThat(leftSonAfterSet).isEqualTo(smallest);
        assertThat(rightSonAfterSet).isEqualTo(bigger);
        assertThat(tree.getRootNode().getColor()).isEqualTo(Color.BLACK);
    }

    private void checkThatAllNodesDoNotHaveRedRightSon(Node<?, ?> node){
        if (node != null) {
            if (node.getRight() != null && node.getRight().isRed()) {
                    noRedRightSons = false;
            }

            checkThatAllNodesDoNotHaveTwoRedSons(node.getLeft());
            checkThatAllNodesDoNotHaveTwoRedSons(node.getRight());
        }
    }

    private void checkThatAllNodesDoNotHaveTwoRedSons(Node<?,?> node){
        if (node != null) {
            if (node.getLeft() != null && node.getRight() != null) {
                if (node.getLeft().isRed() && node.getRight().isRed()){
                    noTwoRedSons = false;
                }
            }

            checkThatAllNodesDoNotHaveTwoRedSons(node.getLeft());
            checkThatAllNodesDoNotHaveTwoRedSons(node.getRight());
        }
    }

    private void compareNumberOfBlackNodesInEveryPath(Node<?, ?> root, int currentLength, int compareLength) {
        if (root == null) {
            if (currentLength != compareLength) {
                sameNumberOfBlackNodes = false;
            }
        } else if (root.getColor().equals(Color.BLACK)) {
            currentLength++;

            compareNumberOfBlackNodesInEveryPath(root.getLeft(), currentLength, compareLength);
            compareNumberOfBlackNodesInEveryPath(root.getRight(), currentLength, compareLength);
        }
    }

    private int countLengthOfLeftBranch(Node<?, ?> root) {
        int numberOfBlackNodes = 0;

        while (root != null) {
            if (root.getColor().equals(Color.BLACK)) {
                numberOfBlackNodes++;
            }
            root = root.getLeft();
        }

        return numberOfBlackNodes;
    }
}
