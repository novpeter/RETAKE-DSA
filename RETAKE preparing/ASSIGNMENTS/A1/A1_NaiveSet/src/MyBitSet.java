import java.util.Arrays;

/**
 * Created by Petr on 02.02.2017.
 */
public class MyBitSet {
    public long[] set = new long[1];

    /**
     * extend the array "set", if it doesn't have enough size
     * @param size -
     */
    private void  extendArray(int size){
        long[] newSet = new long[size];
        set = copyElements(newSet);
    }

    /**
     * Copy elements from old set to new.
     * @param newSet - where it copies elements from old set.
     * @return newSet.
     */
    private long[] copyElements(long[] newSet) {
        for (int i = 0; i < set.length; i++) {
            newSet[i] = set[i];
        }
        return newSet;
    }

    /**
     * Add the given number to the set. Makes "1" the given number of bit in bitset.
     * @param number - index of needed bit.
     */
    public void addElement(int number){
        if (number/64 >= set.length){
            extendArray(number/64+1);
        }
        long adder = 1 << (number%64);
        set[number/64] = set[number/64] | adder;
    }

    /**
     * Checks whether the given number is in the set
     * @param number - index of needed bit
     * @return true, if number is in the set.
     */
    public boolean hasElement(int number) {
        if (number / 64 < set.length) {
            return (((set[number / 64]) & (1 << (number % 64))) == (1 << (number % 64)));
        }
        return false;
    }
}