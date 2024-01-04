import java.util.*;
class Nodes {
    List<Edges> edges;

    public Nodes() {
        edges = new ArrayList<>();
    }

    public void addEdge(int neighbor, long weight) {
        edges.add(new Edges(neighbor, weight));
    }
}

class Edges {
    int neighbor;
    long weight;

    public Edges(int neighbor, long weight) {
        this.neighbor = neighbor;
        this.weight = weight;
    }
}




public class problem1 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Nodes[] nodes = new Nodes[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Nodes();
        }

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            long w = scanner.nextLong();
            nodes[u].addEdge(v, w);
        }

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<Edges> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.weight));
        pq.add(new Edges(1, 0));

        while (!pq.isEmpty()) {
            Edges current = pq.poll();
            int city = current.neighbor;
            long weight = current.weight;

            if (dist[city] < weight) continue;

            for (Edges edge : nodes[city].edges) {
                if (dist[city] + edge.weight < dist[edge.neighbor]) {
                    dist[edge.neighbor] = dist[city] + edge.weight;
                    pq.add(new Edges(edge.neighbor, (int)dist[edge.neighbor]));
                }
            }
        }


        if(dist[n]==Long.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(dist[n]);
    }


}