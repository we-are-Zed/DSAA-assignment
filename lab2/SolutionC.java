import java.io.*;
import java.util.Scanner;

public class SolutionC {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int n = input.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = input.nextInt();
            }
            long swapCount = countInversions(arr);
            System.out.println(swapCount);
        }
    }

    public static long countInversions(int[] arr) {
        return countInversions(arr, new int[arr.length], 0, arr.length - 1);
    }
    private static long countInversions(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return 0;
        int mid = (left + right) / 2;
        long count = countInversions(arr, temp, left, mid) + countInversions(arr, temp, mid + 1, right);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                count += mid - i + 1;
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, left, arr, left, right - left + 1);
        return count;
    }
}
