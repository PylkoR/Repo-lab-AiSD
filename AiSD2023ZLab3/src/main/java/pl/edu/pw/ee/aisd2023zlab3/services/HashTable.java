package pl.edu.pw.ee.aisd2023zlab3.services;

public interface HashTable<T extends Comparable<T>> {

    String put(T newElem);

    T get(T elem);

    void delete(T elem);

    int findElemId(T elem1);
}
