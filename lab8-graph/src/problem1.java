import java.util.*;

public class problem1 {
    public int number;
    public LinkedList<Integer>[] adj;

    public problem1(int number) {
        this.number = number;
        adj = new LinkedList[number];
        for (int i = 0; i < number; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    void addEdge(int src, int dest) {
        adj[src].add(dest);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int m = input.nextInt();
            problem1 g = new problem1(n);

            for (int j = 0; j < m; j++) {
                int a = input.nextInt() - 1;
                int b = input.nextInt() - 1;
                g.addEdge(a, b);
            }

            int[][] matrix = new int[n][n];


            for (int j = 0; j < n; j++) {
                for (int dest : g.adj[j]) {
                    matrix[j][dest] = 1;
                }
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.print(matrix[j][k] + " ");
                }
                System.out.println();
            }
        }

        input.close();
    }
}
