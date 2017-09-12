public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 4);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 8);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, 13);
        tree.root = tree.insert(tree.root, 15);
        tree.root = tree.insert(tree.root, 14);
        tree.root = tree.insert(tree.root, 16);
        tree.root = tree.insert(tree.root, 3);
        System.out.println("");
    }
}
