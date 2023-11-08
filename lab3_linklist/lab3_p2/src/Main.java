import java.util.*;

public class Main {
    private static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private final Node head;
    private final Node tail;
    Node pointer;

    public Main() {
        head = new Node(-1); // Sentinel Node
        tail = new Node(-1); // EOL
        head.next = tail;
        tail.prev = head;
        pointer = tail; // Starts pointing at EOL
    }

    public void insertChar(char c) {
        Node newNode = new Node(c);
        Node preNode = pointer.prev;
        newNode.next = pointer;
        pointer.prev = newNode;
        preNode.next = newNode;
        newNode.prev = preNode;
    }

    public static void mergeSort(Node[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int mid = arr.length / 2;

        Node[] left = Arrays.copyOfRange(arr, 0, mid);
        Node[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(Node[] arr, Node[] left, Node[] right) {
        int i = 0, j = 0, k = 0;

        // Merge the temp arrays back into the original array arr[]
        while (i < left.length && j < right.length) {
            if (left[i].data <= right[j].data) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of left[], if there are any
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // Copy the remaining elements of right[], if there are any
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            nodes[i] = new Node(num);
        }
        Node[] Nodes = Arrays.copyOf(nodes, nodes.length);

        mergeSort(Nodes);
        Nodes[2].data=0;
        for(Node node: nodes) {
            System.out.print(node.data);
        }
        System.out.println();
        for(Node node: Nodes) {
            System.out.print(node.data);
        }

    }
}
