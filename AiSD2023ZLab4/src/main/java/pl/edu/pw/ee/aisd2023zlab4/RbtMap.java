package pl.edu.pw.ee.aisd2023zlab4;

import pl.edu.pw.ee.aisd2023zlab4.services.MapInterface;

public class RbtMap<K extends Comparable<K>, V> implements MapInterface<K, V> {

    private final RedBlackTree<K, V> tree;

    public <K, V> RbtMap() {
        tree = new RedBlackTree<>();
    }

    @Override
    public void setValue(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Params (key, value) cannot be null.");
        }
        tree.put(key, value);
    }

    @Override
    public V getValue(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot get value by null key.");
        }
        return tree.get(key);
    }

    public Node<K, V> getRootNode() {
        return tree.getRoot();
    }

    @Override
    public void deleteMax(){
        tree.deleteMax();
    }
}
