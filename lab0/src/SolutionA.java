import java.util.Scanner;
class SolutionA {
    public static void main(String[] args) {
        Scanner input1=new Scanner(System.in);
        int n=input1.nextInt();
        int[] A=new int[n];
        for(int i=0;i<n;i++)
        {
            int a=input1.nextInt();
            A[i]=a;
        }
        int m=input1.nextInt();
        int[] B=new int[m];
        for(int i=0;i<m;i++)
        {
            int b=input1.nextInt();
            B[i]=b;
        }
        for(int j=0;j<m;j++)
        {
            int a=B[j];
            int b=0;
            for(int i=0;i<n;i++)
            {
                if(B[j]==A[i])
                {
                    System.out.println("yes");
                    b=1;
                    break;
                }
            }
            if(b==0){
                System.out.println("no");
            }
        }
    }
}