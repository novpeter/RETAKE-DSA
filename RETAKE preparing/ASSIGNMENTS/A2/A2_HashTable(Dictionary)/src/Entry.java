/**
 * Created by Petr on 25.02.2017.
 */
public class Entry <K,V>{
    private K k;
    private V v;

    public Entry(K key, V value){
        k = key;
        v= value;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }

    public void setValue(V value) {
        v = value;
    }

    public void setKey(K key){
        k = key;
    }

}
