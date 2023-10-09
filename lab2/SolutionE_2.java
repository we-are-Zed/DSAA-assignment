import java.util.Scanner;

public class SolutionE_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int arr[] = new int[n];
        int Arr[] = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = input.nextInt();
        }

        mergeSort(arr, 0, n-1);

        int K = n/3;
        int arr1[] = new int[K];

        for(int i=0; i<K; i++) {
            arr1[i] = arr[i];
            arr[i] = 0;
        }

        int b=0;
        for(int i=0; i<n && b<K; i+=3) {
            Arr[i] = arr1[b];
            b++;
        }

        int arr2[] = new int[n-K];
        for(int i=K; i<n; i++) {
            arr2[i-K] = arr[i];
        }

        int j=0;
        for(int i=0; i<n; i++) {
            if(Arr[i] == 0) {
                Arr[i] = arr2[j];
                j++;
            }
        }

        System.out.println(Find_K(Arr));
        for(int i=0; i<n; i++) {
            System.out.print(Arr[i] + " ");
        }
    }

    public static int Find_K(int[] Arr) {
        int k=Integer.MAX_VALUE;
        for(int i=0;i<Arr.length-2;i++)
        {
            if(Arr[i]>=Arr[i+1]&&Arr[i]<=Arr[i+2]||Arr[i]>=Arr[i+2]&&Arr[i]<=Arr[i+1])
            {
                if(Arr[i]<k)
                {
                    k=Arr[i];
                }
            }
            else if(Arr[i+1]>=Arr[i]&&Arr[i+1]<=Arr[i+2]||Arr[i+1]>=Arr[i+2]&&Arr[i+1]<=Arr[i])
            {
                if(Arr[i+1]<k)
                {
                    k=Arr[i+1];
                }
            }
            else if(Arr[i+2]>=Arr[i]&&Arr[i+2]<=Arr[i+1]||Arr[i+2]>=Arr[i+1]&&Arr[i+2]<=Arr[i])
            {
                if(Arr[i+2]<k)
                {
                    k=Arr[i+2];
                }
            }
        }
        return k;
    }

    // Merge Sort Implementation
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l+r)/2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];

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
