import java.util.Scanner;
public class problemA {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int T=input.nextInt();
        for(int i=0;i<T;i++)
        {
            String s=input.next();
            int len=s.length();
            System.out.println((len+1)*len/2);
        }
    }
}
