import java.util.*;

public class SolutionC {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int Q = input.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = input.nextInt();
        }

        for (int j = 0; j < Q; j++) {
            int x = input.nextInt();
            int y = input.nextInt();
            int count = Check(arr, x, y);
            if (count != 0) {
                System.out.println("YES" + " " + count);
            } else {
                System.out.println("NO");
            }
        }
    }
    public static int Left(int[] arr, int x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
    public static int Right(int[] arr, int y) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < y) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l - 1;
    }

    public static int Check(int[] arr, int x, int y) {
        int start = Left(arr, x);
        int end = Right(arr, y);
        return Math.max(0, end - start + 1);
    }
}
