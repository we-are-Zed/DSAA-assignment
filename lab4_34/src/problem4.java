import java.io.*;

public class problem4 {


    public static void main(String[] args) throws IOException {
        final int length = 300005;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int t = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < t; i++) {
            int[] ans = new int[length];
            int[] nm = new int[length];
            int[] a = new int[length];
            int[] stack = new int[length];

            int n = Integer.parseInt(br.readLine().trim());
            String[] input = br.readLine().split(" ");


            for (int j = 1; j <= n; j++) {
                a[j] = Integer.parseInt(input[j-1]);
            }

            stack[0] = 0;
            ans[0] = 0;
            nm[n] = a[n];
            for (int j = n - 1; j >= 1; j--) {
                nm[j] = Math.min(nm[j + 1], a[j]);
            }

            int now = 1;
            while (now <= n) {
                if (stack[0] == 0 || stack[stack[0]] > nm[n]) {
                    stack[0]++;
                    stack[stack[0]] = a[now];
                    now++;
                } else {
                    ans[++ans[0]] = stack[stack[0]--];
                }
            }
            while (stack[0] != 0) {
                ans[++ans[0]] = stack[stack[0]--];
            }

            for (int j = 1; j <= n; j++) {
                bw.write(ans[j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }
}
