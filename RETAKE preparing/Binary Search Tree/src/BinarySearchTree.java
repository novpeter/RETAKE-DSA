
public class BinarySearchTree {
    public Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void display(Node root){
        if(root!=null){
            display(root.left);
            System.out.print(root.getValue() + " ");
            display(root.right);
        }
    }

    public boolean find(int value) {
        Node current = root;
        while (current != null) {
            if (current.getValue() == value) {
                return true;
            } else if (current.getValue() < value) {
                current = current.right;
            } else if (current.getValue() > value) {
                current = current.left;
            }
        }
        return false;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (value < current.getValue()) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else if (value > current.getValue()) {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public boolean delete(int value){
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while (current.getValue() != value){
            parent = current;
            if (value < current.getValue()){
                isLeftChild = true;
                current = current.left;
            }else{
                isLeftChild = false;
                current = current.right;
            }
            if (current == null){
                return false;
            }
        }
        //We have found node
        // Case 1: node has no children
        if (current.left == null && current.right == null){
            if (current == root){
                root = null;
            }
            if (isLeftChild){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        //Case 2: node has only right child
        else if (current.left == null){
            if (current == root){
                root = current.right;
            } else if (isLeftChild){
                parent.left = current.right;
            }else{
                parent.right = current.right;
            }
        }
        //Case 3: node has only left child
        else if(current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }
        //Case 4: node has left and right child
        else  if(current.left != null && current.right != null){
            Node successor = getSuccessor(current);
            if(current==root){
                root = successor;
            }else if(isLeftChild){
                parent.left = successor;
            }else{
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    public int findMax(Node root){
        Node current = root;
        while(current.right != null){
            current = current.right;
        }
        return current.getValue();
    }

    public int findMin(Node root){
        Node current = root;
        while(current.left != null){
            current = current.left;
        }
        return current.getValue();
    }

    public int findHeight(Node root) {
        if (root == null){
            return -1;
        }
        return Math.max(findHeight(root.left), findHeight(root.right))+1;
    }

    public void preorderTraversal(Node root){
        if (root == null){
            return;
        }
        System.out.println(root.getValue());
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    public void inorderTraversal(Node root){
        if (root == null){
            return;
        }
        inorderTraversal(root.left);
        System.out.println(root.getValue());
        inorderTraversal(root.right);
    }

    public void postorderTraversal(Node root){
        if (root == null){
            return;
        }
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.println(root.getValue());
    }

    private Node getSuccessor(Node deleteNode){
        Node successor = null;
        Node successorParent = null;
        Node current = deleteNode.right;
        while (current != null){
            successorParent = successor;
            successor = current;
            current = current.left;

        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
        //successorParent
        if(successor!=deleteNode.right){
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }
        return successor;
    }

}
