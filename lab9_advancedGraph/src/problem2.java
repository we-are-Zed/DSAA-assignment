import java.util.*;

class edge {
    int src, dest;
    long weight;

    edge(int src, int dest, long weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    int vertices;
    ArrayList<edge>[] adjacencyList;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    void addEdge(int src, int dest, long weight) {
        edge E = new edge(src, dest, weight);
        adjacencyList[src].add(E);
        adjacencyList[dest].add(E);
    }
}

class Node implements Comparable<Node> {
    int vertex;
    long key;

    Node(int vertex, long key) {
        this.vertex = vertex;
        this.key = key;
    }

    @Override
    public int compareTo(Node other) {
        return Long.compare(this.key, other.key);
    }
}

public class problem2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Graph graph = new Graph(n);
        long totalWeight = 0;

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            long w = scanner.nextLong();
            graph.addEdge(u, v, w);
            if(w>0){
                totalWeight+=w;
            }
        }

        long mstWeight = prim(graph);
        System.out.println(totalWeight - mstWeight);
    }

    public static long prim(Graph graph) {
        long[] key = new long[graph.vertices];
        boolean[] mstSet = new boolean[graph.vertices];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i = 0; i < graph.vertices; i++) {
            key[i] = Long.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        pq.add(new Node(0, key[0]));

        long res = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            if (mstSet[u]) continue;

            mstSet[u] = true;
            if(node.key>0){
                res += node.key;
            }


            for (edge edge : graph.adjacencyList[u]) {
                int v = (edge.src == u) ? edge.dest : edge.src;
                if (!mstSet[v] && edge.weight < key[v]) {
                    key[v] = edge.weight;
                    pq.add(new Node(v, key[v]));
                }
            }
        }

        return res;
    }
}
