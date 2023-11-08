import java.io.*;
public class Main {
    public static class Node
    {
        int value;
        Node next;
        Node pre;
        Node originalNext;
        public Node(int value)
        {
            this.value = value;
            this.next = null;
            this.pre = null;
            this.originalNext = null;
        }
    }
    private Node originalHead=null;
    private Node originalTail=null;
    private Node sortedHead=null;
    private Node sortedTail=null;
    public void insert1(int value){
        Node newNode = new Node(value);
        if(originalHead==null){
            originalHead=newNode;
            originalTail=newNode;
        }else {
            originalTail.originalNext=newNode;
            originalTail=newNode;
        }
    }
    public void insert2(Node node){
        if(sortedHead==null)
        {
            sortedHead=node;
            sortedTail=node;
        }else {
            sortedTail.next=node;
            node.pre=sortedTail;
            sortedTail=node;
        }
    }
    public Node[] mergeSort(Node[] nodes) {
        int length = nodes.length;
        if (length <= 1) return nodes;

        int mid = length / 2;
        Node[] left = new Node[mid];
        Node[] right = new Node[length - mid];

        System.arraycopy(nodes, 0, left, 0, mid);
        for (int i = mid; i < length; i++) {
            right[i - mid] = nodes[i];
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right, nodes);
    }

    private Node[] merge(Node[] left, Node[] right, Node[] result) {
        int leftIndex = 0, rightIndex = 0, mergeIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex].value <= right[rightIndex].value) {
                result[mergeIndex++] = left[leftIndex++];
            } else {
                result[mergeIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            result[mergeIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            result[mergeIndex++] = right[rightIndex++];
        }

        return result;
    }

    public int[] findMinDifferences(int n) {
        Node[] nodes = new Node[n];
        Node curr = originalHead;
        int idx = 0;
        while (curr != null) {
            nodes[idx++] = curr;
            curr = curr.originalNext;
        }
        nodes = mergeSort(nodes);
        for (idx = 0; idx < nodes.length; idx++) {
            insert2(nodes[idx]);
        }
        curr = originalHead;
        int[] result = new int[n];
        idx = 0;
        while (curr != null) {
            int minDiff = Integer.MAX_VALUE;
            if (curr.pre != null) minDiff = Math.min(minDiff, Math.abs(curr.value - curr.pre.value));
            if (curr.next != null) minDiff = Math.min(minDiff, Math.abs(curr.value - curr.next.value));
            result[idx++] = minDiff;

            if (curr.pre != null) curr.pre.next = curr.next;
            if (curr.next != null) curr.next.pre = curr.pre;
            curr = curr.originalNext;
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(br.readLine().trim());
        Main helpNarnal=new Main();
        String[] input=br.readLine().trim().split(" ");
        for(int i=0;i<n;i++){
            int value=Integer.parseInt(input[i]);
            helpNarnal.insert1(value);
        }
        int[] result=helpNarnal.findMinDifferences(n);
        for(int i=0;i<n-1;i++){
            pw.print(result[i]+" ");
        }
        pw.flush();
        br.close();
        pw.close();
    }
}