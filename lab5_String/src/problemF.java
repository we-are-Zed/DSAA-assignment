import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
public class problemF{
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);

        try (PrintWriter writer = new PrintWriter("output.txt")) {
            int T = input.nextInt();
            input.nextLine();  // Consume the newline

            for(int i = 0; i < T; i++)
            {
                String cipher = input.nextLine();
                String s = input.nextLine();
                int result = minSecondHalfLength(cipher, s);
                System.out.println(result);           // Print to console
                writer.println(result);               // Write to file
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static int minSecondHalfLength(String cipher, String s) {
        char[] map = new char[26];
        String[] chars = cipher.split(" ");
        for (int i = 0; i < 26; i++) {
            map[i] = chars[i].charAt(0);
        }

        String firstHalf = s.substring(0, s.length() / 2);
        StringBuilder decrypted = new StringBuilder();
        for (char c : firstHalf.toCharArray()) {
            decrypted.append((char) ('a' + indexOf(map, c)));
        }

        String combined = decrypted + "$" + s.substring(s.length() / 2); // Use $ as separator
        int[] lps = computeLPSArray(combined);

        int maxOverlap = lps[lps.length - 1];
        return s.length() - maxOverlap;
    }

    private static int[] computeLPSArray(String pat) {
        int M = pat.length();
        int[] lps = new int[M];
        int len = 0;
        int i = 1;
        lps[0] = 0;
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }

    public static int indexOf(char[] arr, char c) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) return i;
        }
        return -1;
    }
}