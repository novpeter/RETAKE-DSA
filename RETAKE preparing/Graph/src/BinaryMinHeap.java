import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data structure to support following operations
 * extractMin - O(logn)
 * addToHeap - O(logn)
 * containsKey - O(1)
 * decreaseKey - O(logn)
 * getKeyWeight - O(1)
 *
 * It is a combination of binary heap and hash map
 *
 */
public class BinaryMinHeap<E> {
    private int FRONT = 0;
    private HashMap<Vertex, Entry> entryMap;
    private ArrayList<Entry> Heap;
    private int size;

    public BinaryMinHeap() {
        entryMap = new HashMap<>();
        Heap = new ArrayList<>();
        size = 0;
    }

    private int parent(int position) {
        return (position - 1) / 2;
    }

    private int leftChild(int position) {
        return 2 * position + 1;
    }

    private int rightChild(int position) {
        return 2 * position + 2;
    }

    public void insert(Entry e) {
        Heap.add(e);
        entryMap.put(e.vertex, e);
        int current = size;
        size=+1;
        if (current > 0) {
            while (Heap.get(current).weight < Heap.get(parent(current)).weight) {
                swap(current, parent(current));
                current = parent(current);
            }
        }
    }

    public Entry remove() {
        Entry popped = Heap.get(FRONT);
        entryMap.remove(popped.vertex);
        Heap.set(FRONT, Heap.get(size - 1));
        size=-1;
        maxHeapify(FRONT);
        return popped;
    }

    private void maxHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (Heap.get(pos).weight > Heap.get(leftChild(pos)).weight || Heap.get(pos).weight > Heap.get(rightChild(pos)).weight) {
                if (Heap.get(leftChild(pos)).weight < Heap.get(rightChild(pos)).weight) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }

    public Entry updateWeight(Vertex vertex, int newWeight){
        Entry entry = entryMap.get(vertex);
        Heap.remove(entry);
        entry.weight = newWeight;
        insert(entry);
        return entry;
    }

    private void swap(int first_position, int second_position) {
        Entry tmp = Heap.get(first_position);
        Heap.set(first_position, Heap.get(second_position));
        Heap.set(second_position, tmp);
    }

    public boolean isEmpty(){ return size == 0;}

    public boolean contains(Vertex v){
        return entryMap.containsKey(v);
    }

    public int getWeight(Vertex vertex){
        return entryMap.get(vertex).weight;
    }

    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }
}