import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    static class Node {
        int key;
        ChildNode firstChild;

        public Node(int key) {
            this.key = key;
            this.firstChild = null;
        }

        void addChild(int childKey) {
            ChildNode newChild = new ChildNode(childKey);
            if (this.firstChild == null) {
                this.firstChild = newChild;
            } else {
                ChildNode temp = this.firstChild;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newChild;
            }
        }
    }

    static class ChildNode {
        int key;
        ChildNode next;

        public ChildNode(int key) {
            this.key = key;
            this.next = null;
        }
    }

    Node[] nodes;
    int[] leaves;
    int leafCount = 0;
    boolean[] visited;


    Main(int size) {
        nodes = new Node[size + 1];
        leaves = new int[size];
        visited = new boolean[size + 1];
        for (int i = 1; i <= size; i++) {
            nodes[i] = new Node(i);
        }
    }
    void insert(int parentKey, int childKey) {
        visited[1]=true;
        if (!visited[childKey]) {
            nodes[parentKey].addChild(childKey);
            visited[childKey] = true;
        } else {
            nodes[childKey].addChild(parentKey);
        }
    }

    void collectLeaves(Node node) {
        if (node != null) {
            if (node.firstChild == null) {
                leaves[leafCount++] = node.key;
            } else {
                ChildNode child = node.firstChild;
                while (child != null) {
                    collectLeaves(nodes[child.key]);
                    child = child.next;
                }
            }
        }
    }

    void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (i = left, k = 0; i <= right; i++, k++) {
            array[i] = temp[k];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine().trim());
        Main tree = new Main(n);
        for (int i = 0; i < n - 1; i++) {
            String[] input = br.readLine().trim().split(" ");
            int parent = Integer.parseInt(input[0]);
            int child = Integer.parseInt(input[1]);
            tree.insert(parent, child);
        }
        tree.collectLeaves(tree.nodes[1]);
        tree.mergeSort(tree.leaves, 0, tree.leafCount - 1);

        for (int i = 0; i < tree.leafCount; i++) {
            out.print(tree.leaves[i] + " ");
        }
        out.flush();
        out.close();
    }
}
