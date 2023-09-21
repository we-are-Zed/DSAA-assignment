import java.util.Scanner;
import java.util.Arrays;

public class SolutionA {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;++i) {
            arr[i] = input.nextInt();
        }
        int T= input.nextInt();
        for(int j=0;j<T;j++)
        {
            int target = input.nextInt();
            System.out.println(binarySearch(arr, target));
        }
    }
    public static String binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return "YES";
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return "NO";
    }
}
