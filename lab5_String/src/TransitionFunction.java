import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class TransitionFunction {
    public static void computeTransitionFunction(String s, PrintWriter out) {
        int m = s.length();
        int[][] transition = new int[m][26];

        // Compute the longest suffix which is also a prefix for every position
        int[] longestSuffix = new int[m + 1];
        longestSuffix[0] = -1;  // Base case

        for (int i = 1; i <= m; i++) {
            int j = longestSuffix[i - 1];
            while (j != -1 && s.charAt(j) != s.charAt(i - 1)) {
                j = longestSuffix[j];
            }
            longestSuffix[i] = j + 1;
        }

        // Compute the transition function using the longestSuffix array
        for (int i = 0; i < m; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (i > 0 && s.charAt(i) != c) {
                    transition[i][c - 'a'] = transition[longestSuffix[i]][c - 'a'];
                } else {
                    transition[i][c - 'a'] = i + (s.charAt(i) == c ? 1 : 0);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 26; j++) {
                out.print(transition[i][j] + " ");
            }
            out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String s = in.readLine();
        computeTransitionFunction(s, out);

        out.flush();
    }
}
