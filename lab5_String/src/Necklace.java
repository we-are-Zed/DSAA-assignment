import java.util.Scanner;

public class Necklace {

    private static int[] next_s = new int[100001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        while (n-- > 0) {
            String s = sc.nextLine();
            getnext(s);
            int len = s.length();
            if (next_s[len] == 0) {
                System.out.println(len);
                continue;
            }
            int circle_node = len - next_s[len];
            int add = circle_node - len % circle_node;
            if (len % circle_node == 0) {
                System.out.println(0);
                continue;
            }
            System.out.println(add);
        }
    }

    private static void getnext(String s) {
        int j = -1, i = 0;
        next_s[0] = -1;

        while (i <= s.length()) {
            if (j == -1 || (i < s.length() && s.charAt(i) == s.charAt(j))) {
                next_s[++i] = ++j;
            } else {
                j = next_s[j];
            }
        }
    }
}
