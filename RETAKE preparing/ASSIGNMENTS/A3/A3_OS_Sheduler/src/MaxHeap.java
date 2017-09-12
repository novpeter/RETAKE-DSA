/**
 * Created by Petr on 23.03.2017.
 */
public class MaxHeap {
    private int capacity; //maximum number of processes
    private int numProcesses = 0; //number of processes
    private static Process[] heap; //binary-max-heap

    /**
     *  This is conctructor for object of the MaxHeap class
     * @param capacity - maximum number of elements in heap
     */
    protected MaxHeap(int capacity) {
        heap = new Process[capacity + 1];
    }

    /**
     * This method returns is there any elements in heap
     * @return boolean value
     */
    protected boolean isEmpty() {
        return (numProcesses == 0);
    }

    /**
     * This method returns position of its parent
     * @param position of current node
     * @return parent's position
     */
    private static int parent(int position) {
        return position / 2;
    }

    /**
     * This method returns position of its leftChild
     * @param position of current node
     * @return leftChild's position
     */
    private static int leftChild(int position) {
        return (2 * position);
    }

    /**
     * This method returns position of its rightChild
     * @param position of current node
     * @return rightChild's position
     */
    private static int rightChild(int position) {
        return (2 * position) + 1;
    }

    /**
     * This method return boolean value (Is this node is leaf?)
     * @param position of current node
     * @return
     */
    private boolean isLeaf(int position) {
        if (position >= (numProcesses / 2) && position <= numProcesses) {
            return true;
        }
        return false;
    }

    /**
     * This method inserts element in heap.
     * @param process - new element
     */
    protected void insert(Process process) {
        numProcesses++;
        heap[numProcesses] = process;
        int newIndex = numProcesses;

        if (numProcesses > 1) {
            while (newIndex != 1 && !(heap[newIndex].getPriority() <= heap[parent(newIndex)].getPriority())) {
                swap(newIndex, parent(newIndex));
                newIndex = parent(newIndex);
            }
        }
    }

    /**
     * This method returns the max element from heap
     * @return process with max priority
     */
    protected Process getMax() {
        Process popped = heap[1];
        heap[1] = heap[numProcesses];
        numProcesses--;
        if (numProcesses > 1) {
            maxHeapify(1);
        }
        return popped;
    }

    /**
     * This method restructures the tree to observe all the properties of a binary maxHeap
     * @param i
     */
    private void maxHeapify(int i) {
        if (!isLeaf(i)) {
            if (heap[i].getPriority() < heap[leftChild(i)].getPriority() || heap[i].getPriority() < heap[rightChild(i)].getPriority()) {
                if (heap[leftChild(i)].getPriority() > heap[rightChild(i)].getPriority()) {
                    swap(i, leftChild(i));
                    maxHeapify(leftChild(i));
                } else {
                    swap(i, rightChild(i));
                    maxHeapify(rightChild(i));
                }
            }
        }
    }

    /**
     * This method swaps two elements in heap
     * @param pos - first element
     * @param parent - second element
     */
    private static void swap(int pos, int parent) {
        Process e = heap[pos];
        heap[pos] = heap[parent];
        heap[parent] = e;
    }

}
