import java.io.*;
import java.util.StringTokenizer;

public class SolutionA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] arr1 = new int[n];
            int[] arr2 = new int[m];
            int[] arr3 = new int[n + m];

            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                arr1[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < m; j++) {
                arr2[j] = Integer.parseInt(st.nextToken());
            }

            mergeArrays(arr1, arr2, arr3, n, m);
            for (int z = 0; z < n + m; z++) {
                out.print(arr3[z] + " ");
            }
            out.println();
        }
        out.flush();
    }

    public static void mergeArrays(int[] arr1, int[] arr2, int[] arr3, int n, int m) {
        int i = 0, j = 0, k = 0;

        // Traverse both array using two pointers
        while (i < n && j < m) {
            if (arr1[i] < arr2[j])
                arr3[k++] = arr1[i++];
            else
                arr3[k++] = arr2[j++];
        }

        // Store remaining elements of first array
        while (i < n)
            arr3[k++] = arr1[i++];

        // Store remaining elements of second array
        while (j < m)
            arr3[k++] = arr2[j++];
    }
}
