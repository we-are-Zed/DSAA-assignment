import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class problemC {

    public static void computeTransitionFunction(String s, PrintWriter out) {
        int m = s.length();
        for (int i = 0; i < m; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                int k = Math.min(m, i + 1);
                while (k > 0) {
                    if (s.charAt(k - 1) != c) {
                        k--;
                    } else {
                        String suffix = s.substring(0, i) + c;
                        if (s.substring(0, k).equals(suffix.substring(i + 1 - k))) {
                            break;
                        }
                        k--;
                    }
                }
                out.print(k + " ");
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
