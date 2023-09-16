import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SolutionB_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        Set<Integer> setA = new HashSet<>(n);

        String[] aElements = br.readLine().split(" ");
        for (String element : aElements) {
            setA.add(Integer.parseInt(element));
        }

        int m = Integer.parseInt(br.readLine());
        String[] bElements = br.readLine().split(" ");
        for (String element : bElements) {
            if (setA.contains(Integer.parseInt(element))) {
                pw.println("yes");
            } else {
                pw.println("no");
            }
        }

        pw.flush();  // 确保所有的输出都被写入
    }
}
