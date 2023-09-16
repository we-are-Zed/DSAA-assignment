import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class SolutionE {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            int z = input.nextInt();
            char[][] TouYing = new char[2 * y + 2 * z + 1][2 * y + 2 * x + 1];
            for (int m = 0; m < TouYing.length; m++) {
                Arrays.fill(TouYing[m], '.');
            }
            for (int m = 0; m < 2 * y + 2 * z + 1; m++) {
                int o = x;
                int O = x + 1 ;
                for (int j = 0; j < 2 * y + 2 * x + 1; j++) {
                 if(m<=2*y&&m%2==0)
                 {
                     if(j%2==0&&j>=2*y-m&&O>0)
                     {
                            TouYing[m][j]='+';
                            O--;
                     }
                     if(j%2!=0&&j>=2*y-m&&o>0)
                     {
                         TouYing[m][j]='-';
                         o--;
                     }
                 }
                 if(m<=2*y&&m%2!=0)
                 {
                     if(j%2!=0&&j>=2*y-m&&O>0)
                     {
                         TouYing[m][j]='/';
                         O--;
                     }
                 }


                 if(m>2*y&&m%2==0)
                 {
                     if(j%2==0&&j<2*x+1&&O>0)
                     {
                         TouYing[m][j]='+';
                         O--;
                     }
                        if(j%2!=0&&j<2*x+1&&o>0)
                        {
                            TouYing[m][j]='-';
                            o--;
                        }
                 }
                 if(m>2*y&&m%2!=0)
                 {
                        if(j%2==0&&j<2*x+1&&O>0)
                        {
                            TouYing[m][j]='|';
                            O--;
                        }
                 }
                }
            }
            int k=0;
            for(int B=2*x;B<2*y+2*x+1;B++) {
                int O=z;
                int o=z+1;
                for (int A = 2 * y-k; A < 2 * (y + z) + 1; A++) {
                    if(B%2==0)
                    {
                        if (A % 2 == 0&&o>0) {
                            TouYing[A][B]='+';
                            o--;
                        }
                        if (A % 2 != 0&&O>0) {
                            TouYing[A][B]='|';
                            O--;
                        }
                    }
                    if(B%2!=0)
                    {
                        if(A%2!=0&&o>0)
                        {
                            TouYing[A][B]='/';
                            o--;
                        }
                    }
                }
                k++;

            }
                for (int v = 0; v < TouYing.length; v++) {
                    for (int b = 0; b < TouYing[v].length; b++) {
                        System.out.print(TouYing[v][b]);
                    }
                    System.out.println();
                }


        }
    }
}