import java.util.Scanner;
public class SolutionD {
    public static void main(String[] args) {
     Scanner input=new Scanner(System.in);

     int n=input.nextInt();
        int S=input.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=input.nextInt();
        }
        long count=0;
        for(int i=0;i<n-2;i++)
        {
            int sum=S-arr[i];
            int index1=i+1;
            int index2=n-1;
            while (index2>index1)
            {
                if(arr[index1]+arr[index2]>sum)
                {
                    index2--;
                }
                else if(arr[index1]+arr[index2]<sum)
                {
                    index1++;
                }
                else
                {
                    int ReLeft = 1;
                    int ReRight = 1;
                    if(arr[index1]==arr[index2])
                    {
                        int C=index2-index1+1;
                        count+=C*(C-1)/2;
                        break;
                    }
                    while (index1 + 1 < index2 && arr[index1] == arr[index1 + 1])
                    {
                        index1++;
                        ReLeft++;

                    }
                    while (index2 - 1 > index1 && arr[index2] == arr[index2 - 1])
                    {
                        index2--;
                        ReRight++;
                    }
                    int Repeat= ReLeft * ReRight;
                    count+=Repeat;
                    index1++;
                    index2--;
                }
            }
        }
        System.out.println(count);
    }
}
