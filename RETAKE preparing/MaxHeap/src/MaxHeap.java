import java.util.ArrayList;

public class MaxHeap {
    private int FRONT = 0;
    private ArrayList<Integer> Heap;
    private int size;

    public MaxHeap() {
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

    public void insert(int key) {
        Heap.add(key);
        int current = size;
        size++;
        while (Heap.get(current) > Heap.get(parent(current))) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int remove() {
        int popped = Heap.get(FRONT);
        Heap.set(FRONT, Heap.get(size - 1));
        size--;
        maxHeapify(FRONT);
        return popped;
    }

    private void maxHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (Heap.get(pos) < Heap.get(leftChild(pos)) || Heap.get(pos) < Heap.get(rightChild(pos))) {
                if (Heap.get(leftChild(pos)) > Heap.get(rightChild(pos))) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }

    private void swap(int first_position, int second_position) {
        int tmp = Heap.get(first_position);
        Heap.set(first_position, Heap.get(second_position));
        Heap.set(second_position, tmp);
    }

    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }

}
