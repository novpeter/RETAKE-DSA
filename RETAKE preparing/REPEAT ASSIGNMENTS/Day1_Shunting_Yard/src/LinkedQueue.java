import java.util.LinkedList;

public class LinkedQueue<E> extends LinkedList {
    private LinkedList list = new LinkedList();

    public LinkedQueue() {
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public E first() {
        return (E) list.getFirst();
    }

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E dequeue() {
        return (E) list.removeFirst();
    }
}