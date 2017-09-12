public class Node {
    public Node right, left, parent;
    public double key;
    public int index, height;

    public Node(double key, int index) {
        left = right = parent = null;
        height = 1;
        this.key = key;
        this.index = index;
    }

}