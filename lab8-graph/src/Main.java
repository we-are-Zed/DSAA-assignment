import java.util.*;
import java.io.*;

public class Main {
    static final long MOD = 1000000007;
    int number;
    long result;
    static class Queue {
        Node[] arr;
        int front, rear, capacity;

        Queue(int capacity) {
            this.capacity = capacity;
            arr = new Node[capacity];
            front = 0;
            rear = -1;
        }

        boolean isEmpty() {
            return front > rear;
        }

        boolean isFull() {
            return rear == capacity - 1;
        }

        void enqueue(Node node) {
            if (isFull()) {
                return;
            }
            arr[++rear] = node;
        }

        Node dequeue() {
            if (isEmpty()) {
                return null;
            }
            return arr[front++];
        }
    }
    class Node {
        int id;
        long currentSum;
        int rudu;
        int count;
        ArrayList<Node> adj;
        Node(int id) {
            this.count=0;
            this.id = id;
            this.currentSum = 0;
            this.rudu = 0;
            adj = new ArrayList<>();
        }
    }
    Node[] nodes;
    boolean[] isVisited;
    Main(int number) {
        this.number = number;
        this.result = 0;
        nodes = new Node[number];
        isVisited = new boolean[number];
        for (int i = 0; i < number; i++) {
            nodes[i] = new Node(i);
        }
    }
     void addEdge(int src, int dest) {
        nodes[src].adj.add(nodes[dest]);
        nodes[dest].rudu++;
    }


    void bfs(long[] a,long[] b){
        Queue queue = new Queue(number);
        for(Node node: nodes){
            if(node.rudu == 0){
                queue.enqueue(node);
            }
        }
        while (!queue.isEmpty()){
            Node node = queue.dequeue();
            isVisited[node.id] = true;
            for(Node adjNode: node.adj){
                if(!isVisited[adjNode.id]){
                    adjNode.count++;
                    if(adjNode.count == adjNode.rudu){
                        queue.enqueue(adjNode);
                        adjNode.currentSum+=(node.currentSum+a[node.id])%MOD;
                        adjNode.currentSum%=MOD;
                        result+=(adjNode.currentSum*b[adjNode.id])%MOD;
                        result%=MOD;
                    }else {
                        adjNode.currentSum+=(node.currentSum+a[node.id])%MOD;
                        adjNode.currentSum%=MOD;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for(int i=0;i<T;i++)
        {
          int n=input.nextInt();
            int m=input.nextInt();
            Main g=new Main(n);
            long[] x=new long[n];
            long[] y=new long[n];
            for(int j=0;j<n;j++)
            {
                x[j]=input.nextLong();
                y[j]=input.nextLong();
            }
            for(int j=0;j<m;j++)
            {
                int a=input.nextInt()-1;
                int b=input.nextInt()-1;
                g.addEdge(a,b);
            }
            g.bfs(x,y);
            System.out.println(g.result%MOD);
        }
    }

}