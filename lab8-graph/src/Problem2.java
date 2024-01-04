import java.io.*;
import java.util.*;

public class Problem2 {
    public int number;
    public ArrayList<Integer>[] adj;

    public Problem2(int number) {
        this.number = number;
        adj = new ArrayList[number];
        for (int i = 0; i < number; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    void addEdge(int src, int dest) {
        adj[src].add(dest);
        adj[dest].add(src);
    }

    int[] bfs(int s) {
        int[] dist = new int[number];
        Arrays.fill(dist, -1);
        dist[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] tokens = br.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            int s = Integer.parseInt(tokens[2]) - 1;
            Problem2 g = new Problem2(n);
            for (int j = 0; j < m; j++) {
                tokens = br.readLine().split(" ");
                int a = Integer.parseInt(tokens[0]) - 1;
                int b = Integer.parseInt(tokens[1]) - 1;
                g.addEdge(a, b);
            }
            int[] dist = g.bfs(s);
            for (int j = 0; j < n; j++) {
                out.print(dist[j] + " ");
            }
            out.println();
        }
        br.close();
        out.close();
    }
}
