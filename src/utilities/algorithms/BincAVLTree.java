package utilities.algorithms;

public class BincAVLTree {
    Node root;
    public int search(){return 0;}
    public int max(){return 0;}
    public int min(){return 0;}
    public void insert(int data){
        if(root == null) {
            root = new Node();
            root.data = data;
            root.rank = 0;
        } else {
            insert(data, root);
        }
    }

    private void insert(int data, Node parent){
        if(parent.data > data) {
            if(parent.left != null) {
                insert(data, parent.left);
            } else {
                Node node = new Node();
                node.data = data;
                node.rank = 0;
                parent.left = node;
            }
        } else {
            if(parent.right != null) {
                insert(data, parent.right);
            } else {
                Node node = new Node();
                node.data = data;
                node.rank = 0;
                parent.right = node;
            }
        }
        parent.rank = max(parent.left.rank, parent.right.rank) + 1;

        int balance = parent.left.rank - parent.right.rank;

        
    }

    private int max(int a, int b) {
        return (a >= b) ? a : b;
    }
    public void delete(int data){}

    public void printTree() {
        System.out.print(root);
    }

    class Node {
        int data;
        Node left;
        Node right;
        int rank;

        @Override
        public String toString() {
            return "data:"+data+" rank:"+rank+" left child ("+left+")"+" right child ("+right+")";
        }
    }

    public static void main(String[] args) {
        BincAVLTree obj = new BincAVLTree();
        obj.insert(3);
        obj.insert(5);
        obj.insert(6);
        obj.insert(3);
        obj.insert(8);
        obj.printTree();
    }
}
