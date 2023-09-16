import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class SolutionB {
    public static void main(String[] args) {
        Scanner input1 = new Scanner(System.in);
        int n = input1.nextInt();
        Set<Integer> setA = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            setA.add(input1.nextInt());
        }
        int m = input1.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int b = input1.nextInt();
            if (setA.contains(b)) {
               sb.append("yes\n");
            } else {
                sb.append("no\n");
            }
        }
        System.out.print(sb.toString());
    }
}
