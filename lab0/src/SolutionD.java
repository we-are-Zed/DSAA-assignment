import java.io.*;
import java.util.Scanner;
public class SolutionD {
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        int T=input.nextInt();
        for(int i=0;i<T;i++)
        {
            int m=input.nextInt();
            int [] a=new int[m];
            for(int j=0;j<m;j++)
            {
                a[j]=input.nextInt();
            }
            System.out.println(FindMax(a));
        }

    }
    public static int FindMax(int[] array)
    {
        int max=array[0];
        int Diff=Integer.MIN_VALUE;
        for (int i=1;i<array.length;i++)
        {
            Diff=Math.max(max-array[i],Diff);
            max=Math.max(max,array[i]);
        }
        return Diff;
    }
}
