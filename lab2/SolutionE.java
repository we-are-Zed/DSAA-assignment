import java.util.Scanner;
import java.util.Arrays;
public class SolutionE {
    public static void main(String[] args) {
         Scanner input = new Scanner(System.in);
         int n = input.nextInt();
         int arr[] = new int[n];
         int Arr[] = new int[n];
         for(int i=0;i<n;i++)
         {
             arr[i]=input.nextInt();
         }
         Arrays.sort(arr);
         int K=n/3;
         int arr1[] = new int[K];
         for(int i=0;i<K;i++)
         {
             arr1[i]=arr[i];
             arr[i]=0;
         }
         int b=0;
         for(int i=0;i<n&&b<K;i+=3)
         {
             Arr[i]=arr1[b];
             b++;
         }
         int arr2[] = new int[n-K];
         for(int i=K;i<n;i++)
         {
             arr2[i-K]=arr[i];
         }
         int j=0;
         for(int i=0;i<n;i++)
         {
             if(Arr[i]==0)
             {
                 Arr[i]=arr2[j];
                 j++;
             }
         }
         System.out.println(Find_K(Arr));
            for(int i=0;i<n;i++)
            {
                System.out.print(Arr[i]+" ");
            }
    }
    public static int Find_K(int[] Arr)
    {
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
}
