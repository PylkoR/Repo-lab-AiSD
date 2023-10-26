package pl.edu.pw.ee.aisd2023zlab2;

public class HashListChainingMultiplicativeHashing<T extends Comparable<T>> extends HashListChaining<T> {

    public HashListChainingMultiplicativeHashing() {
        super();
    }

    public HashListChainingMultiplicativeHashing(int size) {
        super(size);
    }

    @Override
    int countHashId(T value) {
        int hashCode = value.hashCode();
        double a = (Math.sqrt(5) - 1) / 2;

        return (int) (((hashCode & Integer.MAX_VALUE) * a) % 1 * size);
    }
}
