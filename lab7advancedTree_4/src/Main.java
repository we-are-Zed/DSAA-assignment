import java.util.*;
public class Main {
    private Node root;
    private int size;

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

    public void updateNode(Node node) {
        if (node != null) {
            node.Size = getSize(node.left) + getSize(node.right) + node.count;
            node.height = max(height(node.left), height(node.right)) + 1;
        }
    }

    public int getSize(Node node) {
        if (node != null) {
            return node.Size;
        }
        return 0;
    }

    public int findKthLargest(int k) {
        return findKthLargest(root, k);
    }

    private int findKthLargest(Node node, int k) {
        if(node==null||k<1||k>node.Size){
            return -1;
        }
        if(k<getSize(node.left)+1){
            return findKthLargest(node.left,k);
        }else if(k>getSize(node.left)+node.count){
            return findKthLargest(node.right,k-getSize(node.left)-node.count);
        }
        return node.data;
    }


    public Main() {
        this.root = null;
        this.size = 0;
    }
    public int getBalanceFactor(Node node) {
        if (node != null) {
            return height(node.left) - height(node.right);
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

    public int height(Node node) {
        if (node != null) {
            return node.height;
        }
        return -1;
    }

    public int getHeight() {
        return height(root);
    }

    public int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    public Node search(Node x, int data) {
        if (x == null) {
            return x;
        }
        if (data < x.data) {
            return search(x.left, data);
        } else if (data > x.data) {
            return search(x.right, data);
        } else {
            return x;
        }
    }

    public Node search(int data) {
        return search(root, data);
    }


    public Node minNum(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int minNum() {
        Node node = minNum(root);
        if (node != null) {
            return node.data;
        }
        return -1;
    }

    public Node maxNum(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public int maxNum() {
        Node node = maxNum(root);
        if (node != null) {
            return node.data;
        }
        return -1;
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Main tree = new Main();
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[100000 + 5];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for(int i=0;i<k;i++){
            tree.insert(arr[i]);
        }
        for(int i=0;i<n-k+1;i++){
            int kth= in.nextInt();
            System.out.println(tree.findKthLargest(kth));
            tree.remove(arr[i]);
            if(i!=n-k){
                tree.insert(arr[i+k]);
            }
        }
    }
}
