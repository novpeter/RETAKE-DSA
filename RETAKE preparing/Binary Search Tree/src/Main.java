public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(8);
        tree.insert(6);
        tree.insert(4);
        tree.insert(7);
        tree.insert(12);
        tree.insert(11);
        tree.insert(15);
        tree.insert(14);
        tree.insert(16);
        tree.insert(9);
        tree.insert(13);

        System.out.println("Root: " + Integer.toString(tree.root.getValue()));
        System.out.println("Original Tree : ");
        tree.display(tree.root);
        System.out.println("\n" + "Check whether Node with value 8 exists : " + tree.find(8));
        System.out.println("Min : " + tree.findMin(tree.root));
        System.out.println("Max : " + tree.findMax(tree.root));
        tree.delete(1);
        tree.delete(30);
        System.out.println("Min : " + tree.findMin(tree.root));
        System.out.println("Max : " + tree.findMax(tree.root));
        System.out.println("Height: " + tree.findHeight(tree.root));
        tree.preorderTraversal(tree.root);
//        tree.inorderTraversal(tree.root);
//        tree.postorderTraversal(tree.root);
    }
}
