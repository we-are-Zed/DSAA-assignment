import java.util.Scanner;

public class SolutionB_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long[] arr1 = new long[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = input.nextLong();
        }
        heapSort(arr1);
        if (n % 2 == 0) {
            System.out.println((arr1[n / 2 - 1] + arr1[n / 2]));
        } else {
            System.out.println(2 * arr1[n / 2]);
        }
    }
    public static void heapSort(long[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            long temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    public static void heapify(long[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;
        if (largest != i) {
            long swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}
