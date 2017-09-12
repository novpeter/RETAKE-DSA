/**
 * Created by Petr on 01.02.2017.
 */
public class LinkedStack<E> extends LinkedList {
    private LinkedList list = new LinkedList();

    public LinkedStack() {
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public E pop() {
        return (E) list.removeFirst();
    }

    public void push(E e) {
        list.addFirst(e);
    }

    public E top() {
        return (E) list.first();

    }

}
