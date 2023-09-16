import com.sun.net.httpserver.Authenticator;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class SolutionF {
    static boolean Success=false;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
         String a=input.next();
         int [][] Maj=new int[4][9];
         for(int j=0;j<a.length();j++)
         {
             if(j%2!=0)
             {
                 if(a.charAt(j)=='w')
                 {
                     Maj[0][Integer.parseInt(a.charAt(j-1)+"")-1]++;
                 }
                 if(a.charAt(j)=='b')
                 {
                        Maj[1][Integer.parseInt(a.charAt(j-1)+ "")-1]++;
                 }
                 if(a.charAt(j)=='s')
                 {
                     Maj[2][Integer.parseInt(a.charAt(j-1)+ "")-1]++;
                 }
                 if(a.charAt(j)=='z')
                 {
                     Maj[3][Integer.parseInt(a.charAt(j-1)+ "")-1]++;
                 }
             }
         }
         Success=dfs(Maj);
         if(Success)
         {
                System.out.println("Blessing of Heaven");
            }
            else
            {
                System.out.println("Bad luck");
         }
        }
    }
    public static List<int[][]> canDelete(int[][] Maj, int r, int l)
    {
        List<int[][]> nextStates = new ArrayList<>();
        if(Maj[r][l]>=3)
        {
            int[][] newMaj =Copy(Maj);
            newMaj[r][l]-=3;
            nextStates.add(newMaj);
        }
        if(r!=3&&l<=6&&Maj[r][l]>0&& Maj[r][l + 1] > 0 && Maj[r][l + 2] > 0)
        {
            int[][] newMaj =Copy(Maj);
            newMaj[r][l]--;
            newMaj[r][l+1]--;
            newMaj[r][l+2]--;
            nextStates.add(newMaj);
        }
        return nextStates;
    }
    public static int[][] Copy(int[][] oriMaj)
    {
        int[][] copyMaj = new int[oriMaj.length][];
        for(int i=0;i<oriMaj.length;i++)
        {
           copyMaj[i]=oriMaj[i].clone();
        }
        return copyMaj;
    }
    public static boolean dfs(int[][] MaJ)
    {
        int remaining=0;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<9;j++)
            {
                remaining+=MaJ[i][j];
            }
        }
        if(remaining==2)
        {
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<9;j++)
                {
                    if(MaJ[i][j]==2)
                    {
                        return true;
                    }
                }
            }
            return false;
        }
     for(int i=0;i<4;i++)
     {
         for(int j=0;j<9;j++)
         {
          if(MaJ[i][j]>0)
          {
              List<int[][]> nextStates = canDelete(MaJ,i,j);
              for(int[][] nextState:nextStates)
              {
                  if(dfs(nextState))
                  {
                      return true;
                  }
              }
          }
         }
     }
        return false;
    }
}
