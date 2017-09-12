import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("The Max Heap is ");
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(1);
        maxHeap.insert(2);
        maxHeap.insert(3);
        maxHeap.insert(4);
        maxHeap.insert(5);
        System.out.println("The max val is " + maxHeap.remove());
        System.out.println("The max val is " + maxHeap.remove());
    }
}
