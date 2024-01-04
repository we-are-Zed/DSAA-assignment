import java.util.*;
public class problem5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for(int i=0;i<T;i++)
        {
           int n=input.nextInt();
           int m=input.nextInt();
           int[][] matrix=new int[n][m];
           for(int j=0;j<n;j++)
           {
               for(int k=0;k<m;k++)
               {
                   matrix[j][k]=input.nextInt();
               }
           }
              System.out.println(findMax(matrix));
        }
    }
    public static int findMax(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int Length=(int)Math.pow(2, m);
        int[][] dp = new int[n][Length];

        for (int mask = 0; mask < (Length); mask++) {
            dp[0][mask] = sumOfSelectedElements(matrix, 0, mask);
        }
        for (int i = 1; i < n; i++) {
            for (int mask = 0; mask < (Length); mask++) {
                int maxSum = 0;
                for (int prevMask = 0; prevMask < (Length); prevMask++) {
                    if (!isAdjacent(mask, prevMask, m)) {
                        maxSum = Math.max(maxSum, dp[i - 1][prevMask] + sumOfSelectedElements(matrix, i, mask));
                    }
                }
                dp[i][mask] = maxSum;
            }
        }
        int result = 0;
        for (int mask = 0; mask < (Length); mask++) {
            result = Math.max(result, dp[n - 1][mask]);
        }
        return result;
    }

    private static int sumOfSelectedElements(int[][] matrix, int row, int mask) {
        int sum = 0;
        for (int j = 0; j < matrix[0].length; j++) {
            if ((mask & (1 << j)) != 0) {
                if (j > 0 && (mask & (1 << (j - 1))) != 0) {
                    return 0;
                }
                if (j < matrix[0].length - 1 && (mask & (1 << (j + 1))) != 0) {
                    return 0;
                }
                sum += matrix[row][j];
            }
        }
        return sum;
    }

    private static boolean isAdjacent(int mask1, int mask2, int m) {
        for (int j = 0; j < m; j++) {
            int bit = (int)Math.pow(2, j);
            if ((mask1 & bit) != 0) {
                if ((mask2 & bit) != 0) return true;
                if (j > 0 && (mask2 & (bit >> 1)) != 0) return true;
                if (j < m - 1 && (mask2 & (bit << 1)) != 0) return true;
            }
        }
        return false;
    }


}
