/**
 * Created by Petr on 22.03.2017.
 */
public class AVLTree {
    protected boolean equalKey;
    protected int resultIndex;
    protected Node root;


    protected Node insert(Node node, double key, int index) {

        //INSERTION
        if (node == null)
            return (new Node(key, index));
        if (key < node.key)
            node.left = insert(node.left, key, index);
        else if (key > node.key)
            node.right = insert(node.right, key, index);
        else // If key was already inserted, we need to store the index
        {
            resultIndex = node.index;
            equalKey = true;
            return node;
        }


        // UPDATE THE HEIGHT OF THE ANCECTOR NODE
        node.height = 1 + max(height(node.left),
                height(node.right));


        int balance = getBalance(node);

        //BALANCED THIS NODE IF IT BECAME UNBALANCED

        //Left case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /**
     * This method return height of given node
     * @param N - given node
     * @return height
     */
    private int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    /**
     * This method returns maximum from 2 numbers.
     * @param a - first number
     * @param b - second number
     * @return
     */
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * This method does right rotate to set the balance.
     * @param y - node
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    /**
     * This method does the left rotate to set the balance.
     * @param x - node
     * @return
     */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    /**
     * This method returns the difference between left and right subtree.
     * @param N - node
     * @return difference
     */
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }
}
