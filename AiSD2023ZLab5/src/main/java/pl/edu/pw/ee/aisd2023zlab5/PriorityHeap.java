package pl.edu.pw.ee.aisd2023zlab5;

public class PriorityHeap<T extends Comparable<T>> {
    private final T[] letters;
    private int lastId = -1;

    public PriorityHeap(int size) {
        this.letters = createTable(size);
    }

    private T[] createTable(int size) {
        return (T[]) new Comparable[size];
    }

    private T[] getArray() {
        return letters;
    }

    public int getLastNodeId() {
        return lastId;
    }

    private void heapDown() {
        int parentId = 0;
        int leftChildId = 1;
        int rightChildId = 2;
        int lowestQuantityId = parentId;

        while (true) {
            if (leftChildId <= lastId && letters[leftChildId].compareTo(letters[lowestQuantityId]) < 0) {
                lowestQuantityId = leftChildId;
            }

            if (rightChildId <= lastId && letters[rightChildId].compareTo(letters[lowestQuantityId]) < 0) {
                lowestQuantityId = rightChildId;
            }

            if (lowestQuantityId != parentId) {
                swap(parentId, lowestQuantityId);
            } else break;

            parentId = lowestQuantityId;
            leftChildId = 2 * parentId + 1;
            rightChildId = 2 * parentId + 2;
        }
    }

    private void heapUp() {
        int i = lastId;
        int parent;
        if (i % 2 == 0) {
            parent = i / 2 - 1;
        } else {
            parent = i / 2;
        }

        while ((i >= 1) && letters[parent].compareTo(letters[i]) > 0) {
            swap(parent, i);
            i = parent;
            if (i % 2 == 0) {
                parent = i / 2 - 1;
            } else {
                parent = i / 2;
            }
        }

    }

    private void swap(int firstId, int secondId) {
        if (firstId != secondId) {

            T firstNode = letters[firstId];
            letters[firstId] = letters[secondId];
            letters[secondId] = firstNode;
        }
    }

    public T extractMin() {
        if (lastId < 0) {
            throw new RuntimeException("Kopiec jest już pusty!");
        }
        T min = letters[0];
        letters[0] = letters[lastId];
        lastId--;
        heapDown();

        return min;
    }

    public void insertToHeap(T newNode) {
        lastId++;
        if (lastId >= letters.length) {
            throw new RuntimeException("Kopiec jest już pełny!");
        }

        letters[lastId] = newNode;
        heapUp();
    }
}
