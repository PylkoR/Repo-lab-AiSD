package pl.edu.pw.ee.aisd2023zlab3;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAddressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        int n = m - 2;

        if (n < 3) {
            n = 1;
        }

        key = key & Integer.MAX_VALUE;

        int hash = (key % m + i * (1 + key % n)) % m;

        return hash;
    }

}
