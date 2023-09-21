import java.util.Scanner;
public class SolutionB {
    public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       int T= input.nextInt();
       for(int i=0;i<T;i++)
       {
              long n=input.nextLong();
              System.out.println(sum(n));
       }
    }
    public static long sum(long n)
    {
     long sum1=n*(n+1)*(n*2+1)/6;
     long sum2=n*(n+1)/2;
     return (sum1+sum2)/2;
    }
}
