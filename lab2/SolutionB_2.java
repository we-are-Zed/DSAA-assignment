import java.util.Scanner;

public class SolutionB_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long[] arr1 = new long[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = input.nextLong();
        }

        quickSort(arr1, 0, n - 1);

        if (n % 2 == 0) {
            System.out.println((arr1[n / 2 - 1] + arr1[n / 2]));
        } else {
            System.out.println(2 * arr1[n / 2]);
        }
    }

    public static void quickSort(long[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(long[] arr, int low, int high) {
        long pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                long temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        long temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
