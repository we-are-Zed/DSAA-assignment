import java.io.*;
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

class State implements Comparable<State> {
    int node;
    long cost;
    int portalsUsed;

    public State(int node, long cost, int portalsUsed) {
        this.node = node;
        this.cost = cost;
        this.portalsUsed = portalsUsed;
    }

    @Override
    public int compareTo(State other) {
        return Long.compare(this.cost, other.cost);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Nodes[] nodes = new Nodes[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Nodes();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            nodes[u].addEdge(v, w);
        }
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodes[u].addEdge(v, 0);
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        long[][] dist = new long[n + 1][k + 1];
        for (long[] array : dist) {
            for(int i = 0; i < array.length; i++) {
                array[i] = Long.MAX_VALUE;
            }
        }
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(start, 0, 0));
        dist[start][0] = 0;
        long result = Long.MAX_VALUE;
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.node == end) {
                result = cur.cost;
                break;
            }
            if (cur.cost > dist[cur.node][cur.portalsUsed]) {
                continue;
            }
            for (Edges edge : nodes[cur.node].edges) {
                long nextCost = cur.cost + edge.weight;
                int newPortals = cur.portalsUsed + (edge.weight == 0 ? 1 : 0);
                if (newPortals <= k && nextCost < dist[edge.neighbor][newPortals]) {
                    dist[edge.neighbor][newPortals] = nextCost;
                    pq.add(new State(edge.neighbor, nextCost, newPortals));
                }
            }
        }
       out.println(result);
        out.flush();
        out.close();
        br.close();
    }
}
