import java.util.Scanner;

public class BinaryTreePostOrder {
    static class Node {
        int value;
        Node left;
        Node right;

        Node(int x) {
            value = x;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int N = scanner.nextInt();
            int[] preorder = new int[N];
            int[] inorder = new int[N];
            for (int i = 0; i < N; i++) {
                preorder[i] = scanner.nextInt();
            }
            for (int i = 0; i < N; i++) {
                inorder[i] = scanner.nextInt();
            }

            Node root = buildTree(preorder, inorder, N);
            postOrderPrint(root);
            System.out.println();
        }
    }

    private static Node buildTree(int[] preorder, int[] inorder, int N) {
        int[][] inMap = new int[N + 1][2];
        for (int i = 0; i < N; i++) {
            inMap[inorder[i]][0] = 1;
            inMap[inorder[i]][1] = i;
        }
        return buildTree(preorder, 0, preorder.length - 1, inMap, 0, inorder.length - 1);
    }

    private static Node buildTree(int[] preorder, int preStart, int preEnd, int[][] inMap, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;

        Node root = new Node(preorder[preStart]);
        int inRoot = inMap[root.value][1];
        int numsLeft = inRoot - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft, inMap, inStart, inRoot - 1);
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd, inMap, inRoot + 1, inEnd);

        return root;
    }

    private static void postOrderPrint(Node node) {
        if (node == null) {
            return;
        }
        postOrderPrint(node.left);
        postOrderPrint(node.right);
        System.out.print(node.value + " ");
    }
}
