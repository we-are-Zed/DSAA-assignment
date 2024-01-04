import java.util.*;

public class problem1 {
    static class Node {
        int value;
        int sum=1;
        ArrayList<Node> children;
        Node left;
        Node right;
        Node(int value) {
            this.left=null;
            this.right=null;
            this.value = value;
            this.children = new ArrayList<>();
        }
    }
    static Node[] g;
    static boolean[] hasParent;

    static boolean isMinHeap(Node node) {
        if (node == null) return true;
        for (Node child : node.children) {
            if (child.value < node.value || !isMinHeap(child))
                return false;
        }
        return true;
    }
    static boolean isMaxHeap(Node node) {
        if (node == null) return true;
        for (Node child : node.children) {
            if (child.value > node.value || !isMaxHeap(child))
                return false;
        }
        return true;
    }
    static boolean isBST(Node node, int sum, int n) {
        if (node == null) return true;

        if (sum > n) return false;

        if (node.children.size() > 2) {
            return false;
        }
        node.sum = sum;
        return isBST(node.left, 2 * sum, n) && isBST(node.right, 2 * sum + 1, n);
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            g = new Node[n + 1];
            hasParent = new boolean[n + 1];
            for (int j = 1; j <= n; j++) {
                int value = input.nextInt();
                g[j] = new Node(value);
            }
            for (int j = 0; j < n - 1; j++) {
                int x = input.nextInt();
                int y = input.nextInt();
                g[x].children.add(g[y]);
                if(g[x].left==null) g[x].left=g[y];
                else g[x].right=g[y];
                hasParent[y] = true;
            }
            Node root = null;
            for (int j = 1; j <= n; j++) {
                if (!hasParent[j]) {
                    root = g[j];
                    break;
                }
            }
            if(isBST(root,1,n)&&(isMinHeap(root)||isMaxHeap(root))) System.out.println("Case #"+(i+1)+": YES");
            else System.out.println("Case #"+(i+1)+": NO");
        }
    }
}
