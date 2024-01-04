import java.util.*;

public class Solution {
    static class Node {
        boolean giant;
        int level;
        public ArrayList<Integer> adjacent;

        Node() {
            this.adjacent = new ArrayList<>();
        }
    }
    static Node[] g;
    static boolean[] visit;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        g = new Node[n + 1];
        visit = new boolean[n + 1];
        visit[1] = true;
        for (int i = 0; i < n - 1; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            if(g[u] == null) g[u] = new Node();
            if(g[v] == null) g[v] = new Node();
            g[u].adjacent.add(v);
            g[v].adjacent.add(u);
        }
        int m = input.nextInt();
        for (int i = 0; i < m; i++) {
            int giant = input.nextInt();
            g[giant].giant = true;
        }
        int ans = 0;
        for(int v : g[1].adjacent) {
            g[v].level = 1;
            Queue<Integer> q = new LinkedList<>();
            ArrayList<Integer> giants = new ArrayList<>();
            q.add(v);
            visit[v] = true;
            while(!q.isEmpty()) {
                int u = q.poll();
                for(int w : g[u].adjacent) {
                    if(visit[w]) continue;
                    g[w].level = g[u].level + 1;
                    visit[w] = true;
                    q.add(w);
                }
                if(g[u].giant) giants.add(g[u].level);
            }
            for(int i = 0; i < giants.size() - 1; i++) {
                if(giants.get(i) >= giants.get(i + 1)) giants.set(i + 1, giants.get(i) + 1);
            }
            if(giants.size() > 0) ans = Math.max(ans, giants.get(giants.size() - 1));
        }
        System.out.println(ans);
    }
}
