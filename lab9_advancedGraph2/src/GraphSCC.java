import java.io.*;
import java.util.*;

public class GraphSCC {
    private static List<List<Integer>> graph;
    private static List<List<Integer>> transposedGraph;
    private static boolean[] visited;
    private static boolean[] visited2;
    private static Stack<Integer> stack;
    private static int[] color;
    private static int SCCCount;
    private static int[] rudu;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken()) - 1;
        graph = new ArrayList<>();
        transposedGraph = new ArrayList<>();
        visited = new boolean[n];
        visited2 = new boolean[n];
        stack = new Stack<>();
        color = new int[n];
        rudu = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            transposedGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph.get(u).add(v);
            transposedGraph.get(v).add(u);
        }
        kosaraju(n);
        for (int i = 0; i < n; i++) {
            for (int j : graph.get(i)) {
                if (color[i] != color[j]) {
                    rudu[color[j]]=rudu[color[j]]+1;
                }
            }
        }
        int Count = 0;
        boolean sInZero;
        if (rudu[color[s]] == 0) {
            sInZero = true;
        } else {
            sInZero = false;
        }
        for (int i = 0; i < SCCCount; i++) {
            if (rudu[i] == 0) {
                Count++;
            }
        }
        int result;
        if(sInZero){
            result=Count-1;
        }else {
            result=Count;
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
    private static void kosaraju(int n) {
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Order(i);
            }
        }
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited2[v]) {
                dfsTranspose(v);
                SCCCount++;
            }
        }
    }

    private static void Order(int v) {
        visited[v] = true;
        for (int n : graph.get(v)) {
            if (!visited[n]) {
                Order(n);
            }
        }
        stack.push(v);
    }

    private static void dfsTranspose(int v) {
        visited2[v] = true;
        color[v] = SCCCount;
        for (int n : transposedGraph.get(v)) {
            if (!visited2[n]) {
                dfsTranspose(n);
            }
        }
    }
}
