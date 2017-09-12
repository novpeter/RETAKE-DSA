public class AVLTree {
    public Node root;
    public int resultIndex;
    public boolean equalKey;

    public Node insert(Node node, double key, int index){
        if (node == null){
            return new Node(key, index);
        }
        if (key > node.key){
            node.right = insert(node.right, key, index);
        }else if (key < node.key){
            node.left = insert(node.left, key, index);
        }else{
            //we found equal node
            resultIndex = node.index;
            equalKey = true;
            return node;
        }
        //Update height oh the ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        //Get balance to determine whether there need rotations
        int balance = getBalance(node);
        //Left left case
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

    /** A utility function to right rotate subtree rooted with y
        See the diagram given above.*/
    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        // Perform rotation
        x.right = y;
        y.left = T2;
        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        // Return new root
        return x;
    }

    /** A utility function to left rotate subtree rooted with x
        See the diagram given above.**/
    public Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    /**
     * This method deletes given node from the tree
     */
    public Node deleteNode(Node root, double key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;
        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {

            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else   // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            } else {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.key = temp.key;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalance(Node node){
        if (node == null){
            return 0;
        }
        return height(node.left)-height(node.right);
    }

}
