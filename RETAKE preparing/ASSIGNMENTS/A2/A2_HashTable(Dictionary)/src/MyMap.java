import java.util.ArrayList;

/**
 * Created by Petr on 23.02.2017.
 */
public class MyMap<K, V> {
    private static Entry[] table;
    private static int size = 256;
    private static int numEntries = 0;

    /**
     * Constructor for object of MyMap class.
     */
    protected MyMap(){
        table = new Entry[size];
        for (int i = 0; i<size ; i++){
            table[i] = null;
        }
    }

    /**
     * This method returns hashcode for certain object.
     * @param k - object.
     * @return hashcode
     */
    protected  int  hashcode(K k){
        return Math.abs(k.hashCode() % size);
    }

    /**
     * This method puts the given  object in HashMap.
     * @param key
     */
    protected void put(K key){
        int i = 0;
        int hash = hashcode(key);
        if (table[hash] != null && table[hash].getKey().equals(key)){
            table[hash].setValue((int)table[hash].getValue() + 1);
        }else {
            if (table[hash] == null) {
                table[hash] = new Entry(key, 1);
            } else {
                while (true) {
                    i++;
                    if (table[hash + i * i] == null || table[hash + i * i].getKey().equals("")) {
                        table[hash + i * i] = new Entry(key, 1);
                        break;
                    }
                }
            }
        }
        numEntries++;
    }

    /**
     * This method removes entry with certain key.
     * @param key
     */
    protected void remove(K key){
        for (int i = 0; i<size; i++){
            if (table[i] != null && table[i].getKey().equals(key)){
                table[i].setKey("");
                table[i].setValue(-1);
                numEntries--;
                break;
            }
        }
    }

    /**
     * This method returns the set of entries.
     * @return entries
     */
    protected ArrayList<Entry> entrySet(){
        ArrayList<Entry> entries = new ArrayList<>();
        for (Entry e: table) {
            if(e != null){
                entries.add(e);
            }
        }
        return entries;
    }

}
