import java.util.*;
public class Main {
    public Main() {
        this.root = null;
        this.size = 0;
    }
    class Node {
        public int data;
        public Node left;
        public Node right;
        public int height;
        public int Size;
        public int count;

        public Node(int data) {
            this.Size = 1;
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 0;
            this.count = 1;
        }
    }
    private Node root;
    private int size;
    public int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }
    public int getBalanceFactor(Node node) {
        if (node != null) {
            return height(node.left) - height(node.right);
        }
        return 0;
    }
    public void updateNode(Node node) {
        if (node != null) {
            node.Size = getSize(node.left) + getSize(node.right) + node.count;
            node.height = max(height(node.left), height(node.right)) + 1;
        }
    }
    public int height(Node node) {
        if (node != null) {
            return node.height;
        }
        return -1;
    }

    public int getSize(Node node) {
        if (node != null) {
            return node.Size;
        }
        return 0;
    }
    public Node gerMinNode(Node node){
        if(node.left!=null){
            return gerMinNode(node.left);
        }
        else {
            return node;
        }
    }
    public Node rightRotate(Node b){
        Node a=b.left;
        Node temp=a.right;
        a.right=b;
        b.left=temp;
        updateNode(b);
        updateNode(a);
        return a;
    }
    public Node leftRotate(Node b){
        Node a=b.right;
        Node temp=a.left;
        a.left=b;
        b.right=temp;
        updateNode(b);
        updateNode(a);
        return a;
    }

    public Node insert(Node tree, int data) {
        if(tree==null){
            size++;
            return new Node(data);
        }
        if(tree.data==data){
            tree.count++;
            updateNode(tree);
            return tree;
        }
        if(tree.data>data){
            tree.left=insert(tree.left,data);
        } else if (tree.data<data) {
            tree.right=insert(tree.right,data);
        }
        updateNode(tree);
        int bf=getBalanceFactor(tree);
        if(bf>1&&getBalanceFactor(tree.left)>0){
            return rightRotate(tree);
        }
        if(bf<-1&&getBalanceFactor(tree.right)<0){
            return leftRotate(tree);
        }
        if(bf>1&&getBalanceFactor(tree.left)<0){
            tree.left=leftRotate(tree.left);
            return rightRotate(tree);
        }
        if(bf<-1&&getBalanceFactor(tree.right)>0){
            tree.right=rightRotate(tree.right);
            return leftRotate(tree);
        }
        return tree;
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    public Node remove(Node tree, int data) {
        if(tree==null){
            return null;
        }
        Node newRoot=null;
        if(tree.data>data){
            tree.left=remove(tree.left,data);
            newRoot=tree;
        }else if(tree.data<data){
            tree.right=remove(tree.right,data);
            newRoot=tree;
        }else {
            if(tree.count>1){
                tree.count--;
                updateNode(tree);
                return tree;
            }
            if(tree.left==null){
                Node right=tree.right;
                tree.right=null;
                size--;
                newRoot=right;
            }else if(tree.right==null){
                Node left=tree.left;
                tree.left=null;
                size--;
                newRoot=left;
            }else {
                Node minNode=gerMinNode(tree.right);
                minNode.right=remove(tree.right,minNode.data);
                minNode.left=tree.left;
                tree.left=tree.right=null;
                newRoot=minNode;
            }
        }
        if(newRoot==null){
            return null;
        }
        updateNode(newRoot);
        int bf=getBalanceFactor(newRoot);

        if(bf > 1&&getBalanceFactor(newRoot.left)>=0){
            return rightRotate(newRoot);
        }
        if(bf < -1&&getBalanceFactor(newRoot.right)<=0){
            return leftRotate(newRoot);
        }
        if(bf > 1&&getBalanceFactor(newRoot.left)<0){
            newRoot.left=leftRotate(newRoot.left);
            return rightRotate(newRoot);
        }
        if(bf<-1&&getBalanceFactor(newRoot.right)>0){
            newRoot.right=rightRotate(newRoot.right);
            return leftRotate(newRoot);
        }
        return newRoot;
    }

    public void remove(int data) {
        root=remove(root,data);
    }
    public Node findClosest(Node node, int value) {
        Node current = node;
        Node closest = null;
        int minDiff = Integer.MAX_VALUE;

        while (current != null) {
            int diff = Math.abs(current.data - value);

            if (diff < minDiff || (diff == minDiff && current.data < (closest != null ? closest.data : Integer.MAX_VALUE))) {
                minDiff = diff;
                closest = current;
            }

            if (current.data > value) {
                current = current.left;
            } else if (current.data < value) {
                current = current.right;
            } else {
                break;
            }
        }

        return closest;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Main tree1 = new Main();
        Main tree2 = new Main();
        int n = input.nextInt();
        long result=0;
        for(int i=0;i<n;i++){
            int first=input.nextInt();
            int data=input.nextInt();
            if(first==0){
                tree1.insert(data);
                if(tree2.size!=0){
                   Node closest=tree2.findClosest(tree2.root,data);
                   result+=Math.abs(closest.data-data);
                     tree2.remove(closest.data);
                     tree1.remove(data);
                }
             }
            else if(first==1){
               tree2.insert(data);
                if(tree1.size!=0){
                     Node closest=tree1.findClosest(tree1.root,data);
                     result+=Math.abs(closest.data-data);
                     tree1.remove(closest.data);
                     tree2.remove(data);
                }
            }
    }
        System.out.println(result);
    }
}
