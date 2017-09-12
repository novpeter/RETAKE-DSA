/**
 * Created by Petr on 22.03.2017.
 */
public class Node {
    public Node right;
    public Node left;
    public Node parent;
    public double key;
    public int index;
    public int height;

    public Node(double key, int index) {
        left = right = parent = null;
        height = 1;
        this.key = key;
        this.index = index;
    }

}

