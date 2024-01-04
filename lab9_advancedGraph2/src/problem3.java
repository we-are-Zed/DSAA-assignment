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

public class problem3{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        long[][] grid = new long[n][m];

        Graph graph = new Graph(n*m);
        int max = 0;
        int max_x = 0;
        int max_y = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                long key = scanner.nextLong();
                grid[i][j] = key;
                if(key>max){
                    max = (int)key;
                    max_x = i;
                    max_y = j;
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i>0){
                    graph.addEdge(i*m+j,(i-1)*m+j,grid[i-1][j]*grid[i][j]);
                }
                if(i<n-1){
                    graph.addEdge(i*m+j,(i+1)*m+j,grid[i+1][j]*grid[i][j]);
                }
                if(j>0){
                    graph.addEdge(i*m+j,i*m+j-1,grid[i][j-1]*grid[i][j]);
                }
                if(j<m-1){
                    graph.addEdge(i*m+j,i*m+j+1,grid[i][j+1]*grid[i][j]);
                }
            }
        }
        long res = prim(graph,max_x,max_y,n,m);
        System.out.println(res);

    }

    public static long prim(Graph graph,int max_x,int max_y,int n,int m) {
        long[] key = new long[graph.vertices];
        boolean[] mstSet = new boolean[graph.vertices];
        PriorityQueue<Node> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < graph.vertices; i++) {
            key[i] = Long.MIN_VALUE;
            mstSet[i] = false;
        }

        key[max_x*m+max_y] = 0;
        pq.add(new Node(max_x*m+max_y, key[max_x*m+max_y]));

        long res = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            if (mstSet[u]) continue;

            mstSet[u] = true;
            res += node.key;

            for (edge edge : graph.adjacencyList[u]) {
                int v = (edge.src == u) ? edge.dest : edge.src;
                if (!mstSet[v] && edge.weight > key[v]) {
                    key[v] = edge.weight;
                    pq.add(new Node(v, key[v]));
                }
            }
        }

        return res;
    }
}
