import java.util.Scanner;

public class SolutionB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long[] arr1 = new long[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = input.nextInt();
        }
        mergeSort(arr1, 0, n - 1);
        if(n%2==0)
        {
            System.out.println((arr1[n/2-1]+arr1[n/2]));
        }
        else {
            System.out.println(2*arr1[n/2]);
        }
    }
    public static void mergeSort(long[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
    public static void merge(long[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        long[] L = new long[n1];
        long[] R = new long[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
