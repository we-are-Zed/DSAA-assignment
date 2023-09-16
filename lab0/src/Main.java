import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();

        for (int i = 0; i < T; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            int z = input.nextInt();

            char[][] projection = generateProjection(x, y, z);
            printProjection(projection);
        }
    }

    private static char[][] generateProjection(int x, int y, int z) {
        char[][] projection = new char[2 * y + 2 * z + 1][2 * y + 2 * x + 1];
        for (char[] row : projection) {
            Arrays.fill(row, '.');
        }

        // Bottom
        for (int i = 2 * y; i < 2 * (y + z); i++) {
            for (int j = 0; j < 2 * (x + y); j++) {
                if (i % 2 == 0) {
                    projection[i][j] = (j % 2 == 0) ? '+' : (j <= 2 * x ? '-' : '.');
                } else {
                    projection[i][j] = (j % 2 == 0) ? '|' : (j <= 2 * x ? '.' : '/');
                }
            }
        }

        // Sides
        for (int i = 0; i < 2 * y; i++) {
            for (int j = 0; j <= i; j++) {
                projection[2 * y - i + j][j] = (i % 2 == 0) ? '+' : '/';
                projection[i][2 * x + j] = (i % 2 == 0) ? '+' : '/';
            }
        }

        return projection;
    }

    private static void printProjection(char[][] projection) {
        for (char[] row : projection) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
