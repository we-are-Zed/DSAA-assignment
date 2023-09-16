import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
public class SolutionC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++)
        {
            String A = br.readLine();
            String[] MJ = A.split(" ");
            Object[][] Data = new Object[A.length()][2];
            int[] rank=new int[MJ.length];
            for(int j=0;j<MJ.length;j++)
            {
             String mj = MJ[j];
             Data[j][0] = mj.charAt(0);
             if(mj.length()==2)
             {
                 Data[j][1]=Integer.parseInt(mj.substring(1));
             }
             else
             {
                 Data[j][1]=0;
             }
            }
            for(int z=0;z< MJ.length;z++)
            {
                if(Data[z][0].equals('W')&&(!Data[z][1].equals(0)))
                {
                    rank[z]+=60;
                }
                else if(Data[z][0].equals('T'))
                {
                    rank[z]+=40;
                }
                else if(Data[z][0].equals('Y'))
                {
                    rank[z]+=30;
                }
                rank[z]+=(10-Integer.parseInt(Data[z][1].toString()));
                if(Data[z][0].equals('E'))
                {
                    rank[z]+=7;
                }
                if(Data[z][0].equals('S'))
                {
                    rank[z]+=6;
                }
                if(Data[z][0].equals('W')&&Data[z][1].equals(0))
                {
                    rank[z]+=5;
                }
                if(Data[z][0].equals('N'))
                {
                    rank[z]+=4;
                }
                if(Data[z][0].equals('B'))
                {
                    rank[z]+=3;
                }
                if(Data[z][0].equals('F'))
                {
                    rank[z]+=2;
                }
                if(Data[z][0].equals('Z'))
                {
                    rank[z]+=1;
                }
            }
            for(int m=0;m< MJ.length;m++)
            {
                for(int n=MJ.length-1;n>0;n--)
                {
                    if(rank[n]>rank[n-1])
                    {
                        int temp1=rank[n];
                        rank[n]=rank[n-1];
                        rank[n-1]=temp1;
                        Object temp2=Data[n][0];
                        Data[n][0]=Data[n-1][0];
                        Data[n-1][0]=temp2;
                        Object temp3=Data[n][1];
                        Data[n][1]=Data[n-1][1];
                        Data[n-1][1]=temp3;
                    }
                }
            }
            for(int k=0;k< MJ.length;k++)
            {
                System.out.print(Data[k][0]);
                if(!Data[k][1].equals(0))
                {
                    System.out.print(Data[k][1]);
                }
                if(k!=MJ.length-1)
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
