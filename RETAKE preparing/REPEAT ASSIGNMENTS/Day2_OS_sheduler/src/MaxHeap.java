public class MaxHeap {
    private Process[] heap;
    private int capacity;
    private int size;
    private final int FRONT = 0;

    public MaxHeap(int capacity) {
        heap = new Process[capacity];
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int parent(int position){
        return (position-1)/2;
    }

    private int leftChild(int position){
        return 2*position + 1;
    }

    private int rightChild(int position){
        return 2*position + 2;
    }

    public void insert(Process process){
        heap[size] = process;
        int current = size;
        size += 1;
        while(heap[current].PRIORITY > heap[parent(current)].PRIORITY){
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Process remove(){
        Process popped = heap[FRONT];
        heap[FRONT] = heap[size-1];
        size -= 1;
        maxHeapify(FRONT);
        return popped;
    }

    private void maxHeapify(int position){
        if(!isLeaf(position)){
            if(heap[position].PRIORITY < heap[leftChild(position)].PRIORITY || heap[position].PRIORITY < heap[rightChild(position)].PRIORITY){
                if (heap[leftChild(position)].PRIORITY > heap[rightChild(position)].PRIORITY) {
                    swap(position, leftChild(position));
                    maxHeapify(leftChild(position));
                } else {
                    swap(position, rightChild(position));
                    maxHeapify(rightChild(position));
                }
            }
        }
    }

    private void swap(int first_position, int second_position) {
        Process tmp = heap[first_position];
        heap[first_position] = heap[second_position];
        heap[second_position] = tmp;
    }

    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }

}
