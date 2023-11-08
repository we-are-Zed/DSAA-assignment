import java.util.Scanner;

public class problemB {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        int[] next = getNextArray(S);
        for (int value : next) {
            System.out.println(value);
        }
    }

    private static int[] getNextArray(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0;
        int j = 0;

        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
