import java.util.Scanner;
public class SolutionD {
    public static void main(String[] args) {
     Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=input.nextInt();
        }
        MergeSort(arr,0,n-1);
        System.out.println(arr[k-1]);
    }
    public static void MergeSort(int[] arr,int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            MergeSort(arr, l, m);
            MergeSort(arr, m + 1, r);
            Merge(arr, l, m, r);
        }
    }
    public static void Merge(int[] arr,int l,int m,int r)
    {
        int[] left= new int[m-l+1];
        int[] right= new int[r-m];
        for(int i=0;i<m-l+1;i++)
        {
            left[i]=arr[l+i];
        }
        for(int i=0;i<r-m;i++)
        {
            right[i]=arr[m+1+i];
        }
        int i=0,j=0,k=l;
        while(i<m-l+1&&j<r-m)
        {
            if(left[i]<=right[j])
            {
                arr[k]=left[i];
                i++;
            }
            else
            {
                arr[k]=right[j];
                j++;
            }
            k++;
        }
        while (i < m-l+1) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < r-m) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}
