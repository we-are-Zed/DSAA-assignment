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

    public Node iterativeSearch(Node x, int data) {
        while (x != null) {
            int temp = data - x.data;
            if (temp < 0) {
                x = x.left;
            } else if (temp > 0) {
                x = x.right;
            } else {
                return x;
            }
        }
        return x;
    }

    public Node iterativeSearch(int data) {
        return iterativeSearch(root, data);
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

    public Node leftLeftRotation(Node k2) {
        Node k1;
        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        updateNode(k2);
        updateNode(k1);
        return k1;
    }

    public Node rightRightRotation(Node k1) {
        Node k2;
        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        updateNode(k1);
        updateNode(k2);
        return k2;
    }

    public Node leftRightRotation(Node k3) {
        k3.left = rightRightRotation(k3.left);
        return leftLeftRotation(k3);
    }

    public Node rightLeftRotation(Node k1) {
        k1.right = leftLeftRotation(k1.right);
        return rightRightRotation(k1);
    }

    public Node insert(Node tree, int data) {
        if (tree == null) {
            tree = new Node(data);
            size++;
            if(tree==null){
                System.out.println("ERROR: create avl tree node failed!");
                return null;
            }
        } else {
            int temp = data - tree.data;
            if (temp < 0) {
                tree.left = insert(tree.left, data);
                if (height(tree.left) - height(tree.right) == 2) {
                    if (data < tree.left.data) {
                        tree = leftLeftRotation(tree);
                    } else {
                        tree = leftRightRotation(tree);
                    }
                }
            } else if (temp > 0) {
                tree.right = insert(tree.right, data);
                if (height(tree.right) - height(tree.left) == 2) {
                    if (data > tree.right.data) {
                        tree = rightRightRotation(tree);
                    } else {
                        tree = rightLeftRotation(tree);
                    }
                }
            } else {
                tree.count++;
                size++;
            }
        }
        updateNode(tree);
        return tree;
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    public Node remove(Node tree, Node z) {
        if (tree == null || z == null) {
            return null;
        }
        int temp = z.data - tree.data;
        if (temp < 0) {
            tree.left = remove(tree.left, z);
            if (height(tree.right) - height(tree.left) == 2) {
                Node r = tree.right;
                if (height(r.left) > height(r.right)) {
                    tree = rightLeftRotation(tree);
                } else {
                    tree = rightRightRotation(tree);
                }
            }
        } else if (temp > 0) {
            tree.right = remove(tree.right, z);
            if (height(tree.left) - height(tree.right) == 2) {
                Node l = tree.left;
                if (height(l.right) > height(l.left)) {
                    tree = leftRightRotation(tree);
                } else {
                    tree = leftLeftRotation(tree);
                }
            }
        } else {
            if (tree.count > 1) {
                tree.count--;
                size--;
                updateNode(tree);
                return tree;
            } else {
                if (tree.left != null && tree.right != null) {
                    if (height(tree.left) > height(tree.right)) {
                        Node max = minNum(tree.left);
                        tree.data = max.data;
                        tree.left = remove(tree.left, max);
                    } else {
                        Node min = minNum(tree.right);
                        tree.data = min.data;
                        tree.right = remove(tree.right, min);
                    }
                } else {
                    Node tem = tree;
                    tree = (tree.left != null) ? tree.left : tree.right;
                    tem = null;
                }
            }
        }
        updateNode(tree);
        return tree;
    }

    public void remove(int data) {
        Node z = search(root, data);
        if (z != null) {
            root = remove(root, z);
        }
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
